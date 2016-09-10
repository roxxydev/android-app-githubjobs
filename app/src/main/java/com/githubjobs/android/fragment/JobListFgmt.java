package com.githubjobs.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.githubjobs.android.R;
import com.githubjobs.android.entity.Job;
import com.githubjobs.android.views.JobsListAdapter;

import java.util.ArrayList;

public class JobListFgmt extends Fragment {

    private static final String TAG = JobListFgmt.class.getName();

    private View v;
    private ListView lv;
    private TextView tvMsg;

    private ArrayList<Job> arrJobs = new ArrayList<>();

    public void setJobs(ArrayList<Job> arrJobs) {
        this.arrJobs = arrJobs;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fgmt_joblist, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView) v.findViewById(R.id.fgmt_joblist_lv);

        tvMsg = (TextView) v.findViewById(R.id.fgmt_joblist_tv_message);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        ActionBar ab = appCompatActivity.getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(getActivity().getResources().getString(R.string.fgmt_joblist_ab_title));

        showListviewData(arrJobs);
    }

    // Initialize ListView adapter
    private void showListviewData(ArrayList<Job> arr) {
        if (arr == null || arr.isEmpty()) {
            showTextMessage(true, getActivity().getResources()
                    .getString(R.string.msg_no_result_found));
        } else {
            showTextMessage(false, null);
            JobsListAdapter adapter = new JobsListAdapter(getActivity(), arr);
            lv.setAdapter(adapter);
        }
    }

    // Show text message for no connection or no result found.
    // If text message is to show then it will hide the listview.
    private void showTextMessage(boolean isShow, String msg) {
        int tvMsgVisibility = isShow ? View.VISIBLE : View.GONE;
        int lvVisibility = isShow ? View.GONE : View.VISIBLE;
        tvMsg.setVisibility(tvMsgVisibility);
        lv.setVisibility(lvVisibility);

        if (isShow) {
            tvMsg.setText(msg);
        }
    }
}
