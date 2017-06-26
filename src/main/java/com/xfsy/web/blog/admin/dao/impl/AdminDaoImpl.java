package com.xfsy.web.blog.admin.dao.impl;

import com.xfsy.web.blog.admin.dao.AdminDao;
import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.Tag;
import com.xfsy.web.blog.entity.User;
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
 * Date  : 2017/5/20 0020
 * Time  : 15:28
 */
public class AdminDaoImpl implements AdminDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean add(Essay essay) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Set<Tag> set = essay.getTags();


        session.save(essay);
        Iterator i = set.iterator();
        while (i.hasNext()) {
            session.save(i.next());
        }
        transaction.commit();
        session.close();
        return true;
    }

    // 查询所有数据
    public List<Essay> select() {
        Session session = sessionFactory.openSession();
        String hql = "from Essay as essay order by essay.time desc";
        List<Essay> list = session.createQuery(hql).list();
        session.close();
        return list;
    }

    // 获取总记录数
    public int getTotalRecords() {
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from Essay";
        Query query = session.createQuery(hql);
        int totalRecords = ((Long)query.iterate().next()).intValue();
        session.close();
        return totalRecords;
    }

    // 获取数据
    public List<Essay> queryPages(int offset, int pageSize) {
        Session session = sessionFactory.openSession();
        String hql = "from Essay as essay order by essay.time desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        List<Essay> list = query.list();
        session.close();
        return list;
    }

    /**
     * 通过文章id查询文章
     * @param id
     * @return
     */
    @Override
    public Essay queryEssayById(Integer id) {
        Session session = sessionFactory.openSession();
        String hql = "from Essay as essay where essay.id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        Essay essay = (Essay) query.uniqueResult();
        session.close();
        return essay;
    }

    /**
     * 文章删除
     * @param id 文章ID
     * @return 影响行数
     */
    public Integer essayDeleteById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete Essay as essay where essay.id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        int rowNum = query.executeUpdate();
        transaction.commit();
        session.close();
        return rowNum;
    }

    public List<User> selectUser() {
        Session session = sessionFactory.openSession();
        String hql  = "from User as user order by user.time desc";
        Query query = session.createQuery(hql);
        List<User> list = query.list();
        session.close();
        return list;
    }

    public List<Comment> selectComments() {
        Session session = sessionFactory.openSession();
        String hql = "from Comment as comment order by comment.time desc";
        Query query = session.createQuery(hql);
        List<Comment> list = query.list();
        for (Comment comment : list) {
            comment.getUser();
            comment.getEssay();
        }
        session.close();
        return list;
    }
}
