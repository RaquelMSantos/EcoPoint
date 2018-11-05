package br.com.rmso.ecopoint.service.repository;

import java.util.List;

import br.com.rmso.ecopoint.service.model.Point;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PointService {

    @GET("action/datastore_search?resource_id=ef521704-6960-4ef1-8f98-a60db4a0d79b&limit=200")
    Call<List<Point>> getPoints();
}
