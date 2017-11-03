package com.andresual.dev.urbankabinet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Activity.HotItemDetails;
import com.andresual.dev.urbankabinet.Activity.ProductDetailActivity;
import com.andresual.dev.urbankabinet.Controller.DashboardController;
import com.andresual.dev.urbankabinet.Model.ProductModel;
import com.andresual.dev.urbankabinet.R;
import com.bumptech.glide.Glide;


/**
 * Created by andresual on 10/27/2017.
 */

public class HotItemAdapter extends RecyclerView.Adapter<HotItemAdapter.ViewHolder> {

    private Context mContext;

    public HotItemAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivHotItem;
        Context ctx;

        public ViewHolder(View view, Context ctx) {
            super(view);
            view.setOnClickListener(this);
            ivHotItem = (ImageView) view.findViewById(R.id.iv_hot_item);
            this.ctx = ctx;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ProductModel productModel = DashboardController.getInstance().getProductModelArrayList().get(position);
            Intent intent = new Intent(this.ctx, ProductDetailActivity.class);
            intent.putExtra("name", productModel.getNameProduct());
            intent.putExtra("price", productModel.getPriceProduct());
            intent.putExtra("description", productModel.getDescriptionProduct());
            intent.putExtra("image", productModel.getImageThumbnail());
            this.ctx.startActivity(intent);
        }
    }

    @Override
    public HotItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_horizontal_listing, parent, false);
        return new ViewHolder(itemView, mContext);
    }

    @Override
    public void onBindViewHolder(HotItemAdapter.ViewHolder holder, int position) {
        ProductModel productModel = DashboardController.getInstance().getProductModelArrayList().get(position);
        holder.ivHotItem.setImageBitmap(null);
        Glide.with(holder.ivHotItem.getContext())
                .load(productModel.getImageThumbnail())
                .into(holder.ivHotItem);
    }

    @Override
    public int getItemCount() {
        return DashboardController.getInstance().getProductModelArrayList().size();
    }
}

