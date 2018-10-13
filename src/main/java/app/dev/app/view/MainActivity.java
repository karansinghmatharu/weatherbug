package app.dev.app.view;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.dev.app.R;
import app.dev.app.adapter.GridAdapter;
import app.dev.app.adapter.ModelAdapter;
import app.dev.app.api.ApiService;
import app.dev.app.databinding.ActivityMainBinding;
import app.dev.app.helper.RetroClient;
import app.dev.app.model.Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://s3.amazonaws.com/sc.va.util.weatherbug.com/interviewdata/mobilecodingchallenge/";
    private ActivityMainBinding binding;
    private List<Model> modelList = new ArrayList<>(100);
    private ModelAdapter adapter;
    private GridAdapter gridAdapter;
    private ProgressDialog pDialog;
    private Object listEmptyorSingle;
    private boolean isLandscape;

    @BindingAdapter({"bind:url"})
    public static void setImage(ImageView imageView, String url) {
        if (url != null && url.trim().length() > 0) {
            Picasso.with(imageView.getContext()).load(url).error(R.mipmap.ic_launcher).into(imageView);
        } else
            imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkIfLandscaeMode();
        loadJson();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


    }

    private void checkIfLandscaeMode() {
        switch (getResources().getConfiguration().orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                isLandscape = false;
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                isLandscape = true;
                break;
            default:
                isLandscape = false;
        }
    }

    private void loadJson() {
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        modelList.clear();
        ApiService api = RetroClient.getApiService();
        Call<ArrayList<Model>> call = api.getMetaDataJson();
        call.enqueue(new Callback<ArrayList<Model>>() {
            @Override
            public void onResponse(Call<ArrayList<Model>> call, Response<ArrayList<Model>> response) {
                if (response.isSuccessful()) {

                    modelList = response.body();
                    Model model = null;

                    for (int i = 0; i < modelList.size(); i++) {
                        String file = modelList.get(i).setFilename(URL + modelList.get(i).getFilename());
                        model = new Model(modelList.get(i).getTitle(), file, modelList.get(i).getDescription());
                        addToList(model);
                    }
                }
                pDialog.dismiss();
                if (isLandscape) {
                    gridAdapter = new GridAdapter(modelList);
                    binding.gridview.setAdapter(gridAdapter);
                } else {
                    adapter = new ModelAdapter(modelList);
                    binding.recycler.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Model>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Poor or no internet connection", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        });
    }

    void addToList(Model model) {
        if (listEmptyorSingle == null) {
            listEmptyorSingle = model;
        } else if (listEmptyorSingle instanceof Model) {
            Model firstEl = (Model) listEmptyorSingle;
            ArrayList<Model> modelList = new ArrayList<>();
            listEmptyorSingle = modelList;
            modelList.add(firstEl);
            modelList.add(model);
        } else {
            ((ArrayList<Model>) listEmptyorSingle).add(model);
        }

        System.out.println(modelList.size());

    }
}
