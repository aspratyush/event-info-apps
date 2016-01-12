package com.example.toshiba.mylogin.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.mylogin.R;
import com.example.toshiba.mylogin.utils.Globals;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;

public class UploadActivity extends AppCompatActivity {

    /**********
     * File Path
     *************/
    final String uploadFilePath = Environment.getExternalStorageDirectory().getPath();
    final String uploadFileName = "a.jpg";
    TextView messageText;
    Button uploadButton;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;
    String upLoadServerUri = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadButton = (Button) findViewById(R.id.uploadButton);
        messageText = (TextView) findViewById(R.id.messageText);

        //setup actionbar
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        messageText.setText( "");

        /************* Php script path ****************/
        //upLoadServerUri = "http://www.step2code.com/upload/upload.php";
        upLoadServerUri = "http://step2code.com/pratyush/api/uploadImage";

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectFile();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            TextView statusText = (TextView) findViewById(R.id.messageText);
            statusText.setText("Sending: " + uri);
            //uploadFile(uri.toString());


            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            final String filePath = cursor.getString(columnIndex);

            cursor.close();
            // Convert file path into bitmap image using below line.
            //yourSelectedImage = BitmapFactory.decodeFile(filePath);
            ((ImageView) findViewById(R.id.img)).setImageBitmap(BitmapFactory.decodeFile(filePath));

            dialog = ProgressDialog.show(UploadActivity.this, "", "Uploading file...", true);



            AsyncHttpClient client=new AsyncHttpClient();
            File file=new File(filePath);
            RequestParams params=new RequestParams();
            try {
                params.put("user_id",Globals.USER_ID+"");
                params.put("gallery_type",1+"");
                params.put("test",file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            client.post(upLoadServerUri,params,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    dialog.dismiss();
                    try {
                        String status=response.getString("status");
                        messageText.setText(status+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    dialog.dismiss();
                    messageText.setText(responseString);
                }
            });
        }

    }
}
