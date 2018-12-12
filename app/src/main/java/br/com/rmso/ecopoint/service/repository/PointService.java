package br.com.rmso.ecopoint.service.repository;

import br.com.rmso.ecopoint.service.model.Point;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PointService {

    String BASE_URL = "http://desafio.serttel.com.br/";

    @GET("dadosRecifeSemaforo.json")
    Call<Point> getPoints();
}
