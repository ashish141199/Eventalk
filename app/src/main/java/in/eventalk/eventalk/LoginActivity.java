package in.eventalk.eventalk;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "abc";
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private static final List<String> PERMISSIONS = Arrays.asList("public_profile","user_friends", "user_location", "user_birthday", "email");
    private static final String ENDPOINT = "https://eventalk.herokuapp.com";
    private Realm realm;
    private User dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(PERMISSIONS);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                User u = new User();
                                if (response.getError()!=null){
                                    Log.e("FBERROR", "Error in response"+response);
                                }

                                    u.setFbId(object.optString("id"));
                                    u.setFullName(object.optString("name"));
                                    u.setDob(object.optString("birthday"));
                                    u.setDp(object.optString("picture"));
                                    u.setGender(object.optString("gender"));
                                    u.setEmail(object.optString("email"));
                                    u.setLocation(object.optString("location"));



                                Retrofit adapter = new Retrofit.Builder()
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .baseUrl(ENDPOINT)
                                        .build();
                                UserAPI api = adapter.create(UserAPI.class);
                                Call<User> call = api.createUser(u);

                                call.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {


                                        Realm.init(getApplicationContext());
                                        Stetho.initialize(
                                                Stetho.newInitializerBuilder(LoginActivity.this)
                                                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(LoginActivity.this))
                                                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(LoginActivity.this).build())
                                                        .build());


                                        realm = Realm.getDefaultInstance();
                                        Toast.makeText(LoginActivity.this, realm.getPath(), Toast.LENGTH_SHORT).show();
                                        dbUser = new User(response.body());

                                        save_into_database();

                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                        Log.w("throwable", t);
                                    }
                                });

                            }
                        });


                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, name, birthday, picture, gender, email, location");
                request.setParameters(parameters);
                request.executeAsync();
                goToProfile();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), "There seems to be an error with Facebook Login. Try Again Later.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void save_into_database() {


        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User u;
                u = realm.where(User.class).equalTo("email", dbUser.getEmail()).findFirst();

                if (u != null) {


                } else {
                    u = realm.createObject(User.class);
                }
                u.setEmail(dbUser.getEmail());
                u.setGender(dbUser.getGender());
                u.setFullName(dbUser.getFullName());
                u.setFbId(dbUser.getFbId());
                u.setDp(dbUser.getDp());
                u.setId(dbUser.getId());
                u.setDob(dbUser.getDob());
                u.setLocation(dbUser.getLocation());
                u.setIsVerified(dbUser.getIsVerified());

            }

        },
        new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
            }
        },
        new Realm.Transaction.OnError(){
            @Override
            public void onError(Throwable error) {
                Log.e("database", error.getMessage());
            }
        });
    }

    public void profile(View view) {
        Intent i = new Intent(this, ProfileViewActivity.class);
        startActivity(i);
    }
    public void goToProfile(){
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

}
