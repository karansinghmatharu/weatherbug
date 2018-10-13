package app.dev.app.helper;

import app.dev.app.api.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KaranDeepSingh on 10/11/2018.
 */

public class RetroClient {

    private static final String ROOT_URL = "https://s3.amazonaws.com/sc.va.util.weatherbug.com/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}