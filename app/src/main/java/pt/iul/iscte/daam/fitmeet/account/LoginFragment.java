package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

import com.google.firebase.auth.FirebaseAuth;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.utils.SharedPreferencesUtils;
import pt.iul.iscte.daam.fitmeet.view.FragmentView;

public class LoginFragment extends FragmentView implements LoginView {

  private Button loginButton;
  private EditText usernameEditText;
  private EditText passwordEditText;
  private LoginButton facebookLoginButton;

  private FirebaseAuth firebaseAuth;
  private LoginPresenter loginPresenter;
  private OnFragmentInteractionListener mListener;
  private LoginStatusManager loginStatusManager;

  public LoginFragment() {
  }

  public static LoginFragment newInstance() {
    LoginFragment fragment = new LoginFragment();
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
    firebaseAuth = FirebaseAuth.getInstance();
    loginStatusManager = LoginStatusManager.getInstance(
        getContext().getSharedPreferences(SharedPreferencesUtils.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE));
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login, container, false);
    bindViews(view);
    setupListeners();
    return view;
  }

  private void bindViews(View view) {
    loginButton = (Button) view.findViewById(R.id.loginButton);
    usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);

    facebookLoginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
    facebookLoginButton.setReadPermissions("email", "public_profile");
    facebookLoginButton.setFragment(this);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    AppLoginManager appLoginManager = new AppLoginManager(firebaseAuth);
    FacebookLoginManager facebookLoginManager = new FacebookLoginManager(firebaseAuth);

    loginPresenter =
        new LoginPresenter(this, appLoginManager, facebookLoginManager, loginStatusManager);
    attachPresenter(loginPresenter);
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onStart() {
    super.onStart();
  }

  @Override public void onStop() {
    super.onStop();
  }

  private void setupListeners() {
    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        loginPresenter.pressedLogin(username, password);
      }
    });
  }

  @Override public void finish() {
    getActivity().onBackPressed();
  }

  @Override public void showErrorToast(int error) {
    if (error == AppLoginManager.EMPTY_FIELDS) {
      Toast.makeText(getActivity(), R.string.empty_fields, Toast.LENGTH_SHORT).show();
    } else if (error == AppLoginManager.WRONG_COMBINATION) {
      Toast.makeText(getActivity(), R.string.invalid_login_combination, Toast.LENGTH_SHORT).show();
    }
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    loginPresenter.onActivityResult(requestCode, resultCode, data);
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
