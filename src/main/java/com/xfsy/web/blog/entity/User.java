package com.xfsy.web.blog.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Class : User
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/15 0015
 * Time  : 15:11
 */
@Entity
@Table(name = "blog_user")
public class User {

    private int id; // 用户编号
    private String nickname; // 昵称
    private String ip; // ip
    private String position; // 位置
    private int admin; // 管理员
    private long time; // 时间

    private Set<Message> messages; // 留言
    private Set<Comment> comments; // 评论
    private Set<Reply> reply_rep; // 回复者
    private Set<Reply> reply_reped; // 回复者

    public User() {
    }

    public User(String nickname, String ip, String position, int admin, long time) {
        this.nickname = nickname;
        this.ip = ip;
        this.position = position;
        this.admin = admin;
        this.time = time;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    @Type(type = "int")
    public int getId() {
        return id;
    }

    @Column(name = "nickname", length = 100)
    @Type(type = "java.lang.String")
    public String getNickname() {
        return nickname;
    }

    @Column(name = "ip")
    @Type(type = "java.lang.String")
    public String getIp() {
        return ip;
    }

    @Column(name = "position", length = 100)
    @Type(type = "java.lang.String")
    public String getPosition() {
        return position;
    }

    @Column(name = "admin", length = 10)
    @Type(type = "int")
    public int getAdmin() {
        return admin;
    }

    @Column(name = "time", length = 20)
    @Type(type = "java.lang.Long")
    public long getTime() {
        return time;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public Set<Message> getMessages() {
        return messages;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public Set<Comment> getComments() {
        return comments;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_rep")
    public Set<Reply> getReply_rep() {
        return reply_rep;
    }

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_reped")
    public Set<Reply> getReply_reped() {
        return reply_reped;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void setReply_rep(Set<Reply> reply_rep) {
        this.reply_rep = reply_rep;
    }

    public void setReply_reped(Set<Reply> reply_reped) {
        this.reply_reped = reply_reped;
    }
}
