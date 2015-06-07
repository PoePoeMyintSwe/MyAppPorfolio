package com.poepoemyintswe.myappportfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by poepoe on 8/6/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
  private Toolbar toolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(getLayoutResource());
    if (needToolbar()) {
      toolbar = (Toolbar) findViewById(R.id.toolbar);

      if (toolbar != null) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(getHomeUpEnabled());
      }
    }
  }

  protected abstract int getLayoutResource();

  protected abstract boolean getHomeUpEnabled();

  protected abstract boolean needToolbar();
}
