package in.eventalk.eventalk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;


public class ProfileActivity extends AppCompatActivity {

    final Profile profile = Profile.getCurrentProfile();
    private String IMAGE_URL = profile.getProfilePictureUri(300,300).toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView greeting = new TextView(this);
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.activity_profile);
        mainLayout.addView(greeting);
        ImageView image = new ImageView(this);
        Picasso.with(getApplicationContext()).load(IMAGE_URL).into(image);

        mainLayout.addView(image);


    }

    public void logout(View view){
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
    private void goLoginScreen(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
