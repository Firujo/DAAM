package pt.iul.iscte.daam.fitmeet.account;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.view.FragmentView;

public class RegisterFragment extends FragmentView implements RegisterView {

  private Button registerButton;
  private EditText nameEditText;
  private EditText usernameEditText;
  private EditText passwordEditText;
  private EditText passwordConfirmationEditText;
  private EditText birthdayEditText;
  private EditText countryEditText;
  private EditText cityEditText;
  private OnFragmentInteractionListener mListener;

  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;

  private RegisterPresenter presenter;

  public static RegisterFragment newInstance() {
    RegisterFragment fragment = new RegisterFragment();
    return fragment;
  }

  public RegisterFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    mAuth = FirebaseAuth.getInstance();

    super.onCreate(savedInstanceState);
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onStop() {
    super.onStop();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_register, container, false);

    bindViews(view);

    setupListener();
    return view;
  }

  private void bindViews(View view) {
    registerButton = (Button) view.findViewById(R.id.registerButton);
    nameEditText = (EditText) view.findViewById(R.id.nameEditText);
    usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
    passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);
    passwordConfirmationEditText = (EditText) view.findViewById(R.id.passwordConfirmationEditText);
    birthdayEditText = (EditText) view.findViewById(R.id.birthday);
    countryEditText = (EditText) view.findViewById(R.id.country);
    cityEditText = (EditText) view.findViewById(R.id.city);
  }

  private void setupListener() {
    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        final String name = nameEditText.getText().toString();
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        final String passwordConfirmation = passwordConfirmationEditText.getText().toString();
        final String birthday = birthdayEditText.getText().toString();
        final String country = countryEditText.getText().toString();
        final String city = cityEditText.getText().toString();

        presenter.pressedRegister(name, username, password, passwordConfirmation, birthday, country,
            city);
      }
    });
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    presenter =
        new RegisterPresenter(this, new RegisterManager(new RegisterCredentialsValidator()));
    attachPresenter(presenter);

    super.onViewCreated(view, savedInstanceState);

  }

  @Override public void showInvalidInputsMessage(int result) {

    switch (result) {
      case RegisterCredentialsValidator.EMPTY_NAME:
        nameEditText.setError("Name can not be empty !");
        nameEditText.requestFocus();
        break;
      case RegisterCredentialsValidator.EMPTY_USERNAME:
        usernameEditText.setError("Username can not be empty !");
        usernameEditText.requestFocus();
        break;
      case RegisterCredentialsValidator.EMPTY_PASSWORD:
        passwordEditText.setError("Password can not be empty !");
        passwordEditText.requestFocus();
        break;
      case RegisterCredentialsValidator.INVALID_PASSWORD_MATCH:
        passwordConfirmationEditText.setError("password and password confirmation don't match");
        passwordConfirmationEditText.requestFocus();
        break;
      case RegisterCredentialsValidator.EMPTY_BIRTHDAY:
        birthdayEditText.setError("Birthday can not be empty !");
        birthdayEditText.requestFocus();
        break;
      case RegisterCredentialsValidator.EMPTY_COUNTRY:
        countryEditText.setError("Country can not be empty !");
        countryEditText.requestFocus();
        break;
      case RegisterCredentialsValidator.EMPTY_CITY:
        cityEditText.setError("City can not be empty !");
        cityEditText.requestFocus();
        break;
      default:
        Toast.makeText(getContext(),
            "There was an unknown error while trying to parse the data. Please try again",
            Toast.LENGTH_SHORT).show();
    }
  }

  @Override public void successfulRegistration() {
    Toast.makeText(getContext(), "Successful registration", Toast.LENGTH_SHORT).show();
  }

  @Override public void showUnsuccessfulRegistration() {
    Toast.makeText(getContext(), "Unexpected error, please try again", Toast.LENGTH_SHORT).show();
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
