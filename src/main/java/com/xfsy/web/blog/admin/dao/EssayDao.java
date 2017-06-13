package com.xfsy.web.blog.admin.dao;

import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;

import java.util.List;

/**
 * Class : EssayDao
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 15:22
 */
public interface EssayDao {

    //essay

    public boolean add(Essay essay);

    //查询所有数据
    public List<Essay> select();

    //查询总记录数
    public int getTotalRecords();

    // 查询结果集
    public List<Essay> queryPages(int offset, int pageSize);

    /**
     * 通过文章ID删除文章
     * @param id 文章ID
     * @return 影响行数
     */
    Integer essayDeleteById(Integer id);

    //查询用户
    List<User> selectUser();

    // 查询所有评论
    List<Comment> selectComments();
}
