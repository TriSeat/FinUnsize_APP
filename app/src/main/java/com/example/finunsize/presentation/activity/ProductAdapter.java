package com.example.finunsize.presentation.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finunsize.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

import persistence.models.ProductModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<ProductModel> productList;
    private Context context;

    public ProductAdapter(Context context, List<ProductModel> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductModel product = productList.get(position);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Glide.with(context).load(product.getUrl_image()).into(holder.productImage);
        holder.validityText.setText(product.getValidade().format(formatter));
        holder.productName.setText(product.getNome());
        holder.creationText.setText(product.getData_cadastro().format(formatter));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView validityText;
        TextView productName;
        TextView creationText;
        ImageButton descriptionButton;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            validityText = itemView.findViewById(R.id.validityText);
            productName = itemView.findViewById(R.id.productName);
            creationText = itemView.findViewById(R.id.creationText);
            descriptionButton = itemView.findViewById(R.id.descriptionButton);
        }
    }
}
