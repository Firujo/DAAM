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
import com.google.firebase.auth.UserProfileChangeRequest;

import pt.iul.iscte.daam.fitmeet.R;

public class RegisterFragment extends Fragment {

  private Button registerButton;
  private EditText nameEditText;
  private EditText usernameEditText;
  private EditText passwordEditText;
  private EditText passwordConfirmationEditText;
  private OnFragmentInteractionListener mListener;

  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;

  public static RegisterFragment newInstance() {
    RegisterFragment fragment = new RegisterFragment();
    return fragment;
  }

  public RegisterFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    mAuth = FirebaseAuth.getInstance();

    mAuthListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
          if (user.isEmailVerified()) {
            System.out.println("Email is verified");
            System.out.println("onAuthStateChanged:signed_in:" + user.getUid());
          }
          else {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(nameEditText.getText().toString())
                    .build();
            user.updateProfile(profileUpdates);
            user.sendEmailVerification();
            System.out.println("Email is not verified");
          }

        } else {
          System.out.println("onAuthStateChanged:signed_out");
        }
      }
    };

    super.onCreate(savedInstanceState);
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

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_register, container, false);

    registerButton = (Button) view.findViewById(R.id.registerButton);
    nameEditText = (EditText) view.findViewById(R.id.nameEditText);
    usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
    passwordConfirmationEditText = (EditText) view.findViewById(R.id.passwordConfirmationEditText);

    setupListener();
    return view;
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
          tabordaTestRegister(name, username, password);
        }
      }
    });
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
  private void tabordaTestRegister(String name, String username, String password) {
    //// TODO: Taborda test registo here
      mAuth.createUserWithEmailAndPassword(username, password)
              .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  System.out.println("createUserWithEmail:onComplete:" + task.isSuccessful());

                  // If sign in fails, display a message to the user. If sign in succeeds
                  // the auth state listener will be notified and logic to handle the
                  // signed in user can be handled in the listener.
                  if (!task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Login falhado",
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
