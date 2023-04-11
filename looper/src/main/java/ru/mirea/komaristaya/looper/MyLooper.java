package ru.mirea.komaristaya.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TimeUtils;

import java.util.concurrent.TimeUnit;

public	class	MyLooper	extends	Thread{
    public Handler mHandler;
    private	Handler	mainHandler;
    public	MyLooper(Handler	mainThreadHandler)	{
        mainHandler	=mainThreadHandler;
    }

    public	void	run()	{
        Log.d("ru.mirea.komaristaya.looper.MyLooper",	"run");
        Looper.prepare();
        mHandler	=	new	Handler(Looper.myLooper())	{
            public	void	handleMessage(Message msg)	{
                String	data	=	msg.getData().getString("KEY");
                int pop = msg.getData().getInt("Age77");
                Log.d("MyLooper	get	message:	", data);
                Log.d("MyLooper	get	message:", String.valueOf(pop));
                try {
                    TimeUnit.SECONDS.sleep(pop);
                }
                    catch(InterruptedException e){
                    throw new RuntimeException(e);
                    }

                int	count	=	data.length();
                Message	message	=	new	Message();
                Bundle bundle	=	new	Bundle();
                bundle.putString("result",	String.format("My work is %s, my age is %d 	", data, pop));
                message.setData(bundle);
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}