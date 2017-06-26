package com.xfsy.web.blog.admin.service;

import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;

import java.util.List;

/**
 * Class : IndexService
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 15:22
 */
public interface AdminService {

    //发表文章
    boolean add(Essay essay);

    //文章列表
    List<Essay> select();

    //分页查询
    PageBean selectPage(int pageNo, int pageSize);

    //根据文章ID查询文章
    Essay selectEssayById(Integer id);

    //修改文章

    /**
     * 通过文章ID删除文章
     * @param id 文章ID
     * @return 影响行数
     */
    Integer essayDeleteById(Integer id);

    // 查询所有用户
    List<User> selectUsers();

    // 查询所有评论
    List<Comment> selectComments();
}
