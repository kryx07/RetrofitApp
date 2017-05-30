package com.example.sda.retrofitapp.network;

import com.example.sda.retrofitapp.model.Client;
import com.example.sda.retrofitapp.model.LoginResponse;
import com.example.sda.retrofitapp.model.CallActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by sda on 23.05.17.
 */

public interface ApiService {

    @GET("Activities/Get")
    Call<List<CallActivity>> getActivities();

    @GET("Clients/Get")
    Call<List<Client>> getClients();

    @PUT("Clients/Put")
    Call<Void> updateClient(@Body Client client);

    @POST("Clients/Post")
    Call<Void> addClient(@Body List<Client >client);

    @HTTP(method = "DELETE", path = "Clients/Delete", hasBody = true)
  /*  @DELETE("Clients/Delete")*/
    Call<Void> deleteClient(@Body Client client);



    @FormUrlEncoded
    @POST("Account/Login")
    Call<LoginResponse> login(@Field("Email") String email, @Field("Password") String password);


}
