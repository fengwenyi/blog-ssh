package com.xfsy.web.blog.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Class : Comment
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/16 0016
 * Time  : 23:22
 */
@Entity
@Table(name = "blog_comment")
public class Comment {

    private int id; // 评论ID
    private String content; // 评论内容
    private User user; // 用户
    private Essay essay; // 文章
    private long time; // 评论时间

    private Set<Reply> replies; // 回复

    public Comment() {
    }

    public Comment(String content, long time) {
        this.content = content;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    @Type(type = "int")
    public int getId() {
        return id;
    }

    @Column(name = "content")
    @Type(type = "java.lang.String")
    public String getContent() {
        return content;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "essay_id", referencedColumnName = "id")
    public Essay getEssay() {
        return essay;
    }

    @Column(name = "time", length = 20)
    @Type(type = "java.lang.Long")
    public long getTime() {
        return time;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    public Set<Reply> getReplies() {
        return replies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEssay(Essay essay) {
        this.essay = essay;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}
