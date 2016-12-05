package in.eventalk.eventalk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
    public void getData(View view){
        final TextView textView = (TextView) findViewById(R.id.textView2);


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://jsonplaceholder.typicode.com/posts";

        //Request a string response from the provided url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.substring(0, 500));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That Didn't Work!");
                    }
                });
        queue.add(stringRequest);
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
