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
    //Image resource ID for the word;
    private int mImageRessourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

//Constructs a new Word with initial values for miwok and default translation.
    public Word (String miwokTranslation, String defaultTranslation){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
    }

    //Constructs a new Word with initial values for miwok and default translation.
    public Word (String miwokTranslation, String defaultTranslation, int imageRessourceId){
        mMiwokTranslation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageRessourceId = imageRessourceId;
    }

    //Get the default translation of the word
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
    //Get the Miwok translation of the word
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageRessourceId (){
        return mImageRessourceId;
    }

    //returns whether or not there is an image
    public boolean hasImage (){
        return mImageRessourceId != NO_IMAGE_PROVIDED;
    }
}
