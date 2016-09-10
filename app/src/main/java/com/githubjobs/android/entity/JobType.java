package com.githubjobs.android.entity;

import com.google.gson.annotations.SerializedName;

public enum JobType {

    @SerializedName("Full Time")
    FULL_TIME("Full Time"),

    @SerializedName("Part Time")
    PART_TIME("Part Time");

    private final String description;

    private JobType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
