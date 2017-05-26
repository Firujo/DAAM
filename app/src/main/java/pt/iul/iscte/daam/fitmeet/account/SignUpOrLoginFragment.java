package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Arrays;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.view.FragmentView;

public class SignUpOrLoginFragment extends FragmentView implements SignUpOrLoginView {

  private AccountFragmentListener signUpOrLoginFragmentListener;
  private Button registerButton;
  private Button loginButton;
  private LoginButton facebookLoginButton;
  private FirebaseAuth firebaseAuth;
  private SignUpOrLoginPresenter presenter;

  public SignUpOrLoginFragment() {
  }

  public static SignUpOrLoginFragment newInstance() {
    return new SignUpOrLoginFragment();
  }


  @Override public void onCreate(Bundle savedInstanceState) {
    FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
    firebaseAuth = FirebaseAuth.getInstance();
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_signuporlogin, container, false);
    registerButton = (Button) view.findViewById(R.id.registerFragmentButton);
    loginButton = (Button) view.findViewById(R.id.loginFragmentButton);

    facebookLoginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
    facebookLoginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
    facebookLoginButton.setFragment(this);

    setupListeners();
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    FacebookLoginManager facebookLoginManager = new FacebookLoginManager(firebaseAuth);
    presenter = new SignUpOrLoginPresenter(facebookLoginManager, this);
    attachPresenter(presenter);
    super.onViewCreated(view, savedInstanceState);
  }

  private void setupListeners() {
    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        presenter.pressedRegister();
      }
    });

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        presenter.pressedLogin();
      }
    });
  }

  private void initFragment(Fragment fragment) {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.accountFrameLayout, fragment);
    transaction.commit();
  }

  @Override public void openRegisterFragment() {
    initFragment(RegisterFragment.newInstance());
  }

  @Override public void openLoginFragment() {
    initFragment(LoginFragment.newInstance());
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    presenter.onActivityResult(requestCode, resultCode, data);
  }

  public interface AccountFragmentListener {
    void onRegisterPressed();

    void onLoginPressed();
  }
}
