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

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private MediaPlayer.OnCompletionListener mComplitionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause (); //pause playback
                mMediaPlayer.seekTo(0); //reset player to te start of the file
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaPlayer.start();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        //Create an ArrayList of words in the NumbersActivity
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add (new Word ("Where are you going?","lminto wuksus", R.raw.phrase_where_are_you_going));
        words.add (new Word ("What is your name?","tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add (new Word ("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        words.add (new Word ("How are you feeling?","michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add (new Word ("I’m feeling good.","kuchi achit", R.raw.phrase_im_feeling_good));
        words.add (new Word ("Are you coming?","әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add (new Word ("Yes, I’m coming.","hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add (new Word ("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        words.add (new Word ("Let’s go.","yoowutis", R.raw.phrase_lets_go));
        words.add (new Word ("Come here.","әnni'nem", R.raw.phrase_come_here));

        //Create a Wordadapter, whose data source is a the list of numbers (words)
        //Adapter knows how to create list item views for each item in the list.
        WordAdapter adapter = new WordAdapter (this, words, R.color.category_phrases);

        //get a reference to the ListView and attach adapter to the ListView
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = words.get(position);

                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, currentWord.getAudioRessourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mComplitionListener);
                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        //When the activity is stopped, release the media player resources because we won't
        //be playing any more sounds.

        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            mAudioManager.abandonAudioFocus(mChangeListener);
        }
    }
}
