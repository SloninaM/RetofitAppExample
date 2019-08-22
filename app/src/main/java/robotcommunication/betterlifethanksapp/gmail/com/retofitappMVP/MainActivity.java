package robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retofitReqresIn.SiteOperationFabric;
import robotcommunication.betterlifethanksapp.gmail.com.retofitappMVP.retrofit.ResponseCallback;


public class MainActivity extends AppCompatActivity implements ResponseCallback, View.OnClickListener {

    private TextView responseText;
    private Button btnListResources;
    private Button btnCreateUser;
    private Button btnUserList;
    private Button btnClearScreen;
    private ProgressBar progressRetrofitBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        if(savedInstanceState != null)
        {
            String responseRecovery = savedInstanceState.getString("tvData");
            responseText.setText(responseRecovery);
        }

    }

    private void initialize() {
        responseText = findViewById(R.id.responseText);
        btnCreateUser = findViewById(R.id.btnCreateUser);
        btnListResources = findViewById(R.id.btnListResources);
        btnClearScreen = findViewById(R.id.btnClearScreen);
        btnUserList = findViewById(R.id.btnUserList);
        btnCreateUser.setOnClickListener(this);
        btnListResources.setOnClickListener(this);
        btnUserList.setOnClickListener(this);
        btnClearScreen.setOnClickListener(this);
        progressRetrofitBar = findViewById(R.id.progress_retrofitBar);
    }

    @Override
    public void onResponseCallback(String message) {
        progressRetrofitBar.setVisibility(View.INVISIBLE);
        responseText.setText(message);
    }

    @Override
    public void onResponseToastCallback(String message) {
        progressRetrofitBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {
        SiteOperationFabric mSiteOperationFabric = new SiteOperationFabric();
        String numPage = "2";

        responseText.setText("");
        switch (view.getId())
        {
            case R.id.btnCreateUser:
                progressRetrofitBar.setVisibility(View.VISIBLE);
                mSiteOperationFabric.createNewUser(MainActivity.this);
                break;
            case R.id.btnListResources:
                progressRetrofitBar.setVisibility(View.VISIBLE);
                mSiteOperationFabric.getListResources(MainActivity.this);
                break;
            case R.id.btnUserList:
                progressRetrofitBar.setVisibility(View.VISIBLE);
                mSiteOperationFabric.getListUser(MainActivity.this,numPage);
                break;
            case R.id.btnClearScreen:
                responseText.setText("");
                break;
                default:
                    throw new Error(getString(R.string.error));

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tvData",responseText.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
