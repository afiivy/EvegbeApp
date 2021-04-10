package com.example.evegbeapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class FamilyFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public FamilyFragment() {
        // Required empty public constructor
    }
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState){
            View rootView = inflater.inflate(R.layout.word_list, container, false);
            mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            // Inflate the layout for this fragment
            final ArrayList<Word> words = new ArrayList<>();
            words.add(new Word("mother", "dada",  R.raw.mother));
            words.add(new Word("father", "fofo", R.raw.father));
           // words.add(new Word("older aunt", "dadia",  R.raw.aunt));
            words.add(new Word("aunt", "dadia",  R.raw.aunt));
            words.add(new Word("uncle", "tuga", R.raw.uncle));
            words.add(new Word("daughter", "vinyunu", R.raw.daughter));
            words.add(new Word("son", "vinutsu", R.raw.son));
            words.add(new Word("sister","nuvinyunu",R.raw.sister));
            words.add(new Word("brother","nuvinutsu",R.raw.brother));
            words.add(new Word("grandmother","mama",R.raw.grandmother));
            words.add(new Word("grandfather","torgbui",R.raw.grandfather));
            WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);
            ListView listView = rootView.findViewById(R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    releaseMediaPlayer();
                    Word word = words.get(position);
                    int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                        mMediaPlayer.start();
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }
            });
            return rootView;
        }
        @Override
        public void onStop () {
            super.onStop();
            releaseMediaPlayer();
        }

        private void releaseMediaPlayer () {
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }

    }
