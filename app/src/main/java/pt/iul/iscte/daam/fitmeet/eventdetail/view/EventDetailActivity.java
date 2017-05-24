package pt.iul.iscte.daam.fitmeet.eventdetail.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import pt.iul.iscte.daam.fitmeet.R;

/**
 * Created by jdandrade on 07/02/2017.
 */

public class EventDetailActivity extends AppCompatActivity {

  public static final String EXTRA_EVENT_ID = "EVENT_ID";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_event_detail);

    setupActionBar();

    // Get the requested event id
    String eventId = getIntent().getStringExtra(EXTRA_EVENT_ID);

    initFragment(EventDetailFragment.newInstance(eventId));
  }

  private void setupActionBar() {
    ActionBar ab = getSupportActionBar();
    ab.setTitle(getResources().getString(R.string.new_event));
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  private void initFragment(Fragment detailFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(R.id.contentFrame, detailFragment);
    transaction.commit();
  }
}
