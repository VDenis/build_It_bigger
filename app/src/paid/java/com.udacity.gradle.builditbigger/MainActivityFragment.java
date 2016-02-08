package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.denis.home.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.IResponse {

    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private ProgressBar mLoadingIndicator = null;

    String joke = "No joke loaded";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(com.udacity.gradle.builditbigger.R.layout.fragment_main, container, false);

        mLoadingIndicator = (ProgressBar) root.findViewById(R.id.progressBar);
        mLoadingIndicator.setVisibility(View.INVISIBLE);

        Button button = (Button) root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });

        return root;
    }

    public void tellJoke() {
        startJokeActivity();
    }

    private void startJokeActivity() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this);
        endpointsAsyncTask.execute();
    }

    @Override
    public void postResult(String asyncResult) {
        Log.i(LOG_TAG, "get resault from async task: " + asyncResult);

        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (!asyncResult.contains("failed")) {
            this.joke = asyncResult;
        }
        launchJokeActivity();
    }

    public void launchJokeActivity() {
        Intent intent = new Intent(getActivity(), JokeActivity.class);
        intent.putExtra(JokeActivity.EXTRA_JOKE_TO_SHOW, joke);
        startActivity(intent);
    }
}
