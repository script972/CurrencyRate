package com.script972.currencyrate.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.script972.currencyrate.R;
import com.script972.currencyrate.databinding.ActivityDescriptionBinding;
import com.script972.currencyrate.ui.model.CurrencyValueModel;
import com.script972.currencyrate.utils.ActivityUtils;
import com.script972.currencyrate.utils.DateUtils;
import com.script972.currencyrate.utils.DialogUtils;
import com.script972.currencyrate.ui.adapters.ViewPagerAdapter;
import com.script972.currencyrate.ui.fragments.ChartFragment;
import com.script972.currencyrate.ui.fragments.ListFragment;
import com.script972.currencyrate.viewmodels.DetailsViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailsActivity extends BaseActivity implements View.OnClickListener {

    private Calendar startDate = Calendar.getInstance();
    private Calendar endDate = Calendar.getInstance();

    private DetailsViewModel viewModel;
    private ViewPagerAdapter adapter;

    private ListFragment listFragment;
    private ChartFragment chartFragment;

    private final List<CurrencyValueModel> list = new ArrayList<>();

    private String currency;

    private ActivityDescriptionBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_description);
        this.viewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        parseIntent();
        initView();
        initViewModel();
    }


    private void initViewModel() {
        viewModel.getCurrencyValueLiveData().observe(this, this::handleResponse);
    }

    private void handleResponse(List<CurrencyValueModel> currencyModelList) {
        list.clear();
        list.addAll(currencyModelList);
        adapter.notifyDataSetChanged();
        listFragment.updateList();
        chartFragment.updateList(list);
    }

    private void parseIntent() {
        this.currency = getIntent().getStringExtra(ActivityUtils.EXTRA_CURRENCY);
    }

    private void initView() {
        initToolbar();
        this.binding.txtStartDate.setOnClickListener(this);
        this.binding.txtEndDate.setOnClickListener(this);
        setupViewPager(binding.viewPager);
        this.binding.bottomNavigationView.setOnNavigationItemSelectedListener(bottomMenuListener);
        this.binding.btnWeak.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            endDate = Calendar.getInstance();
            calendar.add(Calendar.WEEK_OF_MONTH, -1);
            startDate = calendar;
            refreshDateRange();
        });
        this.binding.btnMonth.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            endDate = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -1);
            startDate = calendar;
            refreshDateRange();
        });
        this.binding.btnYear.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            endDate = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -1);
            startDate = calendar;
            refreshDateRange();
        });

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(currency.toUpperCase() + " " + getResources().getString(R.string.label_statistics));
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(v -> DetailsActivity.super.onBackPressed());
    }

    private void setupViewPager(ViewPager viewPager) {
        List<Fragment> fragments = new ArrayList<>();
        this.chartFragment = ChartFragment.getInstance();
        this.listFragment = ListFragment.getInstance();
        fragments.add(this.listFragment);
        fragments.add(this.chartFragment);
        this.adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(adapter);
    }

    BottomNavigationView.OnNavigationItemSelectedListener bottomMenuListener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.bottom_navigation_item_progress:
                binding.viewPager.setCurrentItem(0);
                break;
            case R.id.bottom_navigation_item_logs:
                binding.viewPager.setCurrentItem(1);
                break;
            default:
                return false;
        }
        return true;
    };

    @NonNull
    public List<CurrencyValueModel> getDataCurrencyList() {
        return list;
    }

    private void refreshDateRange() {
        this.binding.txtStartDate.setText(getResources().getString(R.string.start_date) + " "
                + DateUtils.soutDate(startDate.getTimeInMillis()));
        this.binding.txtEndDate.setText(getResources().getString(R.string.end_date) + " "
                + DateUtils.soutDate(endDate.getTimeInMillis()));
        if (!this.startDate.before(this.endDate)) {
            Toast.makeText(this, getResources().getString(R.string.toast_not_valid_date_value), Toast.LENGTH_LONG).show();
            return;
        }
        this.viewModel.rangeDateUpdate(this.startDate, this.endDate, this.currency);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_start_date:
                DialogUtils.showDateDialog(this, startDate, value -> {
                    this.startDate = value;
                    refreshDateRange();
                });
                break;
            case R.id.txt_end_date:
                DialogUtils.showDateDialog(this, this.endDate, value -> {
                    this.endDate = value;
                    refreshDateRange();
                });
                break;
        }
    }


}
