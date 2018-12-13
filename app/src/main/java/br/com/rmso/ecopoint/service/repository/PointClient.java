package br.com.rmso.ecopoint.service.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.com.rmso.ecopoint.Utility;
import br.com.rmso.ecopoint.database.AppDatabase;
import br.com.rmso.ecopoint.service.model.Point;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.gson.FieldNamingPolicy.IDENTITY;

public class PointClient {
    private static Retrofit retrofit;
    private static PointClient pointClient;

    public synchronized static PointClient getInstance() {
        if (pointClient == null) {
            pointClient = new PointClient();
        }
        return pointClient;
    }

    public PointClient(){
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(IDENTITY)
                    .create();

            this.retrofit = new Retrofit.Builder()
                    .baseUrl(PointService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }

    public LiveData<List<Point>> getPointList(){
        final MutableLiveData<List<Point>> data = new MutableLiveData<>();

        Call<Point> call = new PointClient().getPointService().getPoints();

        call.enqueue(new Callback<Point>() {
            @Override
            public void onResponse(Call<Point> call, Response<Point> response) {
                Point result = response.body();
                Utility.mCurrentPointList = result.getPointList();
                data.setValue(Utility.mCurrentPointList);
            }

            @Override
            public void onFailure(Call<Point> call, Throwable t) {
                data.setValue(null);
                Log.e("PointService","Error:" + t.getMessage());
            }
        });
        return data;
    }

    public PointService getPointService(){
        return this.retrofit.create(PointService.class);
    }
}
