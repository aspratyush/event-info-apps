package in.confluenceoftech.android.swedsd.GCM;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Jewel on 1/7/2016.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Intent intent=new Intent(this,RegistrationIntentService.class);
        startService(intent);
    }
}