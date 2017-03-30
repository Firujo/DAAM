package pt.iul.iscte.daam.fitmeet;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by filipe on 28/03/2017.
 */

public class LoginActivity extends AppCompatActivity {

  private Button loginButton;
  private EditText usernameEditText;
  private EditText passwordEditText;
  private TextView registerTextView;


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    setupActionBar();

    loginButton = (Button) findViewById(R.id.loginButton);
    usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    registerTextView = (TextView) findViewById(R.id.registerTextView);

    setupListeners();
  }

  private void setupActionBar() {
    ActionBar ab = getSupportActionBar();
    ab.setTitle(getResources().getString(R.string.login));
    ab.setDisplayHomeAsUpEnabled(true);
    ab.setDisplayShowHomeEnabled(true);
  }

  private void setupListeners() {
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });

    registerTextView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
      }
    });
  }

  @Override public boolean onSupportNavigateUp() {
    onBackPressed();
    return true;
  }

}
