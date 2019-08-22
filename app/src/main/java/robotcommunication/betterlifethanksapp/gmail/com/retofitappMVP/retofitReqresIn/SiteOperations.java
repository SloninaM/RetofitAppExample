package robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.MultipleResource;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.User;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.UserList;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retrofit.ResponseCallback;

class SiteOperations {

    private APIInterface apiInterface;
    private static final String sERROR = "Error :(\nDo you have Internet access?\n Or domain name (in this app) is correct?";


    SiteOperations(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }



    void getListResources(final ResponseCallback responseCallback, final ResourceHelpers resourceHelpers){

        Call<MultipleResource> call =
                apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(@NotNull Call<MultipleResource> call, @NotNull Response<MultipleResource> response) {

                //To map the Model class to the response we use
                MultipleResource resource = response.body();

                String displayResponse = resourceHelpers.combineResource(resource);

                responseCallback.onResponseCallback(displayResponse);

            }

            @Override
            public void onFailure(@NotNull Call<MultipleResource> call, @NotNull Throwable t) {
                call.cancel();
                responseCallback.onResponseCallback(sERROR);
            }
        });

    }

    void createNewUser(final ResponseCallback responseCallback, final ResourceHelpers resourceHelpers, User user)
    {
        Call<User> call = apiInterface.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                User userResponse = response.body();
                String displayResponse = resourceHelpers.combineUserInfo(userResponse);
                responseCallback.onResponseCallback(displayResponse);

            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                call.cancel();
                responseCallback.onResponseCallback(sERROR);
            }
        });

    }

    void getListUser(final ResponseCallback responseCallback, final ResourceHelpers resourceHelpers, String numPage)
    {
        Call<UserList> call = apiInterface.doGetUserList(numPage);
        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(@NotNull Call<UserList> call, @NotNull Response<UserList> response) {
                UserList userList = response.body();
                List<String> userListData = resourceHelpers.combineUserListData(userList);
                responseCallback.onResponseCallback(userListData.get(0));
                //liczone od drugiego elementu
                for (int i=1;i<userListData.size();++i) {
                    responseCallback.onResponseToastCallback(userListData.get(i));
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserList> call, @NotNull Throwable t) {
                call.cancel();
                responseCallback.onResponseCallback(sERROR);

            }
        });

    }

}
