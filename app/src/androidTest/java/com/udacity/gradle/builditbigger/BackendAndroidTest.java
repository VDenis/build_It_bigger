package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by Denis on 07.02.2016.
 */
public class BackendAndroidTest extends AndroidTestCase implements EndpointsAsyncTask.IResponse {
    private final String LOG_TAG = BackendAndroidTest.class.getSimpleName();

    public void testNotNullJokeTest() throws Exception {
        Log.i(LOG_TAG, "Run Async Task");
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        //endpointsAsyncTask.execute().get(15, TimeUnit.SECONDS);

        endpointsAsyncTask.execute().get(2, TimeUnit.SECONDS);

    }

    @Override
    public void postResult(String asyncResult) {
        Log.i(LOG_TAG, "get result from async task: " + asyncResult);
        assertFalse(asyncResult.isEmpty());
        //assertEquals("This is totally a funny joke", asyncResult);
    }
}
