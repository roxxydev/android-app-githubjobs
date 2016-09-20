package com.githubjobs.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class GithubJobsAppTest {

    /**
     * The name 'test preconditions' is a convention to signal that if this
     * test doesn't pass, the test case was not set up properly and it might
     * explain any and all failures in other tests.  This is not guaranteed
     * to run before other tests, as junit uses reflection to find the tests.
     */
    @SmallTest
    public void testPreConditions() {
    }

    @Test
    public void testCorrectVersion() throws Exception {
        // Context of the app under test.
        Context application = InstrumentationRegistry.getTargetContext();

        PackageInfo info = application.getPackageManager()
                .getPackageInfo(application.getPackageName(), 0);
        assertNotNull(info);

        assertThat("Expected result matches regular expression for app version naming",
                info.versionName.matches("\\d\\.\\d\\.\\d"),
                is(true));

    }
}