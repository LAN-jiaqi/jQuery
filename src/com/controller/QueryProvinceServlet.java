package com.controller;

import com.dao.QueryDao;
import com.entity.Province;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author: Shan ♥ Qi
 * @Date: 2021/4/14 21:26
 */
public class QueryProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        //调用dao获取数据
        QueryDao dao = new QueryDao();
        List<Province> provinces = dao.queryProvinceList();

        //把list集合转成json格式的数据，输出给ajax
        if(provinces != null){
            //调用jackson工具库实现List ----> json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(provinces);
        }

        //输出json数据，响应ajax请求，返回数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }
}
