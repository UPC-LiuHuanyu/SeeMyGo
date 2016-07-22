package com.example.lhy.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.lhy.R;


/**
 * @author Serena
 * @time 2016/7/19  21:40
 * @desc ${TODD}
 */
public class BaseFragment extends Fragment implements View.OnClickListener{

    @Override
    public void onClick(View v) {
        getFragmentManager().beginTransaction().remove(this).commit();
        getActivity().findViewById(R.id.button_back).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.iv_edit).setVisibility(View.VISIBLE);
    }
}
