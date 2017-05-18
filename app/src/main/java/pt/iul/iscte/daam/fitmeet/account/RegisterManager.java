package pt.iul.iscte.daam.fitmeet.account;

import android.net.nsd.NsdManager;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by filipe on 01-05-2017.
 */

public class RegisterManager {

  public static final int INVALID_INPUTS = 0;
  public static final int SUCCESSFUL_REGISTRATION = 1;
  public static final int UNSUCCESSFUL_REGISTRATION = 2;
  private FirebaseAuth mAuth;
  private RegisterCredentialsValidator credentialsValidator;

  public RegisterManager(RegisterCredentialsValidator registerCredentialsValidator) {
    this.credentialsValidator = registerCredentialsValidator;
  }

  public void initializeFirebase() {
    mAuth = FirebaseAuth.getInstance();
  }

  public void registerNewUser(String name, String username, String password,
      String passwordConfirmation, String birthday, String country, String city,
      RegisterPresenter.RegisterListener listener) {
    boolean validInputs =
        credentialsValidator.validate(name, username, password, passwordConfirmation, birthday,
            country, city);

    if (!validInputs) {
      listener.onCompleteRegistration(INVALID_INPUTS);
    } else {
      mAuth.createUserWithEmailAndPassword(username, password)
          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override public void onComplete(@NonNull Task<AuthResult> task) {
              System.out.println("createUserWithEmail:onComplete:" + task.isSuccessful());

              // If sign in fails, display a message to the user. If sign in succeeds
              // the auth state listener will be notified and logic to handle the
              // signed in user can be handled in the listener.

              if (!task.isSuccessful()) {
                listener.onCompleteRegistration(SUCCESSFUL_REGISTRATION);
              } else {
                listener.onCompleteRegistration(UNSUCCESSFUL_REGISTRATION);
              }

              // ...
            }
          });
    }
  }
}
