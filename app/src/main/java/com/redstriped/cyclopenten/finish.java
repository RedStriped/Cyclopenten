package com.redstriped.cyclopenten;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.cyclopenten.redstriped.cyclopenten.R;

/**
 * Created by Malte on 23.02.2017.
 */
public class finish  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
    }

    public void onSplashPageClick(View view) {
        finish();
    }
}
