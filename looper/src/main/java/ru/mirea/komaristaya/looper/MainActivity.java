package ru.mirea.komaristaya.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import ru.mirea.komaristaya.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    MyLooper myLooper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task	execute.	This	is	result:	" + msg.getData().getString("result"));
            }
        };

        myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();
    }

    public	void onInf(View v)	{
        Message	msg	=	Message.obtain();
        Bundle	bundle	=	new	Bundle();
        bundle.putString("KEY",	"Репетитор");
        bundle.putInt("Age77", 20);
        msg.setData(bundle);
        myLooper.mHandler.sendMessage(msg);
    }
}


