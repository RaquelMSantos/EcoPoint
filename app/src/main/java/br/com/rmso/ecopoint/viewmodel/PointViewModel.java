package br.com.rmso.ecopoint.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.com.rmso.ecopoint.database.AppDatabase;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.service.repository.PointClient;
import retrofit2.Retrofit;

public class PointViewModel extends AndroidViewModel {

    private LiveData<List<Point>> point;
    private AppDatabase appDatabase;

    public PointViewModel (Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(this.getApplication());
        point = PointClient.getInstance().getPointList();
    }


    public LiveData<List<Point>> getPoint() {
        return point;
    }

}
