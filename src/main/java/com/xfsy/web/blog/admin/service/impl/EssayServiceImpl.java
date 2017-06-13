package com.xfsy.web.blog.admin.service.impl;

import com.xfsy.web.blog.admin.dao.EssayDao;
import com.xfsy.web.blog.admin.service.EssayService;
import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class : EssayServiceImpl
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 15:25
 */
public class EssayServiceImpl implements EssayService {

    @Resource
    private EssayDao essayDao;

    public void setEssayDao(EssayDao essayDao) {
        this.essayDao = essayDao;
    }

//    @Transactional
    public boolean add(Essay essay) {
        essayDao.add(essay);
//        int i = 1/0;
        return true;
    }

    public List<Essay> select() {
        return essayDao.select();
    }

    //分页查询
    public PageBean selectPage(int pageNo, int pageSize) {
        PageBean pageBean = new PageBean();

        //总记录
        int totalRecords = essayDao.getTotalRecords();

        //
        int offset = pageBean.countOffset(pageNo, pageSize);

        // 结果集
        List<Essay> list = essayDao.queryPages(offset, pageSize);
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecords(totalRecords);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * service impl -> essay delete by id
     * @param id 文章ID
     * @return
     */
    @Override
    public Integer essayDeleteById(Integer id) {
        return essayDao.essayDeleteById(id);
    }

    public List<User> selectUsers() {
        return essayDao.selectUser();
    }

    public List<Comment> selectComments() {
        return essayDao.selectComments();
    }
}
