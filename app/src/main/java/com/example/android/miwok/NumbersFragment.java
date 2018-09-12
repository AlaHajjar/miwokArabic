package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {


    private MediaPlayer play;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        play.pause();
                        play.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        play.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };
    private MediaPlayer.OnCompletionListener mListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View rootView =  inflater.inflate(R.layout.word_list,container,false);
        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // ArrayList<String> numbreWrd = new ArrayList<String>(); نوع string
        //نوع اوبجكت معرف علعا word
        // Create a list of words
        final ArrayList<Word> numbreWrd = new ArrayList<Word>();
        Word o = new Word("Bir", "one", R.drawable.number_one,
                R.raw.number_bir);
        //ندخل الاوبجكنن الذي انشأناه في الاعلي من word
        numbreWrd.add(o);
        numbreWrd.add(new Word("Iki", "two", R.drawable.number_two,
                R.raw.number_two));
        numbreWrd.add(new Word("Üç", "three", R.drawable.number_three,
                R.raw.number_three));
        numbreWrd.add(new Word("Dört", "four", R.drawable.number_four,
                R.raw.number_four));
        numbreWrd.add(new Word("Beş", "fıve", R.drawable.number_five,
                R.raw.number_five));
        numbreWrd.add(new Word("Altı", "six", R.drawable.number_six,
                R.raw.number_six));
        numbreWrd.add(new Word("Yedi", "seven", R.drawable.number_seven,
                R.raw.number_seven));
        numbreWrd.add(new Word("Sekiz", "eight", R.drawable.number_eight,
                R.raw.number_eight));
        numbreWrd.add(new Word("Dokuz", "nine", R.drawable.number_nine,
                R.raw.number_nine));
        numbreWrd.add(new Word("On", "ten", R.drawable.number_ten,
                R.raw.number_ten));
        //                                   حذفنا كلمة android من قبل R لان اسخدمنا لايوت من صنعنا

        WordAdapter itemsAdapter = new WordAdapter(getActivity(), numbreWrd, R.color.category_numbers);

        // GridView gridView = (gridView) findViewById(R.id.list);انشاء قائمة شبكية
        ListView listView = (ListView)rootView. findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                Word word = numbreWrd.get(i);
                //استخدمنا هي ال logلعرض تفاصيل ال object اللي هو word
                //Log.v("NumberActivity","current word " +
                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int resealt = mAudioManager.requestAudioFocus(mOnAudioChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (resealt == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word
                    play = MediaPlayer.create(getActivity(), word.getaudio());
                    play.start();
                    //اضيط مستمع لما ينتهي الاغنية شو يعمل

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    play.setOnCompletionListener(mListener);
                }
            }
        });






        return rootView;
    }





















    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }
    private void releaseMediaPlayer ()
    {
        if ( play!=null ) {
            play.release();
            play = null;
            mAudioManager.abandonAudioFocus(mOnAudioChangeListener);
        }
    }
}
