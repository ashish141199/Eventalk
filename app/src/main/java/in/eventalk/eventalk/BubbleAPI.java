package in.eventalk.eventalk;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;


public interface BubbleAPI {

    @GET("/api/bubbles?format=json")
    Call<List<Bubble>> getStream();
}
