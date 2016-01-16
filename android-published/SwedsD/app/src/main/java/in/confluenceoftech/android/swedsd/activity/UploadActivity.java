package in.confluenceoftech.android.swedsd.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.confluenceoftech.android.swedsd.utils.Globals;
import in.confluenceoftech.android.swedsd.utils.Utils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class UploadActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 100;
    TextView messageText;
    Button uploadButton;
    ProgressDialog dialog = null;
    String upLoadServerUri = null;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(in.confluenceoftech.android.swedsd.R.layout.activity_upload);

        uploadButton = (Button) findViewById(in.confluenceoftech.android.swedsd.R.id.uploadButton);
        messageText = (TextView) findViewById(in.confluenceoftech.android.swedsd.R.id.messageText);

        //setup actionbar
        setSupportActionBar((Toolbar)findViewById(in.confluenceoftech.android.swedsd.R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        messageText.setText("");

        /************* Php script path ****************/
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
        Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        startActivityForResult( intent, RESULT_LOAD_IMG );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode== RESULT_LOAD_IMG && null != data ){
            Uri uri = data.getData();
            TextView statusText = (TextView) findViewById(in.confluenceoftech.android.swedsd.R.id.messageText);
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
            Bitmap scaledImage = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(filePath), 1024, 768, true);

            //((ImageView) findViewById(R.id.img)).setImageBitmap(BitmapFactory.decodeFile(filePath));
            ((ImageView) findViewById(in.confluenceoftech.android.swedsd.R.id.img)).setImageBitmap(scaledImage);


            //dialog = ProgressDialog.show(UploadActivity.this, "", "Uploading file...", true);


            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            scaledImage.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            File f = new File(getCacheDir(), Globals.USER_ID + "_" + System.currentTimeMillis() + ".png");

            //write the bytes in file
            FileOutputStream fos = null;
            try {

                f.createNewFile();
                fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            AsyncHttpClient client = new AsyncHttpClient();
            RequestParams params = new RequestParams();
            Utils.sendNotfication(this, "Info", "Uploading...");

            params.put("user_id", Globals.USER_ID + "");
            params.put("gallery_type", 1 + "");
            try {
                params.put("test", f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            client.post(upLoadServerUri, params, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    try {
                        String status = response.getString("status");
                        Utils.sendNotfication(context, "Info", "Uploaded");
                        messageText.setText(status + "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    messageText.setText(responseString);
                    Utils.sendNotfication(context, "Info", "Uploaded");
                }
            });
        }

    }

}
