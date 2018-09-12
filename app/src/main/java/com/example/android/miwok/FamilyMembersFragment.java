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
public class FamilyMembersFragment extends Fragment {

    MediaPlayer play;
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
    MediaPlayer.OnCompletionListener mListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                               ViewGroup container,
                                 Bundle savedInstanceState) {
        View rootView  = inflater.inflate(R.layout.word_list,container,false);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> FamilyWord = new ArrayList<Word>();
        FamilyWord.add(new Word("Baba", "الأب", R.drawable.family_father,
                R.raw.family_father));
        FamilyWord.add(new Word("Anne", "الأم", R.drawable.family_mother,
                R.raw.family_mother));
        FamilyWord.add(new Word("kız", "ابنة", R.drawable.family_daughter,
                R.raw.family_daughter));
        FamilyWord.add(new Word("oğul", "ابن", R.drawable.family_son,
                R.raw.family_son));
        FamilyWord.add(new Word("Dada", "الجد", R.drawable.family_grandfather,
                R.raw.family_grandfather));
        FamilyWord.add(new Word("AnaAnne", "الجدة", R.drawable.family_grandmother,
                R.raw.family_grandmother));
        FamilyWord.add(new Word("Abi", "الأخ الأكبر", R.drawable.family_older_brother,
                R.raw.family_older_brother));
        FamilyWord.add(new Word("Abla", "الأخت الكبرى", R.drawable.family_older_sister,
                R.raw.family_older_sister));
        FamilyWord.add(new Word("Kardeş", "الأخ الصغير", R.drawable.family_younger_brother,
                R.raw.family_younger_brother));
        FamilyWord.add(new Word("Kızkardeş", "الأخت الصغيرة", R.drawable.family_younger_sister,
                R.raw.family_younger_sister));
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), FamilyWord, R.color.category_family);
        ListView listView = (ListView)rootView. findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = FamilyWord.get(i);
                int resealt = mAudioManager.requestAudioFocus(mOnAudioChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (resealt==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    play = MediaPlayer.create(getActivity(), word.getaudio());
                    play.start();
                    play.setOnCompletionListener(mListener);
                }
            }
        });
           return  rootView;
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



