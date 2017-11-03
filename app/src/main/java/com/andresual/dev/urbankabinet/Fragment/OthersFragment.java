package com.andresual.dev.urbankabinet.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andresual.dev.urbankabinet.Activity.BottomNavigationActivity;
import com.andresual.dev.urbankabinet.Activity.MainActivity;
import com.andresual.dev.urbankabinet.Controller.SessionManager;
import com.andresual.dev.urbankabinet.Controller.UserController;
import com.andresual.dev.urbankabinet.R;
import com.facebook.login.LoginManager;

import java.util.HashMap;


public class OthersFragment extends Fragment {

    Button btnLogout, btnLogoutApi;
    SessionManager sessionManager;
    TextView tvUserToken;

    public static OthersFragment newInstance() {
        OthersFragment othersFragment = new OthersFragment();
        return othersFragment;
    }

    public OthersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_others, container, false);

        sessionManager = new SessionManager(getContext());


//        Toast.makeText(getActivity(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();
//        sessionManager.checkLogin();

        btnLogout = (Button) view.findViewById(R.id.btn_logout);
        btnLogoutApi = (Button) view.findViewById(R.id.btn_logout_api);
        tvUserToken = (TextView) view.findViewById(R.id.text_token);


//        Toast.makeText(getContext(), "User Login Status: " + sessionManager.isLoggedIn(), Toast.LENGTH_LONG).show();
//        sessionManager.checkLogin();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(getActivity(), MainActivity.class);
                startActivity(login);
                getActivity().finish();

//                sessionManager.logoutUser();
            }
        });

        btnLogoutApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.logoutUser();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ((BottomNavigationActivity)getActivity()).setActionBarTitle("Others");
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }

}