package com.example.nacenmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Connection connect;
    String ConnectionResult = "";
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Intent intent = new Intent(MainActivity.this, LineChartActivity.class);
//        startActivity(intent);
//        finish();

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

//    public void GetTextFromSQL(View v) {
//        TextView txt1= (TextView) findViewById(R.id.textView);
//        TextView txt2= (TextView) findViewById(R.id.textView1);
//        TextView txt3= (TextView) findViewById(R.id.textView2);
//
//        try {
//            ConnectionHelper connectionHelper = new ConnectionHelper();
//            connect = connectionHelper.connectionclass();
//            if (connect!= null)
//            {
//                String query = "Select * from Device WHERE d_id = '2'";
//                Statement st = connect.createStatement();
//                ResultSet rs = st.executeQuery(query);
//
//                while (rs.next())
//                {
//                    txt1.setText(rs.getString(1));
//                    txt2.setText(rs.getString(2));
//                    txt3.setText(rs.getString(3));
//                }
//            }
//            else {
//                ConnectionResult= "Check Connection";
//            }
//
//        }
//        catch (Exception ex)
//        {
//
//        }
//    }

    private ArrayList<Entry> lineChartDataSet() {
        ArrayList<Entry> dataSet = new ArrayList<Entry>();

        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();


            dataSet.add(new Entry(0,40));
            dataSet.add(new Entry(1,10));
            dataSet.add(new Entry(2,15));
            dataSet.add(new Entry(3,12));
            dataSet.add(new Entry(4,20));
            dataSet.add(new Entry(5,50));
            dataSet.add(new Entry(6,23));
            dataSet.add(new Entry(7,34));
            dataSet.add(new Entry(8,12));

//            String query = "Select value from RawData where name='Lamp0-SC'";
//            Statement st = connect.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            for(int i =0; i<10; i++)
//                    {
//                        float val = Float.parseFloat(rs.getString(1));
//                        dataSet.add(new Entry(i,val));
//                    }

//            if (connect!= null)
//            {
//                String query = "Select value from RawData where name='Lamp0-SC'";
//                Statement st = connect.createStatement();
//                ResultSet rs = st.executeQuery(query);
//
//                while (rs.next())
//                {
//
//                    for(int i =0; i<10; i++)
//                    {
//                        float val = Float.parseFloat(rs.getString(1));
//                        dataSet.add(new Entry(i,val));
//                    }
//                }
//            }
//            else {
//                ConnectionResult= "Check Connection";
//            }

        }
        catch (Exception ex)
        {

        }

//        dataSet.add(new Entry(0,40));
//        dataSet.add(new Entry(1,10));
//        dataSet.add(new Entry(2,15));
//        dataSet.add(new Entry(3,12));
//        dataSet.add(new Entry(4,20));
//        dataSet.add(new Entry(5,50));
//        dataSet.add(new Entry(6,23));
//        dataSet.add(new Entry(7,34));
//        dataSet.add(new Entry(8,12));
//        return dataSet;
        return dataSet;
    }

}