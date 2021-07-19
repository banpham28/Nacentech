package com.example.mqtt;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    @SuppressLint("NewApi")
    public static Connection CONN() {

        String _user = "banpham";
        String _pass = "Ban1234qw";
        String _DB = "NacenManager";
        String _server = "192.168.99.106";
        String _port = "1433";


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnURL = "jdbc:jtds:sqlserver://"+ _server + ":"+ _port+ ";"+ "databasename="+ _DB + ";user="
                    +_user+ ";password="+_pass+";instance=SQLEXPRESS;";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se) {
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e) {
            Log.e("ERRO", e.getMessage());
        } catch (Exception e) {
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}
