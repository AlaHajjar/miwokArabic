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
public class PhrasesFragment extends Fragment {
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
       View rootView = inflater.inflate(R.layout.word_list,container,false);

            mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
            final ArrayList<Word> PhrasesWord = new ArrayList<Word>();
            PhrasesWord.add(new Word("Nasılsın", "كيف حالك",
                    R.raw.phrase_are_you_coming));
            PhrasesWord.add(new Word("İyi Günler!", "ايامك صعيدة",
                    R.raw.phrase_are_you_coming));
            PhrasesWord.add(new Word("Kolay Gelsin", "يعطيك العافية",
                    R.raw.phrase_my_name_is));

            PhrasesWord.add(new Word("Hayirli İşler", "اعمال موفقة ",
                    R.raw.phrase_lets_go));

            PhrasesWord.add(new Word("Başınıza Sağ Olsun", "سلامة راسكن",
                    R.raw.phrase_where_are_you_going));

            PhrasesWord.add(new Word("Hayirli Cumalar", "جمعة مباركة",
                    R.raw.phrase_im_feeling_good));

            PhrasesWord.add(new Word("Allah Rahmat Eylesin", "الله يررحمه",
                    R.raw.phrase_how_are_you_feeling));

            PhrasesWord.add(new Word("Gözünüz Aydın", "مبارك ما اجاكن",
                    R.raw.phrase_im_coming));
            PhrasesWord.add(new Word("Güle Güle kullanın", "تستعمله بالعافية",
                    R.raw.phrase_come_here));
            PhrasesWord.add(new Word("Yolunuz açık Olsun", "الله يفنح عليك",
                    R.raw.phrase_are_you_coming));
            WordAdapter itemsAdapter = new WordAdapter(getActivity(), PhrasesWord,R.color.category_phrases);
            ListView listView = (ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(itemsAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Word word = PhrasesWord.get(i);
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
