package com.xfsy.web.blog.index.dao.impl;

import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.Tag;
import com.xfsy.web.blog.entity.User;
import com.xfsy.web.blog.index.dao.IndexDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Class : AdminDaoImpl
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/25 0025
 * Time  : 20:37
 */
public class IndexDaoImpl implements IndexDao {

    private SessionFactory index_sessionFactory;

    public List<Essay> selectCatalog() {
        Session session = index_sessionFactory.openSession();
        String hql = "from Essay as essay order by essay.time desc";
        List<Essay> list = session.createQuery(hql).list();
        session.close();
        return list;
    }

    public Essay selectEssay(int id) {
        Session session = index_sessionFactory.openSession();
        String hql = "from Essay as essay where essay.id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        List<Essay> list = query.list();
        for (Essay essay : list) {
            Set<Tag> set = essay.getTags();
            Set<Comment> setC = essay.getComments();
            for (Comment comment : setC) {
                comment.getUser();
            }
        }
        session.close();
        return list.get(0);
    }

    //博文评论
    public boolean comment(User user) {
        Session session = index_sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        //先保存一方，再保存多方
        //1文章，多用户，多评论（晕了）
        //用户，评论
        //Set<Tag> set = essay.getTags();
        Set<Comment> set = user.getComments();

        session.save(user);
        Iterator i = set.iterator();
        while (i.hasNext()) {
            session.save(i.next());
        }

        transaction.commit();
        session.close();
        return true;
    }

    // 查询博文总条数
    public int essayTotalCount() {
        Session session = index_sessionFactory.openSession();
        String hql = "select count(*) from Essay";
        Query query = session.createQuery(hql);
        int totalRecords = ((Long)query.iterate().next()).intValue();
        session.close();
        return totalRecords;
    }

    // 分页查询博文
    public List<Essay> selectEssayPages(int offset, int pageSize) {
        Session session = index_sessionFactory.openSession();
        String hql = "from Essay as essay order by essay.time desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        List<Essay> list = query.list();
        session.close();
        return list;
    }

    public void setIndex_sessionFactory(SessionFactory index_sessionFactory) {
        this.index_sessionFactory = index_sessionFactory;
    }
}
