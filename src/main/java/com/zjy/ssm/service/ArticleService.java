/**
 * FileName: ArticleService
 * Author:   zhangjiuyang
 * Date:     2018/8/3 13:52
 * Description:  文章业务逻辑service
 */
package com.zjy.ssm.service;

import com.zjy.ssm.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    /*
     * 返回文章的数据集合
     * @author zhangjiuyang
     * @date 2018/8/3 13:53
     * @param [map]
     * @return java.util.List<com.zjy.ssm.entity.Article>
     */
    public List<Article> findArticle(Map<String, Object> map);

    /*
     * 返回文件集合的总数
     * @author zhangjiuyang
     * @date 2018/8/3 13:54
     * @param [map]
     * @return java.lang.Long
     */
    public Long getTotalArticle(Map<String, Object> map);

    /*
     * 添加文章
     * @author zhangjiuyang
     * @date 2018/8/3 13:55
     * @param [article]
     * @return int
     */
    public int addArticle(Article article);

    /*
     * 更新文章
     * @author zhangjiuyang
     * @date 2018/8/3 13:55
     * @param [article]
     * @return int
     */
    public int updateArticle(Article article);

    /*
     * 根据id删除文章
     * @author zhangjiuyang
     * @date 2018/8/3 13:56
     * @param [id]
     * @return int
     */
    public int deleteArticle(String id);

    /*
     * 根据id查找文章
     * @author zhangjiuyang
     * @date 2018/8/3 13:56
     * @param [id]
     * @return com.zjy.ssm.entity.Article
     */
    public Article getArticleById(String id);
}
