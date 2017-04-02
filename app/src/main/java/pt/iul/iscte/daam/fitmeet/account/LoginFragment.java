package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import pt.iul.iscte.daam.fitmeet.R;

public class LoginFragment extends Fragment {

  private Button loginButton;
  private EditText usernameEditText;
  private EditText passwordEditText;
  private TextView registerTextView;

  private OnFragmentInteractionListener mListener;

  public LoginFragment() {
  }

  public static LoginFragment newInstance(String param1, String param2) {
    LoginFragment fragment = new LoginFragment();
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnFragmentInteractionListener) {
      mListener = (OnFragmentInteractionListener) context;
    } else {
      throw new RuntimeException(
          context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_login, container, false);

    loginButton = (Button) view.findViewById(R.id.loginButton);
    usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
    registerTextView = (TextView) view.findViewById(R.id.registerTextView);

    return view;
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
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
