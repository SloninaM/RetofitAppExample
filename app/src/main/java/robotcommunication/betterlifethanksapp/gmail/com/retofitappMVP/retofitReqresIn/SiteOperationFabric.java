package robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn;

import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.User;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retrofit.APIClient;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retrofit.ResponseCallback;

public class SiteOperationFabric {

    private final APIInterface mInterface;
    private final String SITE_NAME = "https://reqres.in";
    private final SiteOperations siteOperations;
    private final ResourceHelpers resourceHelpers;

    public SiteOperationFabric() {
        mInterface =APIClient.getClient(SITE_NAME).create(APIInterface.class);
        siteOperations = new SiteOperations(mInterface);
        resourceHelpers = new ResourceHelpers();
    }

    public void getListResources(final ResponseCallback responseCallback)
    {
        siteOperations.getListResources(responseCallback,resourceHelpers);
    }

    public void createNewUser(final ResponseCallback responseCallback)
    {
        User user = new User("morpheus", "leader");//George Bluth  //morpheus leader
        siteOperations.createNewUser(responseCallback,resourceHelpers,user);
    }

    public void getListUser(final ResponseCallback responseCallback,String numPage)
    {
        siteOperations.getListUser(responseCallback,resourceHelpers,numPage);
    }

}
