package com.xfsy.web.blog.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Class : Reply
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/16 0016
 * Time  : 23:22
 */
@Entity
@Table(name = "blog_reply")
public class Reply {
    private int id; // 回复ID
    private String content; // 回复ID
    private User user_rep; // 回复者
    private User user_reped; // 被回复者
    private Comment comment; // 评论
    private Essay essay; // 文章
    private Message message; // 留言
    private long time; // 回复时间

    public Reply() {
    }

    public Reply(String content, long time) {
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
    @JoinColumn(name = "user_id_rep", referencedColumnName = "id")
    public User getUser_rep() {
        return user_rep;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_reped", referencedColumnName = "id")
    public User getUser_reped() {
        return user_reped;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    public Comment getComment() {
        return comment;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "essay_id", referencedColumnName = "id")
    public Essay getEssay() {
        return essay;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    public Message getMessage() {
        return message;
    }

    @Column(name = "time", length = 20)
    @Type(type = "java.lang.Long")
    public long getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser_rep(User user_rep) {
        this.user_rep = user_rep;
    }

    public void setUser_reped(User user_reped) {
        this.user_reped = user_reped;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setEssay(Essay essay) {
        this.essay = essay;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
