package com.example.aplikasiservice;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

public class WallpaperChangeService extends Service implements Runnable{

    private int wallpaperid[] = {R.drawable.wallpaper1, R.drawable.wallpaper2};
    private int waktu;
    private int FLAG=0;
    private Bitmap gambar;
    private Bitmap gambar1;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flag, int startid){
        super.onStartCommand(intent,flag,startid);

        Bundle bundle = intent.getExtras();

        waktu = bundle.getInt("durasi");
        gambar = BitmapFactory.decodeResource(getResources(), wallpaperid[0]);
        gambar1 = BitmapFactory.decodeResource(getResources(), wallpaperid[1]);

        Thread t = new Thread(WallpaperChangeService.this);
        t.start();
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void run() {
        WallpaperManager myWallpaper;
        myWallpaper = WallpaperManager.getInstance(this);
        try {
            while (true) {
                if (FLAG == 0) {
                    myWallpaper.setBitmap(gambar);
                    FLAG++;
                } else {
                    myWallpaper.setBitmap(gambar1);
                    FLAG--;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
