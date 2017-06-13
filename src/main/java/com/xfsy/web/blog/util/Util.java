package com.xfsy.web.blog.util;

import com.xfsy.web.blog.entity.Comment;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by Administrator on 2017/2/11.
 * Util 工具
 */
public class Util {

    /**
     * 获取客户端IP地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if(ip.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inetAddress.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ip != null && ip.length() > 15){
            if(ip.indexOf(",")>0){
                ip = ip.substring(0,ip.indexOf(","));
            }
        }
        return ip;
    }


    /**
     * 获取地址
     * @param params
     * @param encoding
     * @return
     * @throws Exception
     */
    public String getAddress(String params, String encoding) throws Exception{

        String path = "http://ip.taobao.com/service/getIpInfo.php";

        String returnStr = this.getRs(path, params, encoding);

        JSONObject json=null;

        if(returnStr != null){

            json = new JSONObject(returnStr);

            if("0".equals(json.get("code").toString())){

                StringBuffer buffer = new StringBuffer();

                buffer.append(decodeUnicode(json.optJSONObject("data").getString("country")));//国家

                buffer.append(decodeUnicode(json.optJSONObject("data").getString("area")));//地区

                buffer.append(decodeUnicode(json.optJSONObject("data").getString("region")));//省份

                buffer.append(decodeUnicode(json.optJSONObject("data").getString("city")));//市区

                buffer.append(decodeUnicode(json.optJSONObject("data").getString("county")));//地区

                buffer.append(decodeUnicode(json.optJSONObject("data").getString("isp")));//ISP公司

                return buffer.toString();

            }else{

                return "0";

            }

        }

        return null;

    }
    /**
     * 从url获取结果
     * @param path
     * @param params
     * @param encoding
     * @return
     */
    public String getRs(String path, String params, String encoding){

        URL url = null;

        HttpURLConnection connection = null;

        try {

            url = new URL(path);

            connection = (HttpURLConnection)url.openConnection();// 新建连接实例

            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒

            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒

            connection.setDoInput(true);// 是否打开输出流 true|false

            connection.setDoOutput(true);// 是否打开输入流true|false

            connection.setRequestMethod("POST");// 提交方法POST|GET

            connection.setUseCaches(false);// 是否缓存true|false

            connection.connect();// 打开连接端口

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            out.writeBytes(params);

            out.flush();

            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),encoding));

            StringBuffer buffer = new StringBuffer();

            String line = "";

            while ((line = reader.readLine())!= null) {

                buffer.append(line);

            }

            reader.close();

            return buffer.toString();

        } catch (Exception e) {

            e.printStackTrace();

        }finally{

            connection.disconnect();// 关闭连接

        }

        return null;
    }
    /**
     * 字符转码
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString){

        char aChar;

        int len = theString.length();

        StringBuffer buffer = new StringBuffer(len);

        for (int i = 0; i < len;) {

            aChar = theString.charAt(i++);

            if(aChar == '\\'){

                aChar = theString.charAt(i++);

                if(aChar == 'u'){

                    int val = 0;

                    for(int j = 0; j < 4; j++){

                        aChar = theString.charAt(i++);

                        switch (aChar) {

                            case '0':

                            case '1':

                            case '2':

                            case '3':

                            case '4':

                            case '5':

                            case '6':

                            case '7':

                            case '8':

                            case '9':

                                val = (val << 4) + aChar - '0';

                                break;

                            case 'a':

                            case 'b':

                            case 'c':

                            case 'd':

                            case 'e':

                            case 'f':

                                val = (val << 4) + 10 + aChar - 'a';

                                break;

                            case 'A':

                            case 'B':

                            case 'C':

                            case 'D':

                            case 'E':

                            case 'F':

                                val = (val << 4) + 10 + aChar - 'A';

                                break;

                            default:

                                throw new IllegalArgumentException(

                                        "Malformed      encoding.");
                        }

                    }

                    buffer.append((char) val);

                }else{

                    if(aChar == 't'){

                        aChar = '\t';
                    }

                    if(aChar == 'r'){

                        aChar = '\r';
                    }

                    if(aChar == 'n'){

                        aChar = '\n';
                    }

                    if(aChar == 'f'){

                        aChar = '\f';

                    }

                    buffer.append(aChar);
                }

            }else{

                buffer.append(aChar);

            }

        }

        return buffer.toString();

    }

    public static String[] splitStr(String str, String split) {
        return str.split(split);
    }

    /**
     * 评论 按时间排序处理
     * @param set
     * @return
     */
    public static Set<Comment> sortCommentByTime(Set<Comment> set){
        List<Comment> setList= new ArrayList<Comment>(set);
        Collections.sort(setList, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                // TODO Auto-generated method stub
                if (o1.getTime() < o2.getTime()) {
                    return 1;
                } else if (o1.getTime() > o2.getTime()) {
                    return -1;
                } else {
                    return o1.toString().compareTo(o2.toString());
                }
            }

        });
        set = new LinkedHashSet<Comment>(setList);
        return set;
    }
}
