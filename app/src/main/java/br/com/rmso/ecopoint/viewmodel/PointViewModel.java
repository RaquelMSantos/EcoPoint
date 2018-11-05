package br.com.rmso.ecopoint.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import br.com.rmso.ecopoint.database.AppDatabase;
import br.com.rmso.ecopoint.service.model.Point;
import br.com.rmso.ecopoint.view.adapters.PointAdapter;

public class PointViewModel extends ViewModel {
    private LiveData<Point> point;

    public PointViewModel (AppDatabase mDb, int mPointId) {
        point = mDb.pointDao().loadPointById(mPointId);
    }

    public LiveData<Point> getPoint() {
        return point;
    }
}
