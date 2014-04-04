package be.uhasselt.privacypolice;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AskPermissionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askpermission);

        Intent intent = getIntent();
        final String SSID = intent.getStringExtra("SSID");
        final String BSSID = intent.getStringExtra("BSSID");

        Resources res = getResources();
        String permissionString = String.format(res.getString(R.string.ask_permission), SSID);

        TextView networkQuestion = (TextView) findViewById(R.id.networkQuestion);
        networkQuestion.setText(permissionString);

        Button yesButton = (Button) findViewById(R.id.yesButton);
        yesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addIntent = new Intent(getApplicationContext(), PermissionChangeReceiver.class);
                        addIntent.putExtra("SSID", SSID).putExtra("BSSID", BSSID).putExtra("enable", true);
                        sendBroadcast(addIntent);
                        finish();
                    }
                }
        );
        Button noButton = (Button) findViewById(R.id.noButton);
        noButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addIntent = new Intent(getApplicationContext(), PermissionChangeReceiver.class);
                        addIntent.putExtra("SSID", SSID).putExtra("BSSID", BSSID).putExtra("enable", false);
                        sendBroadcast(addIntent);
                        finish();
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ask_permission, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
