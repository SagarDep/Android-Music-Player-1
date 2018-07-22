package com.saketkumar.perpule.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.saketkumar.perpule.R;
import com.saketkumar.perpule.data.model.App;
import com.saketkumar.perpule.ui.main.MusicPlayerActivity;

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context , MusicPlayerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Intent.EXTRA_TEXT, mAndroidList);
                bundle.putInt("POSITION", position);
                intent.putExtras(bundle);

                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        holder.mTvDesc.setText(mAndroidList.get(position).getDesc());
        holder.mIbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.mIbPlay.getTag().toString().equals("play")) {
                    try {
                        mPlayer = new MediaPlayer();
                        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mPlayer.setDataSource(mAndroidList.get(position).getAudio());
                        mPlayer.prepare();
                        mPlayer.start();
                        holder.mIbPlay.setTag("pause");
                        holder.mIbPlay.setImageResource(R.drawable.ic_pause_circle_24dp);
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
                else if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                    holder.mIbPlay.setTag("play");
                    holder.mIbPlay.setImageResource(R.drawable.ic_play_circle_24dp);
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
        private ImageView mIbPlay,mIbPause;
        public ViewHolder(View view) {
            super(view);

            mTvDesc = (TextView)view.findViewById(R.id.tv_desc);
            mIbPlay = (ImageView) view.findViewById(R.id.ib_play_circle);
//            mIbPause = (ImageView)view.findViewById(R.id.ib_pause_circle);

        }
    }
}
