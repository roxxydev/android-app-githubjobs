package com.githubjobs.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubjobs.android.R;
import com.githubjobs.android.entity.Job;
import com.githubjobs.android.network.RequestCreator;
import com.githubjobs.android.views.ViewUtil;

public class JobDetailsFgmt extends Fragment {

    private static final String TAG = JobDetailsFgmt.class.getSimpleName();

    private View v;
    private ImageView iv;
    private TextView tvTitle, tvCompany, tvType, tvLocation,
            tvDesc, tvHowToApply, tvLink;

    private Job job;

    public void setJob(Job job) {
        this.job = job;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fgmt_jobdetails, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv = (ImageView) v.findViewById(R.id.fgmt_jobdetails_iv);
        tvTitle = (TextView) v.findViewById(R.id.fgmt_jobdetails_tv_title);
        tvCompany = (TextView) v.findViewById(R.id.fgmt_jobdetails_tv_company_name);
        tvType = (TextView) v.findViewById(R.id.fgmt_jobdetails_tv_type);
        tvLocation = (TextView) v.findViewById(R.id.fgmt_jobdetails_tv_location);
        tvDesc = (TextView) v.findViewById(R.id.fgmt_jobdetails_tv_description);
        tvHowToApply = (TextView) v.findViewById(R.id.fgmt_jobdetails_tv_how_to_apply);
        tvLink = (TextView) v.findViewById(R.id.fgmt_jobdetails_tv_link);

        initViews();
    }

    @Override
    public void onResume() {
        super.onResume();

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        ActionBar ab = appCompatActivity.getSupportActionBar();
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(getActivity().getResources().getString(R.string.fgmt_jobdetails_ab_title));
    }

    // Initialize view values
    private void initViews() {
        RequestCreator.loadImage(getActivity(), job.getCompanyLogo(), iv);

        tvTitle.setText(job.getTitle());
        tvCompany.setText(job.getCompany());
        tvType.setText(job.getType().getDescription());
        tvLocation.setText(job.getLocation());

        // Trim the returned Spanned returned of Html.fromHtml as it adds new line at the end
        String description = job.getDescription().trim();
        tvDesc.setText(ViewUtil.convertHtmlToTextFormat(description).toString().trim());

        String howToApply = job.getHowToApply().trim();
        tvHowToApply.setText(ViewUtil.convertHtmlToTextFormat(howToApply).toString().trim());

        tvLink.setText(job.getUrl());
    }
}
