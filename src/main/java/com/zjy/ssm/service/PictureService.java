/**
 * FileName: PictureService
 * Author:   zhangjiuyang
 * Date:     2018/8/3 14:12
 * Description:  图片业务逻辑service
 */
package com.zjy.ssm.service;

import com.zjy.ssm.entity.Picture;

import java.util.List;
import java.util.Map;

public interface PictureService {
    /*
     * 返回图片的数据集合
     * @author zhangjiuyang
     * @date 2018/8/3 14:13
     * @param [map]
     * @return java.util.List<com.zjy.ssm.entity.Picture>
     */
    public List<Picture> findPicture(Map<String, Object> map);

    /*
     * 返回图片数据的总数
     * @author zhangjiuyang
     * @date 2018/8/3 14:13
     * @param [map]
     * @return java.lang.Long
     */
    public Long getTotalPicture(Map<String, Object> map);

    /*
     * 添加图片
     * @author zhangjiuyang
     * @date 2018/8/3 14:14
     * @param [picture]
     * @return int
     */
    public int addPicture(Picture picture);

    /*
     * 更新图片
     * @author zhangjiuyang
     * @date 2018/8/3 14:15
     * @param [picture]
     * @return int
     */
    public int updatePicture(Picture picture);

    /*
     * 根据id删除图片
     * @author zhangjiuyang
     * @date 2018/8/3 14:15
     * @param [id]
     * @return int
     */
    public int deletePicture(String id);

    /*
     * 根据id获取图片
     * @author zhangjiuyang
     * @date 2018/8/3 14:17
     * @param [id]
     * @return com.zjy.ssm.entity.Picture
     */
    public Picture getPictureById(String id);
}
