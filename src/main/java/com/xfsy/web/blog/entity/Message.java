package com.xfsy.web.blog.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Class : Message
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/15 0015
 * Time  : 15:49
 */
@Entity
@Table(name = "blog_message")
public class Message {

    private int id; // 留言ID
    private String content; // 留言内容
    private User user; // 用户
    private long time; //留言时间

    private Set<Reply> replies; // 回复

    public Message() {
    }

    public Message(String content, long time) {
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

    @Column(name = "time", length = 20)
    @Type(type = "java.lang.Long")
    public long getTime() {
        return time;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
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

    public void setTime(long time) {
        this.time = time;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}
