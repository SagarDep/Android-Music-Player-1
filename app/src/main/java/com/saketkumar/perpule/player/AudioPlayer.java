package com.saketkumar.perpule.player;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;

import com.saketkumar.perpule.data.model.App;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class AudioPlayer implements Player, MediaPlayer.OnPreparedListener {
    MediaPlayer mediaPlayer;
    Context context;
    public AudioPlayer(Context context) {

        this.context = context;
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override public void prepare() {

    }

    @Override
    public void play(App song) {
        try {
            mediaPlayer.reset();

            File file = getLocalFilePath(song);

            mediaPlayer.setDataSource(file.getAbsolutePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private File getLocalFilePath(App song) throws MalformedURLException {
        URL url = new URL(song.getAudio());

        String[] segments = url.getPath().split("/");
        String idStr = segments[segments.length-1];

        return new File(context.getCacheDir(), idStr);
    }

    @Override public void pause() {
        mediaPlayer.pause();
    }

    @Override public void release() {
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override public void onPrepared(MediaPlayer mediaPlayer) {

    }
}
