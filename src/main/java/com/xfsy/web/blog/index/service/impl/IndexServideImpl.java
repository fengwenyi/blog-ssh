package com.xfsy.web.blog.index.service.impl;

import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;
import com.xfsy.web.blog.index.dao.IndexDao;
import com.xfsy.web.blog.index.service.IndexService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class : IndexServideImpl
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/25 0025
 * Time  : 20:35
 */
public class IndexServideImpl implements IndexService {

    @Resource(name = "indexDao")
    private IndexDao indexDao;

    public List<Essay> selectCatalog() {
        return indexDao.selectCatalog();
    }

    // 分页查询博文
    public PageBean selectCatalogPages(int pageNo, int pageSize) {
        // 实例化分页bean
        PageBean pageBean = new PageBean();
        //获取博文总条数
        int essayTotalCount = indexDao.essayTotalCount();

        //
        int offset = pageBean.countOffset(pageNo, pageSize);

        // 分页数据
        List<Essay> list = indexDao.selectEssayPages(offset, pageSize);

        // 将分页数据放入分页bean中
        pageBean.setPageNo(pageNo);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalRecords(essayTotalCount);
        pageBean.setList(list);

        return pageBean;
    }

    public Essay selecEssay(int id) {
        return indexDao.selectEssay(id);
    }

    public boolean comment(User user) {
        return indexDao.comment(user);
    }

    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }
}
