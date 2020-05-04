package com.example.aplikasiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button mSetBtn;
    private Button mUnsetBtn;
    private RadioButton mMenitRadio;
    private RadioButton mLimaRadio;
    private RadioButton mTigaPuluhRadio;
    private RadioButton mJamRadio;
    private RadioGroup mTimeRadioRadio;
    public int mChangeTime = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSetBtn = (Button) findViewById(R.id.btnSet);
        mUnsetBtn = (Button) findViewById(R.id.btnUnset);
        mMenitRadio = (RadioButton) findViewById(R.id.radio0);
        mLimaRadio = (RadioButton) findViewById(R.id.radio1);
        mTigaPuluhRadio = (RadioButton) findViewById(R.id.radio2);
        mJamRadio = (RadioButton) findViewById(R.id.radio3);
        mTimeRadioRadio = (RadioGroup) findViewById(R.id.radioGroup);

        mUnsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mDisable = new Intent(MainActivity.this, WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });

        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRadioID = mTimeRadioRadio.getCheckedRadioButtonId();
                if (mMenitRadio.getId()==mRadioID) {mChangeTime=60;}
                else if (mLimaRadio.getId()==mRadioID) {mChangeTime=60;}
                else if (mTigaPuluhRadio.getId()==mRadioID) {mChangeTime=60;}
                else if (mJamRadio.getId()==mRadioID) {mChangeTime=60;}

                Intent mService = new Intent(MainActivity.this, WallpaperChangeService.class);

                Bundle mBundleTime = new Bundle();
                mBundleTime.putInt("durasi", mChangeTime);

                mService.putExtras(mBundleTime);

                startService(mService);

                finish();

            }
        });
    }
}
