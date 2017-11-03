package com.andresual.dev.urbankabinet.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andresual.dev.urbankabinet.Controller.DashboardController;
import com.andresual.dev.urbankabinet.Model.BannerModel;
import com.andresual.dev.urbankabinet.R;
import com.bumptech.glide.Glide;

/**
 * Created by andresual on 10/17/2017.
 */

public class ImageSliderAdapter extends PagerAdapter {
    Context mContext;

    public ImageSliderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return DashboardController.getInstance().getBannerModelArrayList().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        BannerModel bannerModel = DashboardController.getInstance().getBannerModelArrayList().get(position);
        Glide.with(mContext)
                .load(bannerModel.getImagepath())
                .into(mImageView);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
