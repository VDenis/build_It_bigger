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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.IResponse {

    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private ProgressBar mLoadingIndicator = null;
    InterstitialAd mInterstitialAd;

    String joke = "No joke loaded";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(getString(R.string.device_id))
                .build();
        mAdView.loadAd(adRequest);

        mLoadingIndicator = (ProgressBar) root.findViewById(R.id.progressBar);
        mLoadingIndicator.setVisibility(View.INVISIBLE);

        Button button = (Button) root.findViewById(R.id.joke_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tellJoke();
            }
        });

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.full_screen_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                startJokeActivity();
            }
        });
        requestNewInterstitial();

        return root;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getString(R.string.device_id))
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void tellJoke() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startJokeActivity();
        }
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
