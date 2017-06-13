package com.xfsy.web.blog.index.service.impl;

import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.Tag;
import com.xfsy.web.blog.entity.User;
import com.xfsy.web.blog.index.dao.EssayDao;
import com.xfsy.web.blog.index.service.EssayService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Class : EssayServideImpl
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/25 0025
 * Time  : 20:35
 */
public class EssayServideImpl implements EssayService {

    @Resource
    private EssayDao index_essayDao;

    public List<Essay> selectCatalog() {
        return index_essayDao.selectCatalog();
    }

    // 分页查询博文
    public PageBean selectCatalogPages(int pageNo, int pageSize) {
        // 实例化分页bean
        PageBean pageBean = new PageBean();
        //获取博文总条数
        int essayTotalCount = index_essayDao.essayTotalCount();

        //
        int offset = pageBean.countOffset(pageNo, pageSize);

        // 分页数据
        List<Essay> list = index_essayDao.selectEssayPages(offset, pageSize);

        // 将分页数据放入分页bean中
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecords(essayTotalCount);
        pageBean.setList(list);

        return pageBean;
    }

    public Essay selecEssay(int id) {
        return index_essayDao.selectEssay(id);
    }

    public boolean comment(User user) {
        return index_essayDao.comment(user);
    }

    public void setIndex_essayDao(EssayDao index_essayDao) {
        this.index_essayDao = index_essayDao;
    }
}
