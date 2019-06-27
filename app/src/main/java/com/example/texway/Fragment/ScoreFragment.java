package com.example.texway.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.texway.DAO.Product;
import com.example.texway.R;
import com.example.texway.Util;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreFragment extends Fragment {

    private PieChart mChart;
    private String[] xData = { "Excellent", "Bon", "Mediocre","Mauvais"};

    public ScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);

        mChart = rootView.findViewById(R.id.piechart);

        // configure pie chart
        mChart.setUsePercentValues(true);
        mChart.setDescription(new Description());
        mChart.animateXY(500, 500);

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        //mChart.setHoleColorTransparent(true);
        mChart.setHoleRadius(7);
        mChart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);
        mChart.getDescription().setEnabled(false);
        // add data
        addData();

        // customize legends
        Legend l = mChart.getLegend();

        l.setEnabled(true);

        //l.se(LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);


        return rootView;
    }

    private void createChart()
    {

    }

    private void addData() {

        Product p = new Product();

        p.open(this.getActivity());
        List<Product> plist = p.getAll();

        HashMap<String,List<Integer>> sorted =  Util.sortScoreByQuality(plist);

        List<Integer> excellent =   sorted.get("Excellent");
        List<Integer> bon =   sorted.get("Bon");
        List<Integer> mediocre =   sorted.get("Médiocre");
        List<Integer> mauvais =   sorted.get("Mauvais");
        List<Integer> tous =   sorted.get("Tous");

        int sommeExc = 0;
        int sommeBon = 0;
        int sommeMediocre = 0;
        int sommeMauvais = 0;
        int sommeTous = 0;
        float moyExc = 0f;
        float moyBon = 0f;
        float moyMediocre = 0f;
        float moyMauvais = 0f;
        float moyTous = 0f;

        for (int i = 0; i < excellent.size(); i++ ){
            sommeExc += excellent.get(i);
        }
        if (excellent.size()!= 0)        moyExc = sommeExc / excellent.size();

        for (int i = 0; i < bon.size(); i++ ){
            sommeBon += bon.get(i);
        }

        if (bon.size()!= 0)        moyBon = sommeBon / bon.size();

        for (int i = 0; i < mauvais.size(); i++ ){
            sommeMauvais += mauvais.get(i);
        }
        if (mauvais.size()!= 0)        moyMauvais = sommeMauvais / mauvais.size();

        for (int i = 0; i < mediocre.size(); i++ ){
            sommeMediocre += mediocre.get(i);
        }
        if (mediocre.size()!= 0)        moyMediocre = sommeMediocre / mediocre.size();

        float[] yData = { moyExc, moyBon, moyMediocre, moyMauvais };

        List<PieEntry> yVals1 = new ArrayList<PieEntry>();

        for (int i = 0; i < yData.length; i++)
            yVals1.add(new PieEntry(yData[i], xData[i]));


        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1,"");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }


}
