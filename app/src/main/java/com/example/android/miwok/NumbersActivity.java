/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

    //Create an ArrayList of words in the NumbersActivity
    ArrayList <String> words = new ArrayList<String>();
    words.add ("one");
    words.add ("two");
    words.add ("three");
    words.add ("four");
    words.add ("five");
    words.add ("six");
    words.add ("seven");
    words.add ("eigth");
    words.add ("nine");
    words.add ("ten");
        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

        /*Create a counter variable to keep track of the index position
        int i = 0;
        while (i<words.size()){
            //Create a new TextView
            TextView wordView = new TextView (this);
            wordView.setText(words.get(i));
            //Add the TextView as a child to the
            rootView.addView(wordView);
            //update counter
            i++;
        }*/

        for (int index=0;index<words.size();index++ ){
            TextView wordView = new TextView (this);

            wordView.setText(words.get(index));
            //Add the TextView as a child to the rootView
            rootView.addView(wordView);
            Log.v("NumbersActivity", "The wors is " + words.get(index));
        }




    }
}
