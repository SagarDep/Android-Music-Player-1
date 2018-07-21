package com.saketkumar.perpule.ui.main.adapter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.saketkumar.perpule.R;
import com.saketkumar.perpule.data.model.App;

import java.io.IOException;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<App> mAndroidList;
    private MediaPlayer mPlayer;

    public DataAdapter(ArrayList<App> androidList) {
        mAndroidList = androidList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTvDesc.setText(mAndroidList.get(position).getDesc());
        holder.mTvAudio.setText(mAndroidList.get(position).getAudio());
        holder.mIbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPlayer = new MediaPlayer();
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

                if (holder.mIbPlay.getTag().equals("play") ) {
                    holder.mIbPlay.setImageResource(R.drawable.ic_pause_24dp);
                    try {
                        mPlayer.setDataSource(mAndroidList.get(position).getAudio());
                        mPlayer.prepare();
                        mPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    holder.mIbPlay.setImageResource(R.drawable.ic_play_24dp);
                    mPlayer.pause();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvDesc,mTvAudio;
        private ImageButton mIbPlay;
        public ViewHolder(View view) {
            super(view);

            mTvDesc = (TextView)view.findViewById(R.id.tv_desc);
            mTvAudio = (TextView)view.findViewById(R.id.tv_audio);
            mIbPlay = (ImageButton)view.findViewById(R.id.ib_play);
        }
    }
}
