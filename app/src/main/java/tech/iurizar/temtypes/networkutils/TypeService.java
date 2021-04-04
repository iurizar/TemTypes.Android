package tech.iurizar.temtypes.networkutils;

import tech.iurizar.temtypes.model.TypeWeakness;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypeService {
    @GET("/api/weaknesses")
    Call<TypeWeakness> getWeaknesses();
}
