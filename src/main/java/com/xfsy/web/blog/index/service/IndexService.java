package com.xfsy.web.blog.index.service;

import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;

import java.util.List;

/**
 * Interface : IndexService
 * Desc      : ...
 * Use       : ...
 * Author    : xfsyMrFeng
 * Tool      : IntelliJ IDEA
 * Date      : 2017/5/25 0025
 * Time      : 20:32
 */
public interface IndexService {

    //查询目录
    List<Essay> selectCatalog();

    /**
     * 分页查询博文
     * @param pageNo 当前页
     * @param pageSize 每页显示多少条数据
     * @return
     */
    PageBean selectCatalogPages(int pageNo, int pageSize);

    //查询正文
    Essay selecEssay(int id);

    //博文评论
    boolean comment(User user);
}
