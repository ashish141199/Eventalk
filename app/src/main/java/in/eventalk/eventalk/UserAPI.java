package in.eventalk.eventalk;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("/api/user/create/")
    Call<User> createUser(@Body User user);

}
