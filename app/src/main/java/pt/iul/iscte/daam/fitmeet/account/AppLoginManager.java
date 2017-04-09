package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by filipe on 08-04-2017.
 */

public class AppLoginManager {

  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;

  public static final int VALID_FIELDS = 0;
  public static final int EMPTY_FIELDS = 1;
  public static final int WRONG_COMBINATION = 2;

  public AppLoginManager() {
  }

  public void initializeAuth() {
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

  public void login(String username, String password, LoginListener loginListener) {
    int validationResult = validateLogin(username, password);
    if (validationResult != VALID_FIELDS) {
      loginListener.onError(validationResult);
    }

    signIn(username, password, loginListener);
  }

  private void signIn(String username, String password, LoginListener loginListener) {
    mAuth.signInWithEmailAndPassword(username, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            System.out.println("signInWithEmail:onComplete:" + task.isSuccessful());

            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
              System.out.println("signInWithEmail:failed" + task.getException());
              loginListener.onError(WRONG_COMBINATION);
            } else {
              loginListener.onSuccess();
            }

            // ...
          }
        });
  }

  private int validateLogin(String username,
      String password) {//// TODO:  return int and check != success + discuss login constraints
    if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
      return EMPTY_FIELDS;
    }
    return VALID_FIELDS;
  }

  public void stop() {
    removeAuthListener();
  }

  interface LoginListener {
    void onSuccess();

    void onError(int error);
  }
}
