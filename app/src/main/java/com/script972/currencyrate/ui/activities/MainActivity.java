package com.script972.currencyrate.ui.activities;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.script972.currencyrate.R;
import com.script972.currencyrate.api.model.CurrencyResponce;
import com.script972.currencyrate.databinding.ActivityMainBinding;
import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.utils.ActivityUtils;
import com.script972.currencyrate.utils.DateUtils;
import com.script972.currencyrate.utils.SharedPreferencesUtils;
import com.script972.currencyrate.managers.ConnectivitySelfManager;
import com.script972.currencyrate.ui.adapters.CurrencyAdapter;
import com.script972.currencyrate.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity {

    private CurrencyAdapter adapter;

    private MainViewModel viewModel;

    private final List<CurrencySelectValue> list = new ArrayList<>();

    /**
     * Immutable data
     */
    private final List<CurrencySelectValue> basicData = new ArrayList<>();

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        this.viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        initView();
        this.viewModel.loadCurrency();
        loadCurrency();

    }

    private void initView() {
        initToolbar();
        initInetStatus();
        this.binding.edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.filterData(s.toString());
            }
        });
        this.adapter = new CurrencyAdapter(this.list, currencyModel ->
                ActivityUtils.startDetailsActivity(MainActivity.this, currencyModel));
        this.binding.rvCurrency.setAdapter(this.adapter);
        LinearLayoutManager llm = new GridLayoutManager(this.getApplicationContext(), 3);
        llm.setOrientation(RecyclerView.VERTICAL);
        this.binding.rvCurrency.setLayoutManager(llm);
        this.binding.rvCurrency.setItemAnimator(new DefaultItemAnimator());
        this.adapter.notifyDataSetChanged();
    }

    /**
     * Show status internet
     */
    private void initInetStatus() {
        if (!new ConnectivitySelfManager(this).isNetworkAvailable()) {
            this.binding.txtLastUpdate.setVisibility(View.VISIBLE);
            long lastUpdateValue = SharedPreferencesUtils.getLastSyncDate(this);
            String outStr = lastUpdateValue != -1 ? getResources().getString(R.string.no_internet) +
                    " " + DateUtils.soutDateWithTime(lastUpdateValue) :
                    getResources().getString(R.string.swith_on_internet);
            this.binding.txtLastUpdate.setText(outStr);
        } else {
            this.binding.txtLastUpdate.setVisibility(View.GONE);
        }

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.label_main_activity));
    }

    public void loadCurrency() {
        viewModel.getDataForList().observe(this, this::handleResponse);
    }

    private void handleResponse(List<CurrencySelectValue> currencyResponceList) {
        this.list.clear();
        this.list.addAll(currencyResponceList);
        this.basicData.addAll(currencyResponceList);
        this.adapter.notifyDataSetChanged();
    }

}
