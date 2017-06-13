package com.xfsy.web.blog.bean;

import java.util.List;

/**
 * Class : PageBean
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/24 0024
 * Time  : 19:18
 */
public class PageBean<E> {

    private List<E> list; // 结果集

    private int totalRecords; // 总条数

    private int pageSize; // 每页显示数

    private int pageNo; // 第几页

    //总页数
    public int getToatlPages() {
        return  (totalRecords + pageSize - 1) / pageSize;
    }

    //计算当前页开始记录数
    public int countOffset(int pageNo, int pageSize) {
        int offset = pageSize * (pageNo - 1);
        return offset;
    }

    //首页
    public int getTopPageNo() {
        return 1;
    }

    //上一页
    public int getPrevPageNo() {
        if (pageNo <= 1) {
            return 1;
        }
        return pageNo - 1;
    }

    // 下一页
    public int getNextPageNo() {
        if (pageNo >= getBtmPageNo()) {
            return getBtmPageNo();
        }
        return pageNo + 1;
    }

    //尾页
    public int getBtmPageNo() {
        return getToatlPages();
    }

    public List<E> getList() {
        return list;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
