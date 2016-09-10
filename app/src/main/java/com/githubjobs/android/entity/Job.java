package com.githubjobs.android.entity;

import com.google.gson.annotations.SerializedName;

public class Job {

    private String id;

    @SerializedName("created_at")
    private String createdAt;

    private String title;

    private String company;

    @SerializedName("company_url")
    private String companyUrl;

    @SerializedName("company_logo")
    private String companyLogo;

    private String location;

    private JobType type;

    private String description;

    @SerializedName("how_to_apply")
    private String howToApply;

    private String url;


    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getLocation() {
        return location;
    }

    public JobType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public String getUrl() {
        return url;
    }
}
