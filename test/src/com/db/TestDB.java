package com.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.db.DBUtil;

public class TestDB {
    
    @Test
    public void test() {
        
        String string = "2012-05-06";
        String sql = "select count(*) from iacmain where inputdate > '" + string + "'";
        ResultSet rs = null;
        Connection  conn = DBUtil.getConn();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        int i = 0;
        try {
            while(rs.next()){
                i = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
    
}
