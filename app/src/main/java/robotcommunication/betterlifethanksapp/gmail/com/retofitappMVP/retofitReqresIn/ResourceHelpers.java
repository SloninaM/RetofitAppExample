package robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn;

import java.util.ArrayList;
import java.util.List;

import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.MultipleResource;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.User;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.pojo.UserList;

class ResourceHelpers {

    private static final String PAGE = " Page\n";
    private static final String TOTAL = " Total\n";
    private static final String TOTAL_PAGES = " Total Pages\n";
    private static final String NO_DATA = "Błąd zapytania w sieci";


    String combineResource(MultipleResource resource)
    {

        StringBuilder sb = new StringBuilder();

        sb.append(resource.page);
        sb.append(PAGE);
        sb.append(resource.total);
        sb.append(TOTAL);
        sb.append(resource.totalPages);
        sb.append(TOTAL_PAGES);


        List<MultipleResource.Datum> datumList = resource.data;
        for(MultipleResource.Datum datum: datumList)
        {
            sb.append(datum.id);
            sb.append(" ");
            sb.append(datum.name);
            sb.append(" ");
            sb.append(datum.pantoneValue);
            sb.append(" ");
            sb.append(datum.year);
            sb.append("\n");

        }

        return sb.toString();
    }

    String combineUserInfo(User userResponse) {

        if(userResponse==null)
        {
            throw new NullPointerException(NO_DATA);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(userResponse.name);
        sb.append("\n");
        sb.append(userResponse.job);
        sb.append("\n");
        sb.append(userResponse.id);
        sb.append("\n");
        sb.append(userResponse.createdAt);

        return sb.toString();
    }


    List<String> combineUserListData(UserList userList)
    {
        if(userList==null)
        {
            throw new NullPointerException(NO_DATA);
        }

        List<String> userListData = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(userList.page);
        sb.append(" page\n");
        sb.append(userList.total);
        sb.append(" total\n");
        sb.append(userList.totalPages);
        sb.append(" totalPages\n");

        userListData.add(sb.toString());



        List<UserList.Datum> datumList = userList.data;


        for (UserList.Datum datum : datumList) {
            sb = new StringBuilder();
            sb.append("id : ");
            sb.append(datum.id);
            sb.append(" name: ");
            sb.append(datum.first_name );
            sb.append(" ");
            sb.append(datum.last_name);
            sb.append(" avatar: ");
            sb.append(datum.avatar);
            userListData.add(sb.toString());
        }



        return userListData;

    }
}
