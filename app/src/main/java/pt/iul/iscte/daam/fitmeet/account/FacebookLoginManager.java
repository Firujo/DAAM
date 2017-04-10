package pt.iul.iscte.daam.fitmeet.account;

import android.content.Intent;
import android.support.annotation.NonNull;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by filipe on 09-04-2017.
 */

public class FacebookLoginManager {
  private FirebaseAuth mAuth;
  private CallbackManager facebookCallbackManager;

  public static final int FACEBOOK_LOGIN_ERROR = 0;

  public FacebookLoginManager(FirebaseAuth mAuth) {
    this.mAuth = mAuth;
  }

  public void initializeLoginControls() {
    facebookCallbackManager = CallbackManager.Factory.create();
  }

  public void setupFacebookCallback(FacebookLoginStatusListener statusListener) {
    LoginManager.getInstance()
        .registerCallback(facebookCallbackManager, new FacebookCallback<LoginResult>() {
          @Override public void onSuccess(LoginResult loginResult) {
            System.out.println("Success facebook");
            handleFacebookAccessToken(loginResult.getAccessToken());
          }

          @Override public void onCancel() {
            System.out.println("canceled login on facebook;");
          }

          @Override public void onError(FacebookException error) {
            System.out.println("error while loggin on facebook - " + error);
          }
        });
  }

  private void handleFacebookAccessToken(
      AccessToken accessToken) {//FIXME auth needs to be the same as it is in appLoginManager due to the authStateListener
    AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
    mAuth.signInWithCredential(credential)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override public void onComplete(@NonNull Task<AuthResult> task) {
            // If sign in fails, display a message to the user. If sign in succeeds
            // the auth state listener will be notified and logic to handle the
            // signed in user can be handled in the listener.
            if (!task.isSuccessful()) {
              System.out.println("could not login facebook");
            } else {
              System.out.println("Logged in facebook;");
            }
          }
        });
  }

  public void onResult(int requestCode, int resultCode, Intent data) {
    facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
  }

  interface FacebookLoginStatusListener {
    void onSuccess();

    void onError(int error);
  }
}
