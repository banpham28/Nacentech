package com.example.nacenmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    Connection connect;
    String ConnectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GetTextFromSQL(View v) {
        TextView txt1= (TextView) findViewById(R.id.textView);
        TextView txt2= (TextView) findViewById(R.id.textView1);
        TextView txt3= (TextView) findViewById(R.id.textView2);

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if (connect!= null)
            {
                String query = "Select * from Device WHERE d_id = '2'";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                while (rs.next())
                {
                    txt1.setText(rs.getString(1));
                    txt2.setText(rs.getString(2));
                    txt3.setText(rs.getString(3));
                }
            }
            else {
                ConnectionResult= "Check Connection";
            }

        }
        catch (Exception ex)
        {

        }
    }


}