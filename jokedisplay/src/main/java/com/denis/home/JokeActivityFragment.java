package com.denis.home;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denis.home.joke.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public static final String ARGUMENTS_JOKE_TO_SHOW = JokeActivity.EXTRA_JOKE_TO_SHOW;

    public JokeActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView =  inflater.inflate(R.layout.fragment_joke, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String jokeText = arguments.getString(ARGUMENTS_JOKE_TO_SHOW);
            if (!jokeText.isEmpty()) {
                TextView jokeTextView = (TextView) rootView.findViewById(R.id.joke_text);
                jokeTextView.setText(jokeText);
            }
        }

        return rootView;
    }
}
