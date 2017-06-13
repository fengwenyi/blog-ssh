package com.xfsy.web.blog.index.controller;

import com.google.gson.Gson;
import com.xfsy.web.blog.bean.PageBean;
import com.xfsy.web.blog.entity.Comment;
import com.xfsy.web.blog.entity.Essay;
import com.xfsy.web.blog.entity.User;
import com.xfsy.web.blog.index.service.EssayService;
import com.xfsy.web.blog.util.Constant;
import com.xfsy.web.blog.util.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class : AdminController
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 06:32
 */
@Controller
public class IndexController {

    @Resource(name = "index_essayService")
    private EssayService essayService;

    /*首页*/
    @RequestMapping("index")
    public String index(HttpServletRequest request) {
        //文章列表
//        List<Essay> list = essayService.selectCatalog();
        //当前页
        String noStr = request.getParameter("p");
        if (noStr == null||noStr.equals("")) {
            noStr = 1 + "";
        }
        int pageNo = Integer.valueOf(noStr);
        //每页显示多少条数据
        int pageSize = Constant.PAGE_SIZE;
        PageBean pageBean = essayService.selectCatalogPages(pageNo, pageSize);
        // 分页数据
        request.setAttribute("page", pageBean);
        // 博文数据
        List<Essay> list = pageBean.getList();
        request.setAttribute("list", list);
        // ...
        return "index";
    }

    // 博文目录分页请求（异步）
    @RequestMapping(value = "essay-catalog/{p}")
    @ResponseBody
    public String essayCatalog(@PathVariable(value = "p") String p, HttpServletResponse response) {
        response.setContentType("text/html; charset = UTF-8");
        // 异常处理
        if (p == null||p.equals("")) {
            p = 1 + "";
        }
        int pageNo = Integer.valueOf(p);
        //每页显示多少条数据
        int pageSize = Constant.PAGE_SIZE;
        PageBean pageBean = essayService.selectCatalogPages(pageNo, pageSize);
        // 博文数据
        List<Essay> list = pageBean.getList();

        /*
            essay:
                id
                title
                summary
                time
                commentCount
                img
             page:
                prevPageNo
                是否可显示
                nextPageNo
                是否显示
         */
        List<Map<String, String>> lists = new ArrayList<Map<String, String>>();
        for (Essay essay : list) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", essay.getId() + "");
            map.put("title", essay.getTitle());
            map.put("summary", essay.getSummary());
            map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(essay.getTime()));
            map.put("commentCount", essay.getComments().size() + "");
            map.put("img", essay.getImg());
            lists.add(map);
        }

        Gson gson = new Gson();
        String str = gson.toJson(lists);

        String result = "{\"essay\":" + str + ", \"page\":" + pageBean.getPageNo() + "}";
        return result;
    }

    //文章浏览
    @RequestMapping(value = "essay/{id}")
    public String essay(@PathVariable("id") Integer id, HttpServletRequest request) {
        Essay essay = essayService.selecEssay(id);
        request.setAttribute("essay", essay);
        request.setAttribute("comment", Util.sortCommentByTime(essay.getComments())); // 需要根据时间或者ID来排序
        Set<Comment> set = essay.getComments();
        return "essay";
    }

    //文章评论
    @RequestMapping(value = "comment")
    public ModelAndView comment(HttpServletRequest request) {

        //友好交互
        String tip, intent, number;

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //评论
        String content = request.getParameter("content");

        if (content.equals("") || content == null) {
            tip = "抱歉，评论内容不能为空！！！";
            intent = "window.history.back()";
            number = 3 + "";
        } else {
            //时间
            long time = System.currentTimeMillis();

            //用户
            //用户昵称
            String nickname = request.getParameter("nickname");
            //获取用户IP
            String ip = Util.getIpAddr(request);
            //获取用户地址
            String address = "";
            Util util = new Util();
            try {
                address = util.getAddress("ip=" + ip, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }

            //文章
            String essay_id = request.getParameter("essay_id");
            Essay essay = essayService.selecEssay(Integer.valueOf(essay_id));

            Comment comment = new Comment();
            comment.setContent(content);
            comment.setTime(time);

            User user = new User();
            user.setNickname(nickname);
            user.setIp(ip);
            user.setPosition(address);
            user.setAdmin(0);
            user.setTime(time);

            comment.setUser(user);
            comment.setEssay(essay);

            Set<Comment> set = new HashSet<Comment>();
            set.add(comment);
            user.setComments(set);

            boolean result = essayService.comment(user);

            if (result) {
                tip = "操作成功，即将跳转，请稍等···";
                intent = "location.href=\"/essay/" + essay_id + "\"";
                number = 2 + "";
            } else {
                tip = "抱歉，评论失败！！！";
                intent = "window.history.back()";
                number = 3 + "";
            }
        }

        return jumpTip(tip, intent, number);
    }

    //文章评论回复

    //用户注册

    /**
     *  友好交互，跳转提示
     * @param tip 跳转信息提示
     * @param intent 跳转地址
     * @param number 跳转等待时间（秒）
     * @return
     */
    public static ModelAndView jumpTip(String tip, String intent, String number) {
        ModelAndView modelAndView = new ModelAndView("jump_tip");
        modelAndView.addObject("tip", tip);
        modelAndView.addObject("intent", intent);
        modelAndView.addObject("number", number);
        return modelAndView;
    }

}
