package com.example.denis.backend;

import com.denis.home.JokeSource;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * Created by Denis on 08.02.2016.
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.denis.example.com",
                ownerName = "backend.denis.example.com",
                packagePath = ""
        )
)
public class JokeEndpoint {

    @ApiMethod(name = "tellJoke")
    public JokeBean tellJoke() {
        JokeBean response = new JokeBean();
        JokeSource jokeSource = new JokeSource();
        String joke = jokeSource.getJoke();
        response.setJoke(joke);
        return response;
    }
}
