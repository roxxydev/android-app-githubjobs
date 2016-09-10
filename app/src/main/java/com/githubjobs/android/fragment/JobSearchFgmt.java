package com.githubjobs.android.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.githubjobs.android.MainActivity;
import com.githubjobs.android.views.ViewUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.githubjobs.android.R;
import com.githubjobs.android.entity.Job;
import com.githubjobs.android.network.HttpManager;
import com.githubjobs.android.network.RequestCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JobSearchFgmt extends Fragment {

    private static final String TAG = JobListFgmt.class.getName();

    private View v;
    private EditText etTitle, etCompany, etLocation;
    private AppCompatCheckBox cbFulltime, cbParttime;
    private Button btnSearch;
    private ProgressDialog pgDlg;

    private HttpManager httpMngr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        httpMngr = HttpManager.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fgmt_jobsearch, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etTitle = (EditText) v.findViewById(R.id.fgmt_jobsearch_et_title);
        etCompany = (EditText) v.findViewById(R.id.fgmt_jobsearch_et_company);
        etLocation = (EditText) v.findViewById(R.id.fgmt_jobsearch_et_location);
        cbFulltime = (AppCompatCheckBox) v.findViewById(R.id.fgmt_jobsearch_cb_fulltime);
        cbParttime = (AppCompatCheckBox) v.findViewById(R.id.fgmt_jobsearch_cb_parttime);
        btnSearch = (Button) v.findViewById(R.id.fgmt_joblist_btn_search);

        pgDlg = new ProgressDialog(getActivity());
        pgDlg.setMessage(getActivity().getResources()
                .getString(R.string.progress_msg_jobsearch));

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        ActionBar ab = appCompatActivity.getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setTitle(getActivity().getResources().getString(R.string.app_name));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (httpMngr != null) {
            httpMngr.getRequestQueue().cancelAll(TAG);
        }
    }

    // Initialize views and listeners
    private void initViews() {
        View.OnClickListener btnSearchClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewUtil.hideKeybord(view, getActivity());

                String jobTitle = etTitle.getText().toString();
                String jobCompanyName = etCompany.getText().toString();
                String jobLocation = etLocation.getText().toString();
                boolean isFulltime = cbFulltime.isChecked();
                boolean isParttime = cbParttime.isChecked();

                // Cancel any ongoing search before executing the new search filter
                if (httpMngr != null) {
                    httpMngr.getRequestQueue().cancelAll(TAG);
                }
                fetchJobListData(jobTitle, jobCompanyName, jobLocation, isFulltime, isParttime);
            }
        };
        btnSearch.setOnClickListener(btnSearchClickListener);
    }

    // Call API fetching job listing
    private void fetchJobListData(String jobTitle, String jobCompanyName, String jobLocation,
                                  boolean isFulltime, boolean isParttime) {

        HashMap<String, String> mapQueryParams = new HashMap<>();
        if (jobTitle != null && !jobTitle.isEmpty()) {
            mapQueryParams.put("title", jobTitle);
        }
        if (jobTitle != null && !jobCompanyName.isEmpty()) {
            mapQueryParams.put("company", jobCompanyName);
        }
        if (jobLocation != null && !jobLocation.isEmpty()) {
            mapQueryParams.put("location", jobLocation);
        }
        if (isParttime || isFulltime) {
            if (isParttime && !isFulltime)
                mapQueryParams.put("full_time", "false");
            else if (!isParttime && isFulltime)
                mapQueryParams.put("full_time", "true");
        }

        StringRequest req = RequestCreator.createStringRequest(TAG,
                "/positions.json",
                Request.Method.GET,
                mapQueryParams,
                new RequestCreator.Callback() {
                    @Override
                    public void onResponse(String data) {
                        Log.d(TAG, data);
                        showJobListing(data);
                        showProgressDlg(false);
                    }

                    @Override
                    public void onError(VolleyError err) {
                        Log.e(TAG, err.toString());
                        Toast.makeText(getActivity(), getActivity().getResources()
                                .getString(R.string.msg_unable_to_connect),
                                Toast.LENGTH_SHORT).show();
                        showProgressDlg(false);
                    }
                });

        showProgressDlg(true);
        HttpManager.getInstance(getActivity()).addToRequestQuee(req);
    }

    // Display fragment of JobListFgmt to show job listing
    private void showJobListing(String data) {
        Gson gson = new GsonBuilder().create();
        ArrayList<Job> arr = gson.fromJson(data, new TypeToken<List<Job>>() {}.getType());

        JobListFgmt fgmt = new JobListFgmt();
        fgmt.setJobs(arr);

        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main_container, fgmt)
                .addToBackStack(TAG)
                .commit();
    }

    // Show progress dialog display
    private void showProgressDlg(boolean isShow) {
        if (pgDlg != null) {
            if (isShow) {
                pgDlg.show();
            } else if (pgDlg.isShowing()) {
                pgDlg.cancel();
            }
        }
    }
}
