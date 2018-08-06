package com.zjy.ssm.service.impl;

import com.zjy.ssm.common.Constants;
import com.zjy.ssm.dao.ArticleDao;
import com.zjy.ssm.entity.Article;
import com.zjy.ssm.redis.RedisUtil;
import com.zjy.ssm.service.ArticleService;
import com.zjy.ssm.util.AntiXssUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private static final Logger log = Logger.getLogger(ArticleService.class);

    @Resource
    private ArticleDao articleDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<Article> findArticle(Map<String, Object> map) {
        return articleDao.findArticles(map);
    }

    @Override
    public Long getTotalArticle(Map<String, Object> map) {
        return articleDao.getTotalArticles(map);
    }

    @Override
    public int addArticle(Article article) {
        if (article.getArticleTitle() == null || article.getArticleContent() == null || getTotalArticle(null) > 90 || article.getArticleContent().length() > 50000) {
            return 0;
        }
        article.setArticleTitle(AntiXssUtil.replaceHtmlCode(article.getArticleTitle()));
        if (articleDao.insertArticle(article) > 0) {
           log.info("insert article success,save article to redis");
           redisUtil.put(Constants.ARTICLE_CACHE_KEY + article.getId(), article);
           return 1;
        }
        return 0;
    }

    @Override
    public int updateArticle(Article article) {
        if (article.getArticleTitle() == null || article.getArticleContent() == null || getTotalArticle(null) > 90 || article.getArticleContent().length() > 50000) {
            return 0;
        }
        article.setArticleTitle(AntiXssUtil.replaceHtmlCode(article.getArticleTitle()));
        if (articleDao.updateArticle(article) > 0) {
            log.info("update article success, delete article in redis and save again");
            redisUtil.del(Constants.ARTICLE_CACHE_KEY + article.getId());
            redisUtil.put(Constants.ARTICLE_CACHE_KEY + article.getId(), article);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteArticle(String id) {
        redisUtil.del(Constants.ARTICLE_CACHE_KEY + id);
        return articleDao.deleteArticle(id);
    }

    @Override
    public Article getArticleById(String id) {
        log.info("get Article by id:" + id);
        Article article = (Article) redisUtil.get(Constants.ARTICLE_CACHE_KEY + id, Article.class);
        if (article != null) {
            log.info("article in redis");
            return article;
        }
        Article articleFromDB = articleDao.getArticleById(id);
        if (articleFromDB != null) {
            log.info("get article from mysql and save article to redis");
            redisUtil.put(Constants.ARTICLE_CACHE_KEY + articleFromDB.getId(), articleFromDB);
            return articleFromDB;
        }
        return null;
    }
}
