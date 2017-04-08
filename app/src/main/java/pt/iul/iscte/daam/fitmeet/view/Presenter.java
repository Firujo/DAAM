package pt.iul.iscte.daam.fitmeet.view;

/**
 * Created by filipe on 08-04-2017.
 */

public interface Presenter {

  void onCreate();

  void onResume();

  void onPause();

  void onDestroy();

  void onStop();

  void onStart();
}
