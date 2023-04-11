package ru.mirea.komaristaya.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).build();
        Constraints constraints_net = new  Constraints.Builder().
                setRequiredNetworkType(NetworkType.CONNECTED).build();
        WorkRequest workRequest = new OneTimeWorkRequest.Builder(UploadWorker.class).
                setConstraints(constraints_net).build();

        WorkManager.getInstance(this).enqueue(uploadWorkRequest);
        WorkManager.getInstance(this).enqueue(workRequest);
    }
}


