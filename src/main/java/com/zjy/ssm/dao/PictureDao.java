/**
 * FileName: PictureDao
 * Author:   zhangjiuyang
 * Date:     2018/8/3 12:55
 * Description:  图片接口mapper
 */
package com.zjy.ssm.dao;

import com.zjy.ssm.entity.Picture;

import java.util.List;
import java.util.Map;

public interface PictureDao {
    /*
     * 返回图片的数据集合
     * @author zhangjiuyang
     * @date 2018/8/3 12:56
     * @param [map]
     * @return java.util.List<com.zjy.ssm.entity.Picture>
     */
    public List<Picture> findPictures(Map<String, Object> map);

    /*
     * 图片数据数目
     * @author zhangjiuyang
     * @date 2018/8/3 12:56
     * @param [map]
     * @return java.lang.Long
     */
    public Long getTotalPictures(Map<String, Object> map);

    /*
     * 添加图片
     * @author zhangjiuyang
     * @date 2018/8/3 12:57
     * @param [picture]
     * @return int
     */
    public int insertPicture(Picture picture);

    /*
     * 更新图片
     * @author zhangjiuyang
     * @date 2018/8/3 12:58
     * @param [picture]
     * @return int
     */
    public int updatePicture(Picture picture);

    /*
     * 根据id删除图片
     * @author zhangjiuyang
     * @date 2018/8/3 12:58
     * @param [picture]
     * @return int
     */
    public int deletePicture(String id);

    /*
     * 根据id查找图片
     * @author zhangjiuyang
     * @date 2018/8/3 12:59
     * @param [id]
     * @return com.zjy.ssm.entity.Picture
     */
    public Picture getPictureById(String id);
}
