package br.com.rmso.ecopoint.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import br.com.rmso.ecopoint.database.AppDatabase;

public class PointViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int mPointId;

    public PointViewModelFactory(AppDatabase database, int pointId){
        mDb = database;
        mPointId = pointId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new PointViewModel(mDb, mPointId);
    }
}
