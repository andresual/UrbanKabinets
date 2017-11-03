package com.andresual.dev.urbankabinet.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Activity.BottomNavigationActivity;
import com.andresual.dev.urbankabinet.Adapter.HotItemAdapter;
import com.andresual.dev.urbankabinet.Adapter.ImageListAdapter;
import com.andresual.dev.urbankabinet.Adapter.ImageSliderAdapter;
import com.andresual.dev.urbankabinet.Controller.DashboardController;
import com.andresual.dev.urbankabinet.Controller.SessionManager;
import com.andresual.dev.urbankabinet.Controller.UserController;
import com.andresual.dev.urbankabinet.Model.BannerModel;
import com.andresual.dev.urbankabinet.Model.ProductModel;
import com.andresual.dev.urbankabinet.Model.ReturnModel;
import com.andresual.dev.urbankabinet.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    SessionManager sessionManager;

    ViewPager mViewPager;
    ListView mListView;
    RecyclerView mRecyclerView;
    HotItemAdapter mHotItemAdapter;
    ProductModel productModel = new ProductModel();

    private HomeFragment context = HomeFragment.this;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        sessionManager = new SessionManager(getContext());

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mListView = (ListView) view.findViewById(R.id.lv_hot_kitchen);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.item_list);

        HashMap<String, String> user = sessionManager.getUserDetails();
        String token = user.get(SessionManager.TOKEN_KEY);
        Log.i("token",token);
        UserController.getInstance().getCustomerActive().setToken(token);
        DashboardController.getInstance().fetchDashboard(HomeFragment.this);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new timerTask(), 2000, 4000);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ((BottomNavigationActivity)getActivity()).setActionBarTitle("Home");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void handleFetch(ReturnModel returnModel) {
        Log.e("handle", "status code = " + returnModel.getStatusCode());
        Log.e("handle", "message = " + returnModel.getMessage());
        if (returnModel.getStatusCode() == 1) {
            ImageSliderAdapter adapterView = new ImageSliderAdapter(getActivity());
            mViewPager.setAdapter(adapterView);
            mViewPager.setClickable(true);

            mListView.setAdapter(new ImageListAdapter(getContext()));

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DashboardController.getInstance().getCategoryArrayList().get(position);
                    Toast.makeText(getActivity(), "as" + position, Toast.LENGTH_SHORT).show();

                }
            });

//            mHotItemAdapter = new HotItemAdapter(productModelArrayList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(new HotItemAdapter(getContext()));



            Toast.makeText(getActivity(), returnModel.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), returnModel.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public class timerTask extends TimerTask {

        @Override
        public void run() {
                    if (mViewPager.getCurrentItem() == 0) {
                        mViewPager.setCurrentItem(+1);
                    } else if (mViewPager.getCurrentItem() == 2) {
                        mViewPager.setCurrentItem(-1);
                    } else {
                        mViewPager.setCurrentItem(0);
                    }
                }
    }
}
