package com.zjy.ssm.admin;

import com.zjy.ssm.common.Result;
import com.zjy.ssm.common.ResultGenerator;
import com.zjy.ssm.entity.PageBean;
import com.zjy.ssm.entity.Picture;
import com.zjy.ssm.service.PictureService;
import com.zjy.ssm.util.DateUtil;
import com.zjy.ssm.util.ResponseUtil;
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
@RequestMapping("/pictures")
public class PictureController {
    @Resource
    private PictureService pictureService;
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(PictureController.class);

    @RequestMapping("/datagrid")
    public String list(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            Picture picture, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (page != null && rows != null) {
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        if (picture != null) {
            map.put("id", picture.getId() + "");
            map.put("type", picture.getType() + "");
            map.put("grade", picture.getGrade() + "");
        }
        List<Picture> pictureList = pictureService.findPicture(map);
        Long total = pictureService.getTotalPicture(map);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(pictureList);
        result.put("rows", jsonArray);
        result.put("total", total);
        log.info("request: pictures/datagrid, map: " + map.toString());
        ResponseUtil.write(response, result);
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Result list(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            Picture picture) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (page != null && rows != null) {
            PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
            map.put("start", pageBean.getStart());
            map.put("size", pageBean.getPageSize());
        }
        if (picture != null) {
            map.put("id", picture.getId() + "");
            map.put("type", picture.getType() + "");
            map.put("grade", picture.getGrade() + "");
        }
        List<Picture> pictureList = pictureService.findPicture(map);
        Result result = ResultGenerator.genSuccessResult();
        Long total = pictureService.getTotalPicture(map);
        Map data = new HashMap();
        data.put("rows", pictureList);
        data.put("total", total);
        log.info("request: picture/list, map: " + map.toString());
        result.setData(data);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody Picture picture) throws Exception{
        int resultTotal = 0;
        picture.setTime(DateUtil.getCurrentDateStr());
        resultTotal = pictureService.addPicture(picture);
        log.info("request: picture/save," + picture.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("添加失败");
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Result update(@RequestBody Picture picture) throws Exception{
        int resultTotal = 0;
        picture.setTime(DateUtil.getCurrentDateStr());
        resultTotal = pictureService.updatePicture(picture);
        log.info("request: picture/update," + picture.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("更新失败");
        }
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable(value = "ids") String ids) throws Exception {
        if (ids.length() > 20) {
            return ResultGenerator.genFailResult("ERROR");
        }
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            pictureService.deletePicture(idsStr[i]);
        }
        log.info("request: picture/delete, ids: " + ids);
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result findById(@PathVariable(value = "id") String id) throws Exception {
        Picture picture = pictureService.getPictureById(id);
        if (picture != null) {
            Result result = ResultGenerator.genSuccessResult();
            result.setData(picture);
            return result;
        } else {
            return ResultGenerator.genFailResult("无资源");
        }
    }
}
