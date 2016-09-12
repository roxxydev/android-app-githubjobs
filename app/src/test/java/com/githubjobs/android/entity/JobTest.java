package com.githubjobs.android.entity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JobTest {

    private static final String JOB_JSON_FILENAME = "sample-job.json";
    private static final String JOBLIST_JSON_FILENAME = "sample-job-list.json";

    private static Gson gson;

    @BeforeClass
    public static void setUp() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @AfterClass
    public static void tearDown() {
        gson = null;
    }

    @Test
    public void testJsonToJob() throws Exception {
        JsonReader reader = new JsonReader(new FileReader(getJobJson()));
        Job job = gson.fromJson(reader, Job.class);

        assertNotNull("Job object should not be null", job);
        assertEquals("Parsed JSON String not equals not Job id",
                job.getId(), "da05e006-028b-11e6-98d3-5b3b448add54");
    }

    @Test
    public void testJobToJson() throws Exception {
        JsonReader reader = new JsonReader(new FileReader(getJobJson()));
        Job job = gson.fromJson(reader, Job.class);
        String jsonStr = gson.toJson(job);

        assertNotNull("JSON String Job should not be null", jsonStr);
        assertTrue("JSON String Job should not be empty", !jsonStr.isEmpty());
    }

    @Test
    public void testJsonToJobArray() throws Exception {
        JsonReader reader = new JsonReader(new FileReader(getJobListJson()));
        ArrayList<Job> arr = gson.fromJson(reader, new TypeToken<List<Job>>() {}.getType());

        assertTrue("Job list array should not be empty", !arr.isEmpty());
    }

    @Test
    public void testJobArrayToJson() throws Exception {
        JsonReader reader = new JsonReader(new FileReader(getJobListJson()));
        ArrayList<Job> arr = gson.fromJson(reader, new TypeToken<List<Job>>() {}.getType());
        String jsonArrStr = gson.toJson(arr, new TypeToken<List<Job>>() {}.getType());

        assertNotNull("JSON String Job should not be null", jsonArrStr);
        assertTrue("JSON String Job should not be empty", !jsonArrStr.isEmpty());
    }

    private File getJobJson() {
        URL location = this.getClass().getResource(JOB_JSON_FILENAME);
        String fullPath = location.getPath();
        return new File(fullPath);
    }

    private File getJobListJson() {
        URL location = this.getClass().getResource(JOBLIST_JSON_FILENAME);
        String fullPath = location.getPath();
        return new File(fullPath);
    }
}
