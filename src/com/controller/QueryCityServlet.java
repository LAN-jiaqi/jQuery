package com.controller;

import com.dao.QueryDao;
import com.entity.City;
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
 * @Date: 2021/4/15 22:47
 */
public class QueryCityServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{}";
        // 获取请求传过来的， 省份id
        String strProviceId =  request.getParameter("proid");
        if( strProviceId != null  && !"".equals(strProviceId.trim())){
            QueryDao dao  =new QueryDao();
            List<City> cityList = dao.queryCityList( Integer.valueOf( strProviceId));
            //把list转为json
            ObjectMapper om = new ObjectMapper();
            json  = om.writeValueAsString(cityList);
        }

        //输出数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
