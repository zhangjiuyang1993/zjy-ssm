package com.zjy.ssm.service.impl;

import com.zjy.ssm.dao.PictureDao;
import com.zjy.ssm.entity.Picture;
import com.zjy.ssm.service.PictureService;
import com.zjy.ssm.util.AntiXssUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureDao pictureDao;

    @Override
    public List<Picture> findPicture(Map<String, Object> map) {
        return pictureDao.findPictures(map);
    }

    @Override
    public Long getTotalPicture(Map<String, Object> map) {
        return pictureDao.getTotalPictures(map);
    }

    @Override
    public int addPicture(Picture picture) {
        if (picture.getPath() == null || getTotalPicture(null) > 90 || picture.getPath().length() > 100 || picture.getUrl().length() > 100) {
            return 0;
        }
        picture.setUrl(AntiXssUtil.replaceHtmlCode(picture.getUrl()));
        return pictureDao.insertPicture(picture);
    }

    @Override
    public int updatePicture(Picture picture) {
        if (picture.getPath() == null || getTotalPicture(null) > 90 || picture.getPath().length() > 100 || picture.getUrl().length() > 100) {
            return 0;
        }
        picture.setUrl(AntiXssUtil.replaceHtmlCode(picture.getUrl()));
        return pictureDao.updatePicture(picture);
    }

    @Override
    public int deletePicture(String id) {
        return pictureDao.deletePicture(id);
    }

    @Override
    public Picture getPictureById(String id) {
        return pictureDao.getPictureById(id);
    }
}
