package br.com.rmso.ecopoint.service.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.google.gson.FieldNamingPolicy.IDENTITY;

public class PointClient {

    static final String BASE_URL = "http://dados.recife.pe.gov.br/api/";
    private static Retrofit retrofit;

    public PointClient(){
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(IDENTITY)
                    .create();

            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
    }

    public PointService getPointService(){
        return this.retrofit.create(PointService.class);
    }
}
