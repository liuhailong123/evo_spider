package cn.com.evo.cms.web.dataDispose.dataMoveApi;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import cn.com.evo.cms.constant.Constant;

/**
 * 获取cms_new_db 数据库连接
 * @author shilinxiao
 *
 */
public class Conn {
	public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = Constant.JDBC_URL;
        String username = Constant.JDBC_NAME;
        String password = Constant.JDBC_PASSWORD;
        Connection conn = null;
        try {
            Class.forName(driver); // classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
	}

}
