package pt.iul.iscte.daam.fitmeet.eventdetail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import pt.iul.iscte.daam.fitmeet.R;

/**
 * Created by jdandrade on 07/02/2017.
 */

public class EventDetailActivity extends AppCompatActivity {

  public static final String EXTRA_EVENT_ID = "EVENT_ID";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    //setContentView(R.layout.activity_detail);

    // Set up the toolbar.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ActionBar ab = getSupportActionBar();
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);

    // Get the requested event id
    String eventId = getIntent().getStringExtra(EXTRA_EVENT_ID);

    //initFragment(EventDetailFragment.newInstance(eventId));
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
