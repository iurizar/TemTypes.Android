package com.iurizar.temtypesandroid.networkutils;

import com.iurizar.temtypesandroid.model.TypeWeakness;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypeService {
    @GET("/api/weaknesses")
    Call<TypeWeakness> getWeaknesses();
}
