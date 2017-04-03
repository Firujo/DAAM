package pt.iul.iscte.daam.fitmeet.account;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import pt.iul.iscte.daam.fitmeet.R;

public class RegisterFragment extends Fragment {

  private Button registerButton;
  private EditText nameEditText;
  private EditText usernameEditText;
  private EditText passwordEditText;
  private EditText passwordConfirmationEditText;
  private OnFragmentInteractionListener mListener;

  public static RegisterFragment newInstance() {
    RegisterFragment fragment = new RegisterFragment();
    return fragment;
  }

  public RegisterFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
          tabordaTestRegister(name, username, password, passwordConfirmation);
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
  private void tabordaTestRegister(String name, String username, String password,
      String passwordConfirmation) {
    //// TODO: Taborda test registo here
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
