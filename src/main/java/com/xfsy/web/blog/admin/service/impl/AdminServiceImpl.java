package com.xfsy.web.blog.admin.service.impl;

import com.xfsy.web.blog.admin.dao.AdminDao;
import com.xfsy.web.blog.admin.service.AdminService;
import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class : IndexServiceImpl
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 15:25
 */
public class AdminServiceImpl implements AdminService {

    @Resource(name = "adminDao")
    private AdminDao adminDao;

//    @Transactional
    public boolean add(Essay essay) {
        adminDao.add(essay);
//        int i = 1/0;
        return true;
    }

    public List<Essay> select() {
        return adminDao.select();
    }

    //分页查询
    public PageBean selectPage(int pageNo, int pageSize) {
        PageBean pageBean = new PageBean();

        //总记录
        int totalRecords = adminDao.getTotalRecords();

        //
        int offset = pageBean.countOffset(pageNo, pageSize);

        // 结果集
        List<Essay> list = adminDao.queryPages(offset, pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecords(totalRecords);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 通过文章id查询文章
     * @param id 文章id
     * @return
     */
    @Override
    public Essay selectEssayById(Integer id) {
        return adminDao.queryEssayById(id);
    }

    /**
     * service impl -> essay delete by id
     * @param id 文章ID
     * @return
     */
    @Override
    public Integer essayDeleteById(Integer id) {
        return adminDao.essayDeleteById(id);
    }

    public List<User> selectUsers() {
        return adminDao.selectUser();
    }

    public List<Comment> selectComments() {
        return adminDao.selectComments();
    }

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }
}
