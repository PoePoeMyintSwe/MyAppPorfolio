package com.poepoemyintswe.myappportfolio;

import android.os.Bundle;

public class SpotifyActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_spotify;
  }

  @Override protected boolean getHomeUpEnabled() {
    return true;
  }

  @Override protected boolean needToolbar() {
    return true;
  }
}
