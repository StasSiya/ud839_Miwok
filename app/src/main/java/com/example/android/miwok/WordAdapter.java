package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anastasiya on 12.10.2017.
 */

public class WordAdapter extends ArrayAdapter <Word>{

    public WordAdapter (Activity context, ArrayList<Word>words){
        super (context,0, words);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Check if the existing view is being reused, otherwise inflate the view
        //convertView is the
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
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

        return listItemView;

    }
}
