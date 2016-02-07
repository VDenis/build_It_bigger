package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.denis.home.JokeActivity;


public class MainActivity extends ActionBarActivity implements EndpointsAsyncTask.IResponse {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){

        //String jokeText = new JokeSource().getJoke();
        //"derp"

        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(this, this);
        //endpointsAsyncTask.execute(new Pair<Context, String>(this, jokeText));
        // TODO: add Loading Indicator
        endpointsAsyncTask.execute();

        //Toast.makeText(this, jokeText, Toast.LENGTH_SHORT).show();
/*        Intent intent = new Intent(this, JokeActivity.class).putExtra(JokeActivity.EXTRA_JOKE_TO_SHOW, jokeText);
        startActivity(intent);*/
    }

    @Override
    public void postResult(String asyncResult){
        Log.i(LOG_TAG, "get resault from async task: " + asyncResult);
        Intent intent = new Intent(this, JokeActivity.class).putExtra(JokeActivity.EXTRA_JOKE_TO_SHOW, asyncResult);
        startActivity(intent);
    }
}
