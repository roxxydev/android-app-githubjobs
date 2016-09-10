package com.githubjobs.android.views;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.githubjobs.android.R;
import com.githubjobs.android.entity.Job;
import com.githubjobs.android.network.RequestCreator;

import java.util.ArrayList;

public class JobsListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Job> arr = new ArrayList<>();

    public JobsListAdapter(Activity activity, ArrayList<Job> arr) {
        this.activity = activity;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        if (arr != null) {
            return arr.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return arr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vHolder = null;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.joblist_item, null);
            vHolder = new ViewHolder(view);
            view.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) view.getTag();
        }

        Job jobObj = arr.get(i);

        vHolder.tvJobTitle.setText(jobObj.getTitle());
        vHolder.tvCompanyName.setText(jobObj.getCompany());

        RequestCreator.loadImage(activity, jobObj.getCompanyLogo(), vHolder.iv);

        return view;
    }

    class ViewHolder {
        ImageView iv;
        TextView tvJobTitle, tvCompanyName;

        public ViewHolder(View parentView) {
            iv = (ImageView) parentView.findViewById(R.id.joblist_item_iv);
            tvJobTitle = (TextView) parentView.findViewById(R.id.joblist_item_tvtitle);
            tvCompanyName = (TextView) parentView.findViewById(R.id.joblist_item_tvcompany);
        }
    }
}
