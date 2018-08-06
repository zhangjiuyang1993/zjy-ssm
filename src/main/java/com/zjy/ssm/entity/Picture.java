package com.zjy.ssm.entity;

import java.io.Serializable;

/**
 * @author zhangjiuyang
 * @create 2018/8/3
 * @since 1.0.0
 */
public class Picture implements Serializable {
    private String id;

    private String path;

    private String type;

    private String time;

    private String url;

    private String grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return "Picture [id=" + id + ", path=" + path + ", type=" + type
                + ", time=" + time + ", url=" + url + ", grade=" + grade + "]";
    }
}
