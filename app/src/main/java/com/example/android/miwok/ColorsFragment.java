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
public class ColorsFragment extends Fragment {
    MediaPlayer play;
    private AudioManager mAudioManager;

    MediaPlayer.OnCompletionListener mListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

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

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list,container,false);

            mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            final ArrayList<Word> ColorWord = new ArrayList<Word>();
            ColorWord.add(new Word("Kırmızı", "احمر", R.drawable.color_red,
                    R.raw.color_red));
            ColorWord.add(new Word("Siyah", "اسود", R.drawable.color_black,
                    R.raw.color_black));

            ColorWord.add(new Word("Yeşil", "اخضر", R.drawable.color_green,
                    R.raw.color_brown));

            ColorWord.add(new Word("Beyaz", "ابيض", R.drawable.color_white,
                    R.raw.color_dusty_yellow));

            ColorWord.add(new Word("Bej", "بيج", R.drawable.color_dusty_yellow,
                    R.raw.color_gray));

            ColorWord.add(new Word("sarı", "اصفر", R.drawable.color_mustard_yellow,
                    R.raw.color_green));

            ColorWord.add(new Word("Kahve", "بني", R.drawable.color_brown,
                    R.raw.color_white));

            ColorWord.add(new Word("Gri", "فضي", R.drawable.color_gray,
                    R.raw.color_mustard_yellow));

            WordAdapter itemsAdapter = new WordAdapter(getActivity(), ColorWord, R.color.category_colors);
            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(itemsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Word word = ColorWord.get(i);
                    int resealt = mAudioManager.requestAudioFocus(mOnAudioChangeListener,
                            AudioManager.STREAM_MUSIC,
                            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (resealt == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        releaseMediaPlayer();
                        play = MediaPlayer.create(getActivity(), word.getaudio());
                        play.start();
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
