package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.widget.Toast;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.utils.SharedPreferencesUtils;
import pt.iul.iscte.daam.fitmeet.view.View;

public class AccountActivity extends View
    implements AccountActivityView, SignUpOrLoginFragment.AccountFragmentListener {

  private AccountPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account);

    setupActionBar();

    presenter = new AccountPresenter(new LoginStatusManager(
        getSharedPreferences(SharedPreferencesUtils.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)),
        this);
    attachPresenter(presenter);
  }

  private void initFragment(Fragment fragment) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(R.id.accountFrameLayout, fragment);
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

  @Override public void navigateToSignUpOrLogin() {
    initFragment(SignUpOrLoginFragment.newInstance());
  }

  @Override public void navigateToLoggedIn() {
    initFragment(LoggedInFragment.newInstance());

  }
}
