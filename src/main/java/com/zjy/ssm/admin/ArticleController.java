package com.zjy.ssm.admin;

import com.zjy.ssm.common.Result;
import com.zjy.ssm.common.ResultGenerator;
import com.zjy.ssm.entity.Article;
import com.zjy.ssm.entity.PageBean;
import com.zjy.ssm.service.ArticleService;
import com.zjy.ssm.util.DateUtil;
import com.zjy.ssm.util.ResponseUtil;
import com.zjy.ssm.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ArticleController.class);

    @RequestMapping(value = "/datagrid", method = RequestMethod.POST)
    public String list(
            @RequestParam(value = "page", required =  false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            Article article, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (page != null && rows != null) {
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        if (article != null) {
            map.put("articleTitle", StringUtil.formatLike(article.getArticleTitle()));
        }
        List<Article> articleList = articleService.findArticle(map);
        Long total = articleService.getTotalArticle(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(articleList);
        result.put("rows", jsonArray);
        result.put("total", total);
        log.info("request:article/list, map:" + map.toString());
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Result list(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            Article article) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (page != null && rows != null) {
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        if (article != null) {
            map.put("articleTitle", StringUtil.formatLike(article.getArticleTitle()));
        }
        List<Article> articleList = articleService.findArticle(map);
        Long total = articleService.getTotalArticle(map);
        Result result = ResultGenerator.genSuccessResult();
        Map data = new HashMap();
        data.put("rows", articleList);
        data.put("total", total);
        result.setData(data);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Article article) throws Exception {
        int resultTotal = 0;
        article.setArticleCreateDate(DateUtil.getCurrentDateStr());
        resultTotal = articleService.addArticle(article);
        log.info("request: article/save" + article.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Result update(@RequestBody Article article) throws Exception {
        int resultTotal = 0;
        article.setArticleCreateDate(DateUtil.getCurrentDateStr());
        resultTotal = articleService.updateArticle(article);
        log.info("request: article/update" + article.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("更新失败");
        }
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable("ids") String ids) throws Exception {
        if (ids.length() > 20) {
            return ResultGenerator.genFailResult("ERROR");
        }
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            articleService.deleteArticle(idsStr[i]);
        }
        log.info("request: article/delete, ids: " + ids);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result findById(@PathVariable("id") String id) throws Exception {
        Article article = articleService.getArticleById(id);
        Result result = ResultGenerator.genSuccessResult();
        result.setData(article);
        log.info("request: article/findById");
        return result;
    }
}
