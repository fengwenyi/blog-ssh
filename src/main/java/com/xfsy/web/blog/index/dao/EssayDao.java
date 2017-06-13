package com.xfsy.web.blog.index.dao;

import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;

import java.util.List;

/**
 * Interface : EssayDao
 * Desc      : ...
 * Use       : ...
 * Author    : xfsyMrFeng
 * Tool      : IntelliJ IDEA
 * Date      : 2017/5/25 0025
 * Time      : 20:32
 */
public interface EssayDao {

    //查询目录
    List<Essay> selectCatalog();

    //查询文章
    Essay selectEssay(int id);

    //博文
    boolean comment(User user);

    // 查询博文总条数
    int essayTotalCount();

    // 分页查询博文
    List<Essay> selectEssayPages(int offset, int pageSize);
}
