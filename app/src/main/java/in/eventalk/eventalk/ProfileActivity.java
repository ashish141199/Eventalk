package in.eventalk.eventalk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileActivity extends AppCompatActivity {
    private ProgressBar spinner;
    public static final String ENDPOINT = "http://www.bloup.in";
    RelativeLayout activity_profile;

    List<Bubble> bubbleList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        activity_profile = (RelativeLayout) findViewById(R.id.activity_profile);
        spinner = new ProgressBar(ProfileActivity.this);
        activity_profile.addView(spinner);
        requestData("Asc");
    }
    public void requestData(String uri){
        Retrofit adapter = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ENDPOINT)
                .build();
        BubbleAPI api = adapter.create(BubbleAPI.class);

        Call<List<Bubble>> call = api.getStream();
        call.enqueue(new Callback<List<Bubble>>() {
            @Override
            public void onResponse(Call<List<Bubble>> call, Response<List<Bubble>> response) {
                bubbleList = response.body();
                ListView listView = (ListView) findViewById(R.id.listView);
                CustomAdapter adapter = new CustomAdapter(ProfileActivity.this, bubbleList);
                listView.setAdapter(adapter);
                activity_profile.removeView(spinner);

            }

            @Override
            public void onFailure(Call<List<Bubble>> call, Throwable t) {

            }
        });

    }

}
