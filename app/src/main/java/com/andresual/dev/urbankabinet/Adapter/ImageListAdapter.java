package com.andresual.dev.urbankabinet.Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.andresual.dev.urbankabinet.Controller.DashboardController;
import com.andresual.dev.urbankabinet.Fragment.HomeFragment;
import com.andresual.dev.urbankabinet.R;
import com.bumptech.glide.Glide;

import static com.andresual.dev.urbankabinet.R.id.bottom;

/**
 * Created by andresual on 10/26/2017.
 */

public class ImageListAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    public ImageListAdapter(Context context) {
        super(context, R.layout.list_item);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return DashboardController.getInstance().getCategoryArrayList().size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        Glide.with(context)
                .load(DashboardController.getInstance().getCategoryArrayList().get(position))
                .into((ImageView) convertView);

        return convertView;
    }
}
