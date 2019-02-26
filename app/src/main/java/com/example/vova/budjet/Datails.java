package com.example.vova.budjet;

import android.content.Intent;
import android.graphics.Color;
import android.media.audiofx.AudioEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.vova.budjet.classes.Info;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;


public class Datails extends AppCompatActivity {

    TextView money,date,dis,category;

    PieChart mPieChart;
    float data[] = new float[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datails);


        Intent intent = getIntent();
        Info info = (Info) intent.getExtras().getSerializable("Info");
        data[0]=Float.parseFloat(info.getMoneyChange());
        data[1]=300;

        money = findViewById(R.id.mon);
        date = findViewById(R.id.dt);
        dis = findViewById(R.id.des);
        category = findViewById(R.id.category);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        money.setText(info.getMoneyChange());
        date.setText(df.format("yy.MM.dd", new java.util.Date()));
        dis.setText(info.getShortDescribe());
        category.setText(info.getChoise());


        mPieChart = findViewById(R.id.pie_chart);
        mPieChart.setHoleRadius(40);
        mPieChart.setTransparentCircleAlpha(2);
        addDataSet();

    }

    private void addDataSet() {
        ArrayList<PieEntry> yData = new ArrayList<>();

        for(int i = 0;i< data.length;i++){
            yData.add(new PieEntry(data[i],i));
        }

        PieDataSet pieDataSet = new PieDataSet(yData,"");
        pieDataSet.setSliceSpace(1);

        ArrayList<Integer> color= new ArrayList<>();
        color.add(Color.CYAN);
        color.add(Color.MAGENTA);
        pieDataSet.setColors(color);
        pieDataSet.setValueTextSize(15);
        Description description = new Description();
        description.setText("");
        mPieChart.setDescription(description);


        PieData pieData = new PieData(pieDataSet);
        mPieChart.setData(pieData);
        mPieChart.invalidate();
    }
}
