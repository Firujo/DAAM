package pt.iul.iscte.daam.fitmeet.newevent.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import pt.iul.iscte.daam.fitmeet.R;

/**
 * Displays a new event screen.
 */
public class NewEventActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_newevent);

    setupActionBar();

    if (null == savedInstanceState) {
      initFragment(NewEventFragment.newInstance());
    }
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  private void setupActionBar() {
    ActionBar ab = getSupportActionBar();
    ab.setTitle(getResources().getString(R.string.new_event));
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);
  }

  private void initFragment(Fragment detailFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(R.id.contentFrame, detailFragment);
    transaction.commit();
  }
}
