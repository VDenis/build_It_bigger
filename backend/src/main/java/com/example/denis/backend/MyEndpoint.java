/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.denis.backend;

import com.denis.home.JokeSource;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
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
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

    /**
     * A simple endpoint method that says a joke back
     */
    @ApiMethod(name = "sayJoke")
    public MyBean sayJoke() {
        MyBean response = new MyBean();
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {

        }*/
        JokeSource jokeSource = new JokeSource();
        response.setData(jokeSource.getJoke());

        return response;
    }

}
