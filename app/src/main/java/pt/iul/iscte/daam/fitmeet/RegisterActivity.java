package pt.iul.iscte.daam.fitmeet;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by filipe on 29/03/2017.
 */

public class RegisterActivity extends AppCompatActivity {

  private Button registerButton;
  private EditText nameEditText;
  private EditText usernameEditText;
  private EditText passwordEditText;
  private EditText passwordConfirmationEditText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register);

    setupToolbar();

    registerButton = (Button) findViewById(R.id.registerButton);
    nameEditText = (EditText) findViewById(R.id.nameEditText);
    usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    passwordConfirmationEditText = (EditText) findViewById(R.id.passwordConfirmationEditText);

    setupListener();
  }

  private void setupToolbar() {
    ActionBar ab = getSupportActionBar();
    ab.setTitle(getResources().getString(R.string.sign_up));
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);
  }

  private void setupListener() {
    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final String name = nameEditText.getText().toString();
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        final String passwordConfirmation = passwordConfirmationEditText.getText().toString();

        if (validateRegister(name, username, password, passwordConfirmation)) {
          //TODO : Taborda make regist call here.
          tabordaTestRegister(name, username, password, passwordConfirmation);
        }
      }
    });
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

  private boolean validateRegister(String name, String username, String password,
      String passwordConfirmation) {//todo apply MVP + discuss verifications
    if (name.isEmpty()) {
      return false;
    } else if (username.isEmpty()) {
      return false;
    } else if (password.isEmpty()) {
      return false;
    } else if (passwordConfirmation.isEmpty()) {
      return false;
    } else if (!password.equals(passwordConfirmation)) {
      return false;
    }
    return true;
  }

  /**
   * Metodo po Xôr Bruno Taborda testar o registo
   */
  private void tabordaTestRegister(String name, String username, String password,
      String passwordConfirmation) {
    //// TODO: Taborda test registo here
  }

}
