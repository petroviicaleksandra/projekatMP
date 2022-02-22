package com.mv.project;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public class RequestMenadzer {

    Context context;
    Retrofit retrofit = new Retrofit.Builder().
            baseUrl("https://imdb-internet-movie-database-unofficial.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();

    public RequestMenadzer(Context context) {
        this.context = context;
    }
    public void pretraziFilmove(OnSearchAPIListener listener, String film){
        getFilmovi getFilmovi = retrofit.create(RequestMenadzer.getFilmovi.class);
        Call<SearchAPIOdgovor> call = getFilmovi.filmovi(film);

        call.enqueue(new Callback<SearchAPIOdgovor>() {
            @Override
            public void onResponse(Call<SearchAPIOdgovor> call, Response<SearchAPIOdgovor> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context,"Nisu preuzeti podaci",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchAPIOdgovor> call, Throwable t) {
            listener.onError(t.getMessage());
            }
        });
    }
    public void pretraziInformacijeOFilmu(OnInformacijeApiListener listener, String filmID){
        getInformacije getInformacije = retrofit.create(RequestMenadzer.getInformacije.class);
        Call<InformacijeAPI> call = getInformacije.informacije(filmID);

        call.enqueue(new Callback<InformacijeAPI>() {
            @Override
            public void onResponse(Call<InformacijeAPI> call, Response<InformacijeAPI> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context,"Nisu preuzeti podaci",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<InformacijeAPI> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public  interface getFilmovi{
        @Headers({
                "Accept: application/json",
                "x-rapidapi-host: imdb-internet-movie-database-unofficial.p.rapidapi.com",
                "x-rapidapi-key: 2b99da297fmsh1d84fccf8ba676cp1d464bjsn95d100f7d1bf"
        })
        @GET("search/{film}")
        Call<SearchAPIOdgovor> filmovi(
                @Path("film") String film
        );
    }
    public  interface getInformacije{
        @Headers({
                "Accept: application/json",
                "x-rapidapi-host: imdb-internet-movie-database-unofficial.p.rapidapi.com",
                "x-rapidapi-key: 2b99da297fmsh1d84fccf8ba676cp1d464bjsn95d100f7d1bf"
        })
        @GET("film/{film_id}")
        Call<InformacijeAPI> informacije(
                @Path("film_id") String film_id
        );
    }
}
