package pt.iul.iscte.daam.fitmeet.account;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import pt.iul.iscte.daam.fitmeet.FIT2Gather;
import pt.iul.iscte.daam.fitmeet.R;

public class AccountActivity extends AppCompatActivity
    implements SignUpOrLoginFragment.AccountFragmentListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account);

    setupActionBar();

    //((FIT2Gather) context).getAccountManager().login();
    if (null == savedInstanceState) {
      initFragment(SignUpOrLoginFragment.newInstance());
    }
  }

  private void initFragment(SignUpOrLoginFragment signUpOrLoginFragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(R.id.accountFrameLayout, signUpOrLoginFragment);
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
