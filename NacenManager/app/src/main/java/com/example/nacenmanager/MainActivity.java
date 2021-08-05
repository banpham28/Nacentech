package com.example.nacenmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Connection connect;
    String ConnectionResult = "";
    LineChart lineChart;
    Float[] list = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lineChart = findViewById(R.id.lineChart);
        LineDataSet lineDataSet = new LineDataSet(lineChartDataSet(),"data set");
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(lineDataSet);

        LineData lineData = new LineData(iLineDataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();

        lineChart.setNoDataText("Data not Available");

        //Customize Chart (Optional)
//        lineDataSet.setColor(Color.BLUE);
//        lineDataSet.setCircleColor(Color.GREEN);
//        lineDataSet.setDrawCircles(true);
//        lineDataSet.setDrawCircleHole(true);
//        lineDataSet.setLineWidth(5);
//        lineDataSet.setCircleRadius(10);
//        lineDataSet.setValueTextSize(10);
//        lineDataSet.setValueTextColor(Color.BLACK);
    }


     private ArrayList<Entry> lineChartDataSet() {
        ArrayList<Entry> dataSet = new ArrayList<Entry>();
        try {

            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();


            if (connect!= null)
            {
                ConnectionResult= "Success!";
                Log.d("success", "Connected");
                String query = "Select top 10 value from RawData where name='Lamp0-SC'";
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);


                ArrayList<Float> val = new ArrayList<Float>();
                while (rs.next())
                {
                    val.add(rs.getFloat(1));

                }
                Float[] valFloat = new Float[val.size()];
                valFloat = val.toArray(valFloat);
                for (int i = 0; i < 10; i++) {
                    dataSet.add(new Entry(i,valFloat[i] ));
                }
            }
            else {
                ConnectionResult= "Check Connection";
                Log.d("fail", "Connection Failed!");
            }
        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return dataSet;
    }

}