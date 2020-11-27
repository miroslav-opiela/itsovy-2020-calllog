package sk.itsovy.android.calllog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 34;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_GRANTED) {
            // povolenie uz bolo predtym udelene
            initPhoneNumbers();
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CALL_LOG)) {
                // ak to uz raz bolo odmietnute
                View layout = findViewById(R.id.layout);
                Snackbar.make(layout, "Potrebujem povolenie.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CODE);
                            }
                        }).show();

            } else {
                // prvykrat pytam povolenie
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initPhoneNumbers();
            }
        }

    }

    public void initPhoneNumbers() {
        final TextView textView = findViewById(R.id.callsTextView);

        ViewModelProvider provider = new ViewModelProvider(this);
        CallsViewModel model = provider.get(CallsViewModel.class);

        LiveData<List<Call>> callsLiveData = model.getCalls();
        callsLiveData.observe(this, new Observer<List<Call>>() {
            @Override
            public void onChanged(List<Call> calls) {
                if (calls != null) {
                    textView.setText(calls.toString());
                }
            }
        });

    }
}