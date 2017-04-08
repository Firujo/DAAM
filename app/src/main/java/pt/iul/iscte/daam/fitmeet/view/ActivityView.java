package pt.iul.iscte.daam.fitmeet.view;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by filipe on 08-04-2017.
 */

public class ActivityView extends AppCompatActivity {

  private Presenter presenter;

  protected void attachPresenter(Presenter presenter) {
    this.presenter = presenter;
    presenter.onCreate();
  }

  @Override protected void onPause() {
    super.onPause();
    presenter.onPause();
  }

  @Override protected void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override protected void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override protected void onStop() {
    super.onStop();
    presenter.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.onDestroy();
  }
}
