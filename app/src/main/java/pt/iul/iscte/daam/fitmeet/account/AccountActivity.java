package pt.iul.iscte.daam.fitmeet.account;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.events.EventsFragment;

public class AccountActivity extends AppCompatActivity
    implements AccountFragment.AccountFragmentListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account);

    setupActionBar();

    if (null == savedInstanceState) {
      initFragment(AccountFragment.newInstance());
    }
  }

  private void initFragment(AccountFragment accountFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(R.id.accountFrameLayout, accountFragment);
    transaction.commit();
  }

  private void setupActionBar() {
    ActionBar ab = getSupportActionBar();
    ab.setTitle(getResources().getString(R.string.my_account));
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  @Override public void onRegisterPressed() {
    Toast.makeText(this, "pressed register", Toast.LENGTH_SHORT).show();
  }

  @Override public void onLoginPressed() {
  }
}
