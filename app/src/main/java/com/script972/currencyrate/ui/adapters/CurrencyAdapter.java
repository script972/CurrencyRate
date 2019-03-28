package com.script972.currencyrate.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.script972.currencyrate.databinding.ItemCurrencyListBinding;
import com.script972.currencyrate.domain.database.entity.CurrencySelectValue;
import com.script972.currencyrate.ui.model.CurrencySelectValueUi;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {

    private List<CurrencySelectValueUi> data;
    private OnClickListener clicker;

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private ItemCurrencyListBinding binding;

        CurrencyViewHolder(ItemCurrencyListBinding bindingview) {
            super(bindingview.getRoot());
            binding = bindingview;
        }

        private void bind() {
            binding.setCurrency(data.get(getAdapterPosition()));
            binding.setListener(clicker);
            binding.invalidateAll();
        }
    }

    public CurrencyAdapter(List<CurrencySelectValueUi> data, OnClickListener clickListener) {
        this.data = data;
        this.clicker = clickListener;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCurrencyListBinding binding = ItemCurrencyListBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CurrencyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnClickListener {
        void onClickItem(CurrencySelectValueUi currencyResponce);
    }

}
