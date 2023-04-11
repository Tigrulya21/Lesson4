package ru.mirea.komaristaya.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import ru.mirea.komaristaya.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Integer lessons;
    private Integer days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText EditText1 = findViewById(R.id.Day);
        EditText EditText2 = findViewById(R.id.Lesson);
        TextView Rezult = findViewById(R.id.Rez);


        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
        // Меняем имя и выводим в текстовом поле
        mainThread.setName("МОЙ НОМЕР ГРУППЫ: 01-20, НОМЕР ПО СПИСКУ: 13, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Хористы");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack:	" + Arrays.toString(mainThread.getStackTrace()));


        //Задание 2.5
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessons = Integer.parseInt(EditText2.getText().toString());
                days = Integer.parseInt(EditText1.getText().toString());
                int num = lessons/days;
                Rezult.setText("Среднее кол-во пар в день: " + num);
            }
        });


        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
}