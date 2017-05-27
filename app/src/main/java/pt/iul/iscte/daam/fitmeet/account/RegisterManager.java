package pt.iul.iscte.daam.fitmeet.account;

import android.net.nsd.NsdManager;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by filipe on 01-05-2017.
 */

public class RegisterManager {

  public static final int SUCCESSFUL_REGISTRATION = 8;
  public static final int UNSUCCESSFUL_REGISTRATION = 9;

  private FirebaseAuth mAuth;
  private RegisterCredentialsValidator credentialsValidator;
  private FirebaseAuth.AuthStateListener mAuthListener;


  public RegisterManager(RegisterCredentialsValidator registerCredentialsValidator) {
    this.credentialsValidator = registerCredentialsValidator;
  }

  public void initializeFirebase() {
    mAuth = FirebaseAuth.getInstance();
  }

  public void setupAuthListener() {
    mAuthListener = new FirebaseAuth.AuthStateListener() {
      @Override public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
          if (user.isEmailVerified()) {
            System.out.println("Email is verified");
            System.out.println("onAuthStateChanged:signed_in:" + user.getUid());
            System.out.println(user.getDisplayName());
          } else {
            user.sendEmailVerification();
            System.out.println("Email is not verified");
          }
          System.out.println("CORRECT AUTHENTICATION AFTER REGISTER !!!!");
        } else {
          System.out.println("onAuthStateChanged:signed_out");
        }
      }
    };

    mAuth.addAuthStateListener(mAuthListener);
  }

  public void removeAuthListener() {
    if (mAuthListener != null) {
      mAuth.removeAuthStateListener(mAuthListener);
    }
  }

  public void stop() {
    removeAuthListener();
  }

  public void registerNewUser(String name, String username, String password,
      String passwordConfirmation, String birthday, String city,
      RegisterPresenter.RegisterListener listener) {
    int inputsValidationResult =
        credentialsValidator.validate(name, username, password, passwordConfirmation, birthday,
            city);

    if (inputsValidationResult == RegisterCredentialsValidator.SUCCESS) {
      mAuth.createUserWithEmailAndPassword(username, password)
          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override public void onComplete(@NonNull Task<AuthResult> task) {
              System.out.println("createUserWithEmail:onComplete:" + task.isSuccessful());

              // If sign in fails, display a message to the user. If sign in succeeds
              // the auth state listener will be notified and logic to handle the
              // signed in user can be handled in the listener.

              if (!task.isSuccessful()) {
                listener.onCompleteRegistration(UNSUCCESSFUL_REGISTRATION);
              } else {
                listener.onCompleteRegistration(SUCCESSFUL_REGISTRATION);
              }

              // ...
            }
          });

    } else {
      listener.onCompleteRegistration(inputsValidationResult);
    }
  }
}
