package com.duyj.work.jdk.jdbc;

import java.sql.*;
import java.util.*;

/*
1.load mysql driver
2.get db connection
3.get statement from connection
4.execute statement
5.close all
 */
public class JDBCTest {

    private Connection connection;

    public static void main(String[] args) {

        JDBCTest test = new JDBCTest();
        test.insert();
        test.update();
        test.delete();

        List<Map<String, Object>> resultList = test.queryForList();

        for (Map m : resultList) {
            System.out.println(m);
        }
    }

    public void getConnect() {

        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }

            // 加载JDBC驱动
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/webapp";

            String user = "root";
            String password = "";

            // 获取数据库连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        PreparedStatement stat = null;
        try {
            if (connection == null || connection.isClosed()) {
                getConnect();
            }
            stat = connection.prepareStatement("insert into tbl_user_t (name, age ,pwd ) values (?,?,?)");

            for (int i = 0; i < 10; i++) {
                stat.setString(1, "robet" + i);
                stat.setInt(2, 9 + i);
                stat.setString(3, "pwd" + i);
                stat.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public List<Map<String, Object>> queryForList() {
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            if (connection == null && connection.isClosed()) {
                getConnect();
            }

            String sql = "select * from tbl_user_t where id != ? and name != ?";

            // 创建Statement对象（每一个Statement为一次数据库执行请求）
            stmt = connection.prepareStatement(sql);

            // 设置传入参数
            stmt.setInt(1, 2);
            stmt.setString(2, "luangeng");

            // 执行SQL语句
            rs = stmt.executeQuery();
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
            // 处理查询结果（将查询结果转换成List<Map>格式）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();

            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < num; i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    map.put(columnName, rs.getString(columnName));
                }
                resultList.add(map);
            }
            return resultList;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭结果集
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                // 关闭执行
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Collections.emptyList();
    }

    public void update() {
        try {
            getConnect();
            PreparedStatement stat = connection.prepareStatement("update tbl_user_t set age = 22 where name = ? ");
            stat.setString(1, "Tommy");
            stat.executeUpdate();
            stat.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        PreparedStatement stat = null;
        try {
            stat = connection.prepareStatement("delete from tbl_user_t where id > 100054");
            stat.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
