package com.script972.currencyrate.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.script972.currencyrate.R;
import com.script972.currencyrate.ui.activities.DetailsActivity;
import com.script972.currencyrate.ui.model.CurrencyValueModel;
import com.script972.currencyrate.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChartFragment extends Fragment {

    private GraphView graph;
    private static ChartFragment fragment;
    private List<CurrencyValueModel> data;


    public ChartFragment() {
    }

    public static ChartFragment getInstance() {
        if (fragment == null) {
            fragment = new ChartFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        graph = view.findViewById(R.id.graph);
        //graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space


        //graph.getViewport().setMinX(d1.getTime());
        //graph.getViewport().setMaxX(d3.getTime());
        //graph.getViewport().setXAxisBoundsManual(true);
    }

    public void updateList() {
       /* data = ((DetailsActivity) getActivity()).getDataCurrencyList();
        DataPoint[] dataPoints = new DataPoint[data.size()];
        for (int i = 0; i < data.size(); i++) {
            dataPoints[i] = new DataPoint(DateUtils.chartValueFromStr(data.get(i).getDate()), data.get(i).getRate());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graph.addSeries(series);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity(), DateUtils.getChartDataFormat()));*/
    }
}
