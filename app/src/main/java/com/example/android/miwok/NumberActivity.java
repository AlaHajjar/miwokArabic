package com.example.android.miwok;import android.annotation.SuppressLint;import android.content.Context;import android.media.AudioManager;import android.media.MediaPlayer;import android.os.Bundle;import android.support.v7.app.AppCompatActivity;import android.view.View;import android.widget.AdapterView;import android.widget.ListView;import java.util.ArrayList;  public class NumberActivity extends AppCompatActivity {    @SuppressLint("ResourceAsColor")    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_category);        getSupportFragmentManager().beginTransaction()                .replace(R.id.container, new NumbersFragment())                .commit();    }    }//creat an array//array start with 0//array type is string in blow/** * String[] words = new String[10]; * words[0] = "one"; * words[1] = "two"; * words[2] = "three"; * words[3] = "four"; * words[4] = "five"; * words[5] = "six"; * words[6] = "seven"; * words[7] = "eight"; * words[8] = "nine"; * words[9] = "ten"; * <p> * Log.v("NumberActivity","element stored by :"+numbreWrd.get(0)); * Log.v("NumberActivity","element stored by :"+numbreWrd.get(1)); * Log.v("NumberActivity","element stored by :"+numbreWrd.get(2)); * Log.v("NumberActivity","element stored by :"+numbreWrd.get(3)); int index  = 0;// نعين قيمة للعداد * while (index <numbreWrd.size()){ * TextView wordView = new TextView(this);//لازم هذا السطر يكون هون ضروري مو فوق والا لن يتم انشاء ال ال view * wordView.setText(numbreWrd.get(index)); * rootView.addView(wordView); * index++;} **///اظهار رسالة االسجل لاحضار القيم المخزنة في الخان رقم 0 او 1 او 2 او الخ//لايقبل اارسايل العلربي//عند ظهور الرسالة يرجا حذف ال log  لامه يحجز مساحة بالذاكرة// Log.v("NumberActivity", "words at index : " + words[0]);//Log.v("NumberActivity", "words at index:" + words[1]);//creat an ArrayList// remove = delete one element//numbreWrd.remove("four");//اطبعلي شو مخزن في الخانة رقم 0 لايتم ظهرها الا اذا ضغطت علا ال number في التطبيق/** Log.v("NumberActivity","element stored by :"+numbreWrd.get(0)); Log.v("NumberActivity","element stored by :"+numbreWrd.get(1)); Log.v("NumberActivity","element stored by :"+numbreWrd.get(2)); Log.v("NumberActivity","element stored by :"+numbreWrd.get(3));**///LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);//creat a while loop/** int index  = 0;// نعين قيمة للعداد while (index <numbreWrd.size()){ TextView wordView = new TextView(this);//لازم هذا السطر يكون هون ضروري مو فوق والا لن يتم انشاء ال ال view wordView.setText(numbreWrd.get(index)); rootView.addView(wordView); index++;}**/