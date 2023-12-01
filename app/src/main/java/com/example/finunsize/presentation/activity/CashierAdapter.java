package com.example.finunsize.presentation.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.finunsize.R;
import java.util.List;
import persistence.models.CashierModel;

public class CashierAdapter extends RecyclerView.Adapter<CashierAdapter.ViewHolder> {

    private List<CashierModel> cashierList;
    private Context context;

    public CashierAdapter(Context context, List<CashierModel> cashierList) {
        this.context = context;
        this.cashierList = cashierList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caixa_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CashierModel cashier = cashierList.get(position);

        holder.cashierId.setText(cashier.getIdcaixa());
        holder.cashierName.setText(cashier.getNome());
        holder.cashierStatus.setText(cashier.getStatus());

    }

    @Override
    public int getItemCount() {
        return cashierList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cashierId;
        TextView cashierName;
        TextView cashierStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            cashierId = itemView.findViewById(R.id.cashierID);
            cashierName = itemView.findViewById(R.id.cashierName);
            cashierStatus = itemView.findViewById(R.id.cashierStatus);
        }
    }
}
