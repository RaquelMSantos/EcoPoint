package br.com.rmso.ecopoint.service.repository;

import br.com.rmso.ecopoint.service.model.Point;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PointService {

    String BASE_URL = "http://raquel.servehttp.com/";

    @GET("pontosColetaRecife.json")
    Call<Point> getPoints();
}
