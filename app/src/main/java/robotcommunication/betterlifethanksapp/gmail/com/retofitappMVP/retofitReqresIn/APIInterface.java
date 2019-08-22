package robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.MultipleResource;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.User;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.UserList;

public interface APIInterface {

    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

}
