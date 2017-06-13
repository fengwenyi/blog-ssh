package com.xfsy.web.blog.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Class : Tag
 * Desc  : 文章标签
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/15 0015
 * Time  : 14:13
 */
@Entity
@Table(name = "blog_tag")
public class Tag {

    private int id;
    private String name;
    private Essay essay;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    @Type(type = "int")
    public int getId() {
        return id;
    }

    @Column(name = "name", length = 20)
    @Type(type = "java.lang.String")
    public String getName() {
        return name;
    }

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "essay_id", referencedColumnName = "id")
    public Essay getEssay() {
        return essay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEssay(Essay essay) {
        this.essay = essay;
    }
}
