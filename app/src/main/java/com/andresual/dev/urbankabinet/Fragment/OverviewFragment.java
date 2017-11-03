package com.andresual.dev.urbankabinet.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andresual.dev.urbankabinet.Activity.HotItemDetails;
import com.andresual.dev.urbankabinet.Adapter.HotItemAdapter;
import com.andresual.dev.urbankabinet.Controller.DashboardController;
import com.andresual.dev.urbankabinet.Model.ProductModel;
import com.andresual.dev.urbankabinet.R;
import com.bumptech.glide.Glide;

public class OverviewFragment extends Fragment {

    ProductModel productModel = new ProductModel();

    public OverviewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_overview, container, false);

        TextView tvNamaProduk = (TextView) view.findViewById(R.id.tv_detail_nama_produk);
        TextView tvHargaProduk = (TextView) view.findViewById(R.id.tv_detail_harga_produk);
        ImageView ivGambarProduk = (ImageView) view.findViewById(R.id.iv_detail_gambar_produk);

        tvNamaProduk.setText(getActivity().getIntent().getStringExtra("name"));
        tvHargaProduk.setText(getActivity().getIntent().getStringExtra("price"));
        ivGambarProduk.setImageResource(getActivity().getIntent().getIntExtra("image", 00));

        Glide.with(ivGambarProduk.getContext())
                .load(productModel.getImageThumbnail())
                .into(ivGambarProduk);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
