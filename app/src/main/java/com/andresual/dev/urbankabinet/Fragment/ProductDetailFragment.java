package com.andresual.dev.urbankabinet.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andresual.dev.urbankabinet.Model.ProductModel;
import com.andresual.dev.urbankabinet.R;
import com.bumptech.glide.Glide;

public class ProductDetailFragment extends Fragment {

    ProductModel productModel = new ProductModel();

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_detail2, container, false);

        TextView tvNamaProduk = (TextView) view.findViewById(R.id.tv_detail_nama);
        TextView tvHargaProduk = (TextView) view.findViewById(R.id.tv_detail_harga);
        TextView tvDescProduk = (TextView) view.findViewById(R.id.tv_product_description);
        ImageView ivGambarProduk = (ImageView) view.findViewById(R.id.iv_detail_gambar);

        tvNamaProduk.setText(getActivity().getIntent().getStringExtra("name"));
        tvHargaProduk.setText(getActivity().getIntent().getStringExtra("price"));
        tvDescProduk.setText(Html.fromHtml(getActivity().getIntent().getStringExtra("description")));
//        ivGambarProduk.setImageResource(getActivity().getIntent().getIntExtra("image", 00));
        Glide.with(getActivity())
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
