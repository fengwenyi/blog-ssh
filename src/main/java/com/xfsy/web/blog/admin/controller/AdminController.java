package com.xfsy.web.blog.admin.controller;

import com.xfsy.web.blog.admin.service.AdminService;
import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.Tag;
import com.xfsy.web.blog.entity.User;
import com.xfsy.web.blog.index.controller.IndexController;
import com.xfsy.web.blog.util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class : AdminController
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 06:56
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping(value = "index")
    public String index() {
        return "admin/index";
    }

    /* Essay */
    @Resource(name = "adminService")
    private AdminService adminService;
    /**
     * 文章列表
     * @return
     */
    @RequestMapping(value = "essay-list")
    public String essayList(HttpServletRequest request) {
        String pageNo = request.getParameter("pageNo");
        if (pageNo == null||pageNo.equals("")) {
            pageNo = 1 + "";
        }
        /*essayService.select();
        List<Essay> list = essayService.select();
        request.setAttribute("list", list);*/
        PageBean pageBean = adminService.selectPage(Integer.valueOf(pageNo), 10);
        request.setAttribute("page", pageBean);
        List<Essay> list = pageBean.getList();
        request.setAttribute("list", list);
        return "admin/essay/list";
    }

    /**
     * 写文章
     * @return
     */
    @RequestMapping(value = "essay-write")
    public String essayWrite() {
        return "admin/essay/write";
    }
    @RequestMapping(value = "essay-write-impl")
    public String essayWriteImpl(HttpServletRequest request, @RequestParam("title-img") MultipartFile file, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //图片上传
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        //String filePath = basePath + "resources/images/essay/";
        String filePath = ("/root/MyApps/tomcat/img/ROOT/images/");
        String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
        File targetFile = new File(filePath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String summary = request.getParameter("summary");
        String tagStr = request.getParameter("tag");
        String fileUrl = "http://img.fengwenyi.com/images/" + fileName;
        long time = System.currentTimeMillis();
        Essay essay = new Essay(title, content, summary, null, time);
        essay.setImg(fileUrl);

        String[] tags = Util.splitStr(tagStr, ",");
        Set<Tag> tagSet = new HashSet<Tag>();
        for (int i = 0; i < tags.length; i++) {
            Tag tag = new Tag(tags[i]);
            tagSet.add(tag);
        }
        essay.setTags(tagSet);
        adminService.add(essay);
        response.setContentType("text/html; charset = utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print("<script>alert('发表成功');</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "admin/essay/list";
    }

    /**
     * 文章修改
     * @return
     */
    @RequestMapping(value = "essay-update/{id}")
    public String essayUpdate(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
        Essay essay = adminService.selectEssayById(id);
        request.setAttribute("essay", essay);
        return "admin/essay/update";
    }

    /**
     * 文章修改实现
     * @return
     */
    @RequestMapping(value = "essay-update-impl")
    public ModelAndView essayUpdateImpl() {
        return null;
    }

    /**
     * 文章删除
     * @return
     */
    @RequestMapping(value = "essay-delete/{id}", method = RequestMethod.GET)
    public ModelAndView essayDeleteById(@PathVariable(value = "id") Integer id) {
        int rowNum = adminService.essayDeleteById(id);
        System.out.println("rowNum: " + rowNum);
        //友好交互
        String tip, intent, number;
        if (rowNum == 1) {
            tip = "操作成功，即将跳转，请稍等···";
            intent = "location.href=\"/admin/essay-list\"";
            number = 2 + "";
        } else {
            tip = "抱歉，删除失败！！！";
            intent = "window.history.back()";
            number = 3 + "";
        }
        return IndexController.jumpTip(tip, intent, number);
    }

    // 用户列表
    @RequestMapping(value = "user-list")
    public String user(HttpServletRequest request) {
        List<User> list = adminService.selectUsers();
        request.setAttribute("list", list);
        return "/admin/user/list";
    }

    // 评论列表
    @RequestMapping(value = "comment-list")
    public String comment(HttpServletRequest request) {
        List<Comment> list = adminService.selectComments();
        request.setAttribute("list", list);
        return "/admin/comment/list";
    }
}
