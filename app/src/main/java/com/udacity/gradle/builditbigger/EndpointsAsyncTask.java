package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.denis.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by Denis on 31.01.2016.
 */
public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {

    private final String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();

    // Callback
    public interface IResponse {
        void postResult(String asyncresult);
    }

    // Callback
    private IResponse mResponse = null;

    private static MyApi myApiService = null;
    private Context mContext;

/*    public EndpointsAsyncTask(Context context) {
        mContext = context;
    } */

    public EndpointsAsyncTask(Context context, IResponse response) {
        mContext = context;
        mResponse = response;
    }

    @Override
    //protected String doInBackground(Pair<Context, String>... params) {
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        //mContext = params[0];
/*        mContext = params[0].first;
        String name = params[0].second;*/

        try {
            //return myApiService.sayHi(name).execute().getData();
            return myApiService.sayJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
        //return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
        //super.onPostExecute(s);
        if (mResponse != null) {
            // TODO: If error, return "failed to connect to /10.0.2.2 (port 8080) after 20000ms"
            // or + Network is unreachable
            mResponse.postResult(s);
        } else {
            Log.e(LOG_TAG, "You have not assigned IResponse mResponse");
        }
    }
}
