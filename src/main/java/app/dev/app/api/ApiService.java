package app.dev.app.api;

import java.util.ArrayList;
import java.util.List;

import app.dev.app.model.Model;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by KaranDeepSingh on 10/11/2018.
 */

public interface ApiService {

    @GET("interviewdata/mobilecodingchallenge/sampledata.json")
    Call<ArrayList<Model>> getMetaDataJson();
}
