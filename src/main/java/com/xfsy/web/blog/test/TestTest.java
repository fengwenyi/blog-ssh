package com.xfsy.web.blog.test;

import com.xfsy.web.blog.entity.Tag;
import com.xfsy.web.blog.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class : TestTest
 * Desc  : ...
 * Use   : ...
 * Author: xfsyMrFeng
 * Tool  : IntelliJ IDEA
 * Date  : 2017/5/20 0020
 * Time  : 13:02
 */
public class TestTest {

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
        String formatStr =formatter.format(new Date());
        //String pwd = "xfsy" + year + month + day + hour + minute + second;
        System.out.println(formatStr);

        String[] tags = Util.splitStr("spring,mvc", ",");
        Set<Tag> tagSet = new HashSet<Tag>();
        for (int i = 0; i < tags.length; i++) {
            Tag tag = new Tag(tags[i]);

            tagSet.add(tag);
        }
        for (Tag tag : tagSet) {
            System.out.println(tag);
        }
    }
}
