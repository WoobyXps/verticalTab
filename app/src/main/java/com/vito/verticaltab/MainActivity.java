package com.vito.verticaltab;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        VerticalTabView tabView = new VerticalTabView(this);
        tabView.initVerticalTabView();
    }
}
