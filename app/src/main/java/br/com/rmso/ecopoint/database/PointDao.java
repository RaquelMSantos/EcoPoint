package br.com.rmso.ecopoint.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.rmso.ecopoint.service.model.Point;

@Dao
public interface PointDao {

    @Query("SELECT * FROM point ORDER BY id")
    LiveData<List<Point>> loadAllPoints();

    @Insert
    void insertPoint(List<Point> point);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePoint (Point point);

    @Delete
    void deletePoint (Point point);

    @Query("SELECT * FROM point WHERE id = :id")
    LiveData<Point> loadPointById(int id);

    @Query("SELECT * FROM point WHERE id = :id")
    List<Point> loadPointByUId(int id);

}
