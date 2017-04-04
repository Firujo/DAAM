package pt.iul.iscte.daam.fitmeet.account;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pt.iul.iscte.daam.fitmeet.R;

public class LoginFragment extends Fragment {

  private Button loginButton;
  private EditText usernameEditText;
  private EditText passwordEditText;

  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;

  private OnFragmentInteractionListener mListener;

  public LoginFragment() {
  }

  public static LoginFragment newInstance() {
    LoginFragment fragment = new LoginFragment();
    return fragment;
  }


  @Override public void onCreate(Bundle savedInstanceState) {

    mAuth = FirebaseAuth.getInstance();

    mAuthListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
          System.out.println("onAuthStateChanged:signed_in:" + user.getUid());
        } else {
          System.out.println("onAuthStateChanged:signed_out");
        }
      }
    };
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login, container, false);

    loginButton = (Button) view.findViewById(R.id.loginButton);
    usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);

    setupListeners();
    return view;
  }

  @Override
  public void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
  }

  @Override
  public void onStop() {
    super.onStop();
    if (mAuthListener != null) {
      mAuth.removeAuthStateListener(mAuthListener);
    }
  }

  private void setupListeners() {
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if (validateLogin(username, password)) {
          tabordaLoginTeste(username, password);
        }
      }
    });
  }

  private boolean validateLogin(String username,
      String password) {//// TODO: apply MVP + discuss login constraints
    if (!username.isEmpty() && !password.isEmpty()) {
      return true;
    }
    return false;
  }

  /**
   * Metodo po Xô Bruno Taborda
   */
  private void tabordaLoginTeste(String username, String password) {
    //todo taborda test here
    System.out.println("testing login with : " + username + " ; " + password);

    mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                System.out.println("signInWithEmail:onComplete:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                  System.out.println("signInWithEmail:failed" + task.getException());
                  Toast.makeText(getActivity(), "Email or password is invalid",
                          Toast.LENGTH_SHORT).show();
                }
                else{
                  Toast.makeText(getActivity(), "Login com sucesso",
                          Toast.LENGTH_SHORT).show();
                }

                // ...
              }
            });
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
