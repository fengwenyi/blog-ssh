package com.xfsy.web.blog.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Class : Essay
 * Desc  : 文章实体类
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/15 0015
 * Time  : 11:26
 */
@Entity
@Table(name = "blog_essay")
public class Essay {

    private int id; // 文章ID
    private String title; // 文章标题
    private String content; // 文章内容
    private String summary; // 文章摘要
    private String img; // 文章配图
    private int readCount; //阅读量
    private long time; // 发表时间

    private Set<Tag> tags; // 标签
    private Set<Comment> comments; // 评论
    private Set<Reply> replies; // 回复

    public Essay() {
    }

    public Essay(String title, String content, String summary, String img, long time) {
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.img = img;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    @Type(type = "int")
    public int getId() {
        return id;
    }

    @Column(name = "title", length = 100)
    @Type(type = "java.lang.String")
    public String getTitle() {
        return title;
    }

    @Column(name = "content")
    @Type(type = "text")
    public String getContent() {
        return content;
    }

    @Column(name = "summary", length = 255)
    @Type(type = "java.lang.String")
    public String getSummary() {
        return summary;
    }

    @Column(name = "img", length = 255)
    @Type(type = "java.lang.String")
    public String getImg() {
        return img;
    }

    @Column(name = "readCount", length = 100)
    @Type(type = "int")
    public int getReadCount() {
        return readCount;
    }

    @Column(name = "time", length = 100)
    @Type(type = "java.lang.Long")
    public long getTime() {
        return time;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "essay_id")
    public Set<Tag> getTags() {
        return tags;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "essay_id")
    public Set<Comment> getComments() {
        return comments;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "essay_id")
    public Set<Reply> getReplies() {
        return replies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}
