package com.saketkumar.perpule.network.download;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Audio {
    private static final Audio ourInstance = new Audio();

    public static Audio getInstance() {
        return ourInstance;
    }

    private Audio() {
    }

    public String downloadAudio(Context context, String requestUrl) throws IOException,
                                                                            DownloadException {
        int count;
        String idStr = "-1";
        try {
            URL url = new URL(requestUrl);

            String[] segments = url.getPath().split("/");
            idStr = segments[segments.length-1];

            File file = new File(context.getCacheDir(), idStr);

            if(!file.exists()) {
                URLConnection conexion = url.openConnection();
                conexion.connect();
                int lenghtOfFile = conexion.getContentLength();
                InputStream input = new BufferedInputStream(url.openStream());

                file.createNewFile();
                OutputStream output = new FileOutputStream(file);
                byte data[] = new byte[1024];
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new DownloadException("Error Occured");
        }
        return idStr;
    }
}
