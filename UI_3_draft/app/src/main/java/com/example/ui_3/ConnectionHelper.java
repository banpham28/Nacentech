package com.example.ui_3;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    String ip, database, uname, pass,port;
    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        ip = "192.168.99.106";
        database   = "SCADA";
        uname = "banpham";
        pass = "Ban1234qw";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection   = null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+ ";"+ "databasename="+ database + ";user="
                    +uname+ ";password="+pass+";instance=SQLEXPRESS;";
            connection= DriverManager.getConnection(ConnectionURL);

        }
        catch (Exception ex) {
            Log.e("Error ", ex.getMessage());

        }
        return connection;
    }

}
