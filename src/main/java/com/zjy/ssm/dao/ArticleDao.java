/**
 * FileName: ArticleDao
 * Author:   zhangjiuyang
 * Date:     2018/8/3 12:46
 * Description:  文章接口mapper
 */
package com.zjy.ssm.dao;

import com.zjy.ssm.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    /*
     * 返回相应的数据集合
     * @author zhangjiuyang
     * @date 2018/8/3 12:48
     * @param [map]
     * @return java.util.List<com.zjy.ssm.entity.Article>
     */
    public List<Article> findArticles(Map<String, Object> map);

    /*
     * 返回文章数据数目
     * @author zhangjiuyang
     * @date 2018/8/3 12:49
     * @param [map]
     * @return java.lang.Long
     */
    public Long getTotalArticles(Map<String, Object> map);

    /*
     * 添加文章
     * @author zhangjiuyang
     * @date 2018/8/3 12:51
     * @param [article]
     * @return int
     */
    public int insertArticle(Article article);

    /*
     * 更新文章
     * @author zhangjiuyang
     * @date 2018/8/3 12:53
     * @param [article]
     * @return int
     */
    public int updateArticle(Article article);

    /*
     * 根据id删除
     * @author zhangjiuyang
     * @date 2018/8/3 12:53
     * @param [id]
     * @return int
     */
    public int deleteArticle(String id);

    /*
     * 根据id查找文章
     * @author zhangjiuyang
     * @date 2018/8/3 12:54
     * @param [id]
     * @return com.zjy.ssm.entity.Article
     */
    public Article getArticleById(String id);
}
