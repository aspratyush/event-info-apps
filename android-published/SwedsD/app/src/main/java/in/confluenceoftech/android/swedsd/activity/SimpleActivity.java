package in.confluenceoftech.android.swedsd.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import in.confluenceoftech.android.swedsd.R;
import in.confluenceoftech.android.swedsd.model.MGallery;
import com.squareup.picasso.Picasso;

public class SimpleActivity extends AppCompatActivity {
    public static MGallery gallery;
    private Toolbar toolbar;
    private ImageView imgPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        init();
        prepareDis();
    }

    private void init() {
        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void prepareDis(){
        //check orientation
        if ( gallery.getSrc().contains("_LAND")){
            Picasso.with(this)
                    .load("http://confluenceoftech.in/uploads/image/large/"+gallery.getSrc())
                    .rotate(90)
                    .placeholder(R.drawable.loading)
                    .into(imgPhoto);
        }
        else{
            Picasso.with(this)
                    .load("http://confluenceoftech.in/uploads/image/large/"+gallery.getSrc())
                    .placeholder(R.drawable.loading)
                    .into(imgPhoto);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
