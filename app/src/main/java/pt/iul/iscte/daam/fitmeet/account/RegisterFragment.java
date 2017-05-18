package pt.iul.iscte.daam.fitmeet.account;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
    //
    //mAuthListener = new FirebaseAuth.AuthStateListener() {
    //  @Override
    //  public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    //    FirebaseUser user = firebaseAuth.getCurrentUser();
    //    if (user != null) {
    //      if (user.isEmailVerified()) {
    //        System.out.println("Email is verified");
    //        System.out.println("onAuthStateChanged:signed_in:" + user.getUid());
    //      }
    //      else {
    //        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
    //                .setDisplayName(nameEditText.getText().toString())
    //                .build();
    //        user.updateProfile(profileUpdates);
    //        user.sendEmailVerification();
    //        System.out.println("Email is not verified");
    //      }
    //
    //    } else {
    //      System.out.println("onAuthStateChanged:signed_out");
    //    }
    //  }
    //};

    super.onCreate(savedInstanceState);
  }

  @Override
  public void onStart() {
    super.onStart();
    //mAuth.addAuthStateListener(mAuthListener);
  }

  @Override
  public void onStop() {
    super.onStop();
    //if (mAuthListener != null) {
    //  mAuth.removeAuthStateListener(mAuthListener);
    //}
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

  //// TODO: 01-05-2017  ver v8 phoneinputfragment
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

  @Override public void showInvalidInputsMessage() {
    Toast.makeText(getContext(), "Invalid fields, please correct them", Toast.LENGTH_SHORT).show();
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
