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

      }
    });
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
