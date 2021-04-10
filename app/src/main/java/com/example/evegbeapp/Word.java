package com.example.evegbeapp;

public class Word {
    private final String mEweTranslation;
    private final String mDefaultTranslation;
    private final int mAudioResourceId;
    private int mImageResourceId = No_IMAGE_PROVIDED;
    private static final int No_IMAGE_PROVIDED = 1;

    public Word(String defaultTranslation, String eweTranslation, int audioResourceId){
        mEweTranslation = eweTranslation;
        mDefaultTranslation = defaultTranslation;
        mAudioResourceId = audioResourceId;
    }
    public Word(String defaultTranslation, String eweTranslation, int imageResourceId, int audioResourceId){
        mEweTranslation = eweTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getEweTranslation(){
        return mEweTranslation;
    }
    public int getImageResourceId(){
        return mImageResourceId;
    }
    public boolean hasImage(){
        return mImageResourceId != No_IMAGE_PROVIDED;
    }
    public int getAudioResourceId(){
        return mAudioResourceId;
    }
}
