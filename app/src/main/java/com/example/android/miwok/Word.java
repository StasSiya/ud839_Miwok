package com.example.android.miwok;

/**
 * Created by Anastasiya on 12.10.2017.
 */
//This class represents a vocab word that the user wants to learn.
// It consist of miwok and default translation of the word.

public class Word {
    //default translation
    private String mMiwokTranslation;
    //miwok translation - private because they need to be accessible only within the class - to indicate that, we ad "m"
    private String mDefaultTranslation;

//Constructs a new Word with initial values for miwok and default translation.
    public Word (String miwokTranslation, String defaultTranslation){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;

    }
    //Get the default translation of the word
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    //Get the Miwok translation of the word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
}
