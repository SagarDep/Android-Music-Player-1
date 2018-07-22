package com.saketkumar.perpule.ui.main;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.hanks.htextview.base.HTextView;
import com.saketkumar.perpule.R;
import com.saketkumar.perpule.data.model.App;

import java.io.IOException;
import java.util.List;


public class MusicPlayerActivity extends AppCompatActivity {

    private int Clicked = 0;
    private int prevClicked = 0;

    private ImageView back;
    private ImageView play;
    private ImageView prev;
    private ImageView next;

    private MediaPlayer mMediaPlayer;

    private boolean isStarted = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplayer);
        play = (ImageView) findViewById(R.id.iv_play);
        next = (ImageView) findViewById(R.id.iv_next);
        prev = (ImageView) findViewById(R.id.iv_prev) ;

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1300);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        findViewById(R.id.iv_music_note).startAnimation(rotateAnimation);

        mMediaPlayer = new MediaPlayer();

        play.setOnClickListener(onButtonClick);


        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        int position = bundle.getInt("POSITION");
        List<App> list = (List<App>)bundle.getSerializable(Intent.EXTRA_TEXT);

        String desc = list.get(position % 6).getDesc();
        String url = list.get(position % 6).getAudio();

        final HTextView tvsplash = (HTextView) findViewById(R.id.player);
        tvsplash.animateText(desc);

        try {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            play.setImageResource(R.drawable.ic_pause_24dp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clicked = Clicked + 1;
                int position = bundle.getInt("POSITION") + Clicked;
                List<App> list = (List<App>)bundle.getSerializable(Intent.EXTRA_TEXT);
                String nextDesc = list.get((position) % 6).getDesc();
                String nextAudio = list.get((position) % 6).getAudio();
                stopPlay();
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                //position = position + 1;
                mMediaPlayer.pause();
                tvsplash.animateText(nextDesc);
                try {
                    mMediaPlayer.setDataSource(nextAudio);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause_24dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevClicked = prevClicked + 1;
                int position = bundle.getInt("POSITION") - prevClicked;
                if (prevClicked == 5 ) {
                    position = bundle.getInt("POSITION");
                }
                List<App> list = (List<App>)bundle.getSerializable(Intent.EXTRA_TEXT);
                String nextDesc = list.get((position) % 6).getDesc();
                String nextAudio = list.get((position) % 6).getAudio();

                stopPlay();
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mMediaPlayer.pause();
                tvsplash.animateText(nextDesc);
                try {
                    mMediaPlayer.setDataSource(nextAudio);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.ic_pause_24dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        back = (ImageView) findViewById(R.id.iv_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void startPlay(String url) {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        play.setImageResource(R.drawable.ic_pause_24dp);
        isStarted = true;
    }

    private void stopPlay() {
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        play.setImageResource(R.drawable.ic_play_24dp);

        isStarted = false;
    }

    private View.OnClickListener onButtonClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_play: {
                    if (mMediaPlayer.isPlaying()) {
                        mMediaPlayer.pause();
                        play.setImageResource(R.drawable.ic_play_24dp);
                    } else {
                        if (isStarted) {
                            mMediaPlayer.start();
                            play.setImageResource(R.drawable.ic_pause_24dp);
                        }
                    }
                    break;
                }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.reset();

        mMediaPlayer = null;
    }
}
