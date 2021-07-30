package com.example.nacenmanager;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Log;
import android.os.StrictMode;
import android.nfc.Tag;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionHelper {

    String ip, database, uname, pass,port;
    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        ip = "192.168.99.107";
        database   = "NacenManager";
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
