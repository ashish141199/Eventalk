package in.eventalk.eventalk;

import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;

public class ProfileViewActivity extends FragmentActivity {
    private Realm realm;
    private User dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        //adding the profile header fragment
        ProfileHeader profileHeader = new ProfileHeader();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fragment2, profileHeader, "profile_header");
        ft.commit();
        //getting the realm instance and stetho initialization
        Realm.init(getApplicationContext());
        Stetho.initialize(
                Stetho.newInitializerBuilder(ProfileViewActivity.this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(ProfileViewActivity.this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(ProfileViewActivity.this).build())
                        .build());
        realm = Realm.getDefaultInstance();
        //getting the user model of the current user!
        //since current user's info is stored as the first row, all we have to do is retrieve the first row's data
        User u = realm.where(User.class).findFirst();
        //copying the original db model to a temporary user model just to be safe and not manipulate the db data.
        dbUser = new User(u);
        Toast.makeText(this, dbUser.toString(), Toast.LENGTH_SHORT).show();

        TextView event_count = (TextView) findViewById(R.id.event_count);
        event_count.clearComposingText();
        //getting the profile_name TextView and setting name from dbUser(the copied object from DB).
        TextView profile_name = (TextView) findViewById(R.id.profile_name);
        profile_name.setText(dbUser.getFullName());








    }

    public void edit_profile(View view) {

    }
}
