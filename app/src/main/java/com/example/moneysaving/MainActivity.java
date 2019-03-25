package com.example.moneysaving;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button chartBtn;
    private PieChart chart;
    private EditText actual;
    private EditText goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chartBtn = findViewById(R.id.dayBtn);
        actual = findViewById(R.id.goalTv);
        goal = findViewById(R.id.goalTv2);

        chartBtn.setOnClickListener(this);

    }

    public void buildChart(float act, float gl){

        chart = (PieChart) findViewById(R.id.pieChartView);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(7f);
        l.setYOffset(5f);

        float tester = (act * (float) 100.0);

        setTitle("Goal Chart");
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        String test = String.format("%.2f", tester) ;

        chart.setCenterText(test + "%");
        chart.setCenterTextSize(18f);

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(act, "Actual"));
        entries.add(new PieEntry(gl, "Goal"));

        PieDataSet set = new PieDataSet(entries, "Daily Goals");
        PieData data = new PieData(set);

        data.setValueFormatter(new PercentFormatter());
        set.setValueTextSize(12f);
        set.setValueTextColor(Color.WHITE);
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        chart.setData(data);
        chart.animateXY(3000, 3000); // animate horizontal and vertical 3000 milliseconds
        chart.setEntryLabelTextSize(14f);
        chart.invalidate();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.dayBtn:{

                    String value= actual.getText().toString();
                    float actualValue=Float.parseFloat(value);

                    String secondValue= goal.getText().toString();
                    float goalValue= Float.parseFloat(secondValue);

                    float percentActual = (actualValue / goalValue);
                    float percentGoal = ((float) 1.00 - percentActual);

                     Log.e("actual", Float.toString(percentActual));
                     Log.e("goal", Float.toString(percentGoal));

                    buildChart(percentActual, percentGoal);
                break;
            }

        }

    }
}
