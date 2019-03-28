package com.script972.currencyrate.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.script972.currencyrate.databinding.ItemCurrencyListValueBinding;
import com.script972.currencyrate.ui.model.CurrencyValueModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListValueAdapter extends RecyclerView.Adapter<ListValueAdapter.CurrencyViewHolder> {

    private List<CurrencyValueModel> data;


    class CurrencyViewHolder extends RecyclerView.ViewHolder {

        private ItemCurrencyListValueBinding binding;

        CurrencyViewHolder(ItemCurrencyListValueBinding bindingView) {
            super(bindingView.getRoot());
            binding = bindingView;
        }

        private void bind() {
            binding.setCurrency(data.get(getAdapterPosition()));
            binding.invalidateAll();
        }
    }

    public ListValueAdapter(List<CurrencyValueModel> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, final int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCurrencyListValueBinding binding = ItemCurrencyListValueBinding.inflate(
                LayoutInflater.from(parent.getContext()));
        return new CurrencyViewHolder(binding);
    }

}
