package com.script972.currencyrate.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.script972.currencyrate.R;
import com.script972.currencyrate.ui.model.CurrencyValueModel;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ListValueAdapter extends RecyclerView.Adapter<ListValueAdapter.CurrencyViewHolder> {

    private List<CurrencyValueModel> data;

    /**
     * View holder class
     */
    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNumber;
        private TextView txtCurrency;

        CurrencyViewHolder(View view) {
            super(view);
            txtNumber = view.findViewById(R.id.txt_number);
            txtCurrency = view.findViewById(R.id.txt_currency);
        }
    }

    public ListValueAdapter(List<CurrencyValueModel> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, final int position) {
        CurrencyValueModel c = data.get(position);
        holder.txtNumber.setText(String.valueOf(c.getRate()));
        holder.txtCurrency.setText(c.getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_currency_list_value, parent, false);
        return new CurrencyViewHolder(v);
    }

}
