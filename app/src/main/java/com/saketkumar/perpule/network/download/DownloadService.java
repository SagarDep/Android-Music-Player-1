package com.saketkumar.perpule.network.download;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;

public class DownloadService extends IntentService {

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;

    private static final String TAG = "DownloadService";

    private int waitingIntentCount = 0;

    public DownloadService() {
        super(DownloadService.class.getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        waitingIntentCount++;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String url = intent.getStringExtra("url");

        Bundle bundle = new Bundle();

        if (!TextUtils.isEmpty(url)) {
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);

            try {
                Audio songDownloader = Audio.getInstance();
                String results = songDownloader.downloadAudio(getApplicationContext(),url);

                if (!TextUtils.isEmpty(results)) {
                    bundle.putString("result", results);
                    receiver.send(STATUS_FINISHED, bundle);
                }
            } catch (Exception e) {
            }
        }
    }
}
