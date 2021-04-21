package com.dao;

import com.entity.City;
import com.entity.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Shan ♥ Qi
 * @Date: 2021/4/12 23:15
 */
public class QueryDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String url = "jdbc:mysql://localhost:3306/springdb";
    String username = "root";
    String  pwd = "cgs100001";
    String sql = "";

    public List<Province> queryProvinceList(){ //List<Province>:表示集合里面放的都是Province对象。
        //查询所有的省份信息，因为不止一条，所以用List集合来做数据类型
        List<Province> provinces = new ArrayList<>();
        try {
            Province p = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,pwd);
            sql = "select id,name,jiancheng,shenghui from province order by id";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                //遍历结果集，往集合中添加数据
                p = new Province();  //创建对象，然后赋值
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setJiancheng(rs.getString("jiancheng"));
                p.setShenghui(rs.getString("shenghui"));
                provinces.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return provinces;
    }

    //查询一个省份下面的所有城市
    public List<City> queryCityList(Integer provinceId){
        List<City> cities  = new ArrayList<>();
        try{
            City city = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,pwd);
            sql="select  id, name from city where provinceid = ? ";
            ps = conn.prepareStatement(sql);
            //设置省份的参数值
            ps.setInt(1, provinceId);
            rs = ps.executeQuery();
            while(rs.next()){
                city = new City();
                city.setId( rs.getInt("id"));
                city.setName( rs.getString("name"));
                cities.add(city);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( rs != null){
                    rs.close();
                }
                if( ps != null){
                    ps.close();
                }
                if( conn != null){
                    conn.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return cities;
    }
}
