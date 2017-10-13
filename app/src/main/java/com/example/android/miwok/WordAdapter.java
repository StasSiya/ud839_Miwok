package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anastasiya on 12.10.2017.
 */

public class WordAdapter extends ArrayAdapter <Word>{
    //Ressource ID for the background color for this list of words

    private int mColorResourceId;


    public WordAdapter (Activity context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }
        @NonNull
        @Override
        public View getView ( int position, @Nullable View convertView, @NonNull ViewGroup parent){
            //Check if the existing view is being reused, otherwise inflate the view
            //convertView is the
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
                //------------------------------------------------------(layout resource id, the parent view,which is the listview,and
                //false because we dont want to attach listItemView to the parent ListView just yet --why?
            }
            //Get the Word object located at this position in the list
            Word currentWord = getItem(position);

            //Find the TextView in the list_item.xml layout with the default_translation
            TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_text_view);
            //Get the current defualt word from the Word object and set text on the Textview
            defaultTranslation.setText(currentWord.getDefaultTranslation());
            //return super.getView(position, convertView, parent);


            //Find the TextView in the list_item.xml layout with the miwok_translation
            TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_text_view);
            //Get the current Miwok word from the Word object and set text on the Textview
            miwokTranslation.setText(currentWord.getMiwokTranslation());
            //Find the ImageView in the list-item.xml layout with the ID image.

            ImageView image = (ImageView) listItemView.findViewById(R.id.image);
            //Set the ImageView to the Image ressource specified in the current Word
            if (currentWord.hasImage()) {
                //Check if the view is visible
                image.setImageResource(currentWord.getImageRessourceId());
                image.setVisibility(View.VISIBLE);
            } else {
                //if the view is not visible, hide the ImageView
                image.setVisibility(View.GONE);
            }

            // Set the theme color for the list item
            View textContainer = listItemView.findViewById(R.id.text_container);
            // Find the color that the resource ID maps to
            int color = ContextCompat.getColor(getContext(), mColorResourceId);
            // Set the background color of the text container View
            textContainer.setBackgroundColor(color);

            return listItemView;

        }
    }
