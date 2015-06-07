package com.poepoemyintswe.myappportfolio;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {
  private Button[] mButtons;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mButtons = new Button[6];
    for (int i = 0; i < mButtons.length; i++) {
      String b = "button" + (i + 1);
      mButtons[i] = (Button) findViewById(getResources().getIdentifier(b, "id", getPackageName()));
      mButtons[i].setOnClickListener(this);
    }
  }

  @Override protected int getLayoutResource() {
    return R.layout.activity_main;
  }

  @Override protected boolean getHomeUpEnabled() {
    return false;
  }

  @Override protected boolean needToolbar() {
    return true;
  }

  /**
   * Called when a view has been clicked.
   *
   * @param v The view that was clicked.
   */
  @Override public void onClick(View v) {
    for (int i = 0; i < mButtons.length; i++) {
      if (v.getId() == mButtons[i].getId()) {
        Toast.makeText(MainActivity.this,
            getResources().getString(R.string.toast_message) + " " + mButtons[i].getText()
                .toString()
                .toLowerCase(), Toast.LENGTH_SHORT).show();
      }
    }
  }
}
