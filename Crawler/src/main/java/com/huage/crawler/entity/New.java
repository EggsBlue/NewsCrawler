package com.huage.crawler.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.sql.Timestamp;

@Table("t_news")
public class New {
    @Id
    private Integer id; //编号
    @Column()
    private String title; //标题
    @Column
    private String Type; //类型
    @Column
    private String time; //日期
    @Column
    String content; //内容
    @Column
    private Timestamp create_time; //创建时间
    @Column
    private String source; //数据源
    @Column
    private String souceUrl; //源地址
    @Column
    private String sourceImg;//图片地址

    public New(Integer id, String title, String type, String time, String content, Timestamp create_time, String source, String souceUrl, String sourceImg) {
        this.id = id;
        this.title = title;
        Type = type;
        this.time = time;
        this.content = content;
        this.create_time = create_time;
        this.source = source;
        this.souceUrl = souceUrl;
        this.sourceImg = sourceImg;
    }

    public New() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSouceUrl() {
        return souceUrl;
    }

    public void setSouceUrl(String souceUrl) {
        this.souceUrl = souceUrl;
    }

    public String getSourceImg() {
        return sourceImg;
    }

    public void setSourceImg(String sourceImg) {
        this.sourceImg = sourceImg;
    }
}
