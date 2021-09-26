package com.demo.utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
@Deprecated
public class JdbcUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    static {

        try {
            Properties properties = new Properties();
            InputStream stream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(stream);
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = conns.get();
        if(conn==null){
            try {
                conn=dataSource.getConnection();
                conns.set(conn);//保存到threadlocal对象中，jdbc使用
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并释放资源
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if(connection!=null){

            try {
                connection.commit();//提交事务

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    /**
     * 事务回滚
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if(connection!=null){

            try {
                connection.rollback();//回滚事务

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
   /* public static void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
