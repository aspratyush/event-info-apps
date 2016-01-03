package in.confluenceoftech.googlemapsexample;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button mBtnNav;
    private EditText mDestAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDestAddr = (EditText) findViewById(R.id.editText);
        mBtnNav = (Button) findViewById(R.id.btnNav);
        mBtnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("NavEg", "onClick...");

                //set default value
                Uri gmmIntentUri = Uri.parse( "geo:0,0?q=bangabandhu+stadium+dhaka" );
                //start Google Maps
                if ( mDestAddr.length() == 0 ){
                    Log.d("NavEg","Destination address is empty... Use default address");
                }
                else{
                    String strAddr = mDestAddr.getText().toString();
                    Log.d("NavEg", "Address entered : " + strAddr + "..." + strAddr.length() );
                    //update gmmIntentUri

                    strAddr.replaceAll("\\s","+");
                    gmmIntentUri = Uri.parse( "geo:0,0?q=" + strAddr );
                }

                Intent mapIntent = new Intent( Intent.ACTION_VIEW, gmmIntentUri );
                mapIntent.setPackage( "com.google.android.apps.maps" );
                if (mapIntent.resolveActivity( getPackageManager() ) != null) {
                    startActivity(mapIntent);
                }

            }
        });
    }

    @Override
    protected void onResume(){
       super.onResume();
        Log.d("NavEg", "onResume...");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("NavEg", "onDestroy...");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("NavEg", "onPause...");
    }
}
