package com.script972.currencyrate.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.script972.currencyrate.R;
import com.script972.currencyrate.ui.activities.DetailsActivity;
import com.script972.currencyrate.ui.adapters.ItemOffsetDecoration;
import com.script972.currencyrate.ui.adapters.ListValueAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListFragment extends Fragment {

    private RecyclerView rvCurrency;
    private ListValueAdapter adapter;

    private static ListFragment fragment;

    public ListFragment() {
    }

    public static ListFragment getInstance() {
        if (fragment == null) {
            fragment = new ListFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCurrency = view.findViewById(R.id.rv_currency);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(RecyclerView.VERTICAL);
        rvCurrency.setLayoutManager(llm);
        rvCurrency.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListValueAdapter(((DetailsActivity) getActivity()).getDataCurrencyList());
        rvCurrency.setAdapter(adapter);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getContext(), R.dimen.dimen_8);
        this.rvCurrency.addItemDecoration(itemDecoration);
        adapter.notifyDataSetChanged();
    }

    public void updateList() {
        adapter.notifyDataSetChanged();
    }
}