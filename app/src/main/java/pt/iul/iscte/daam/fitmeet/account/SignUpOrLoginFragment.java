package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import pt.iul.iscte.daam.fitmeet.R;

public class SignUpOrLoginFragment extends Fragment {

  private AccountFragmentListener signUpOrLoginFragmentListener;
  private Button registerButton;
  private Button loginButton;

  public SignUpOrLoginFragment() {
  }

  public static SignUpOrLoginFragment newInstance() {
    return new SignUpOrLoginFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);

    if (context instanceof AccountFragmentListener) {
      signUpOrLoginFragmentListener = (AccountFragmentListener) context;
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
    View view = inflater.inflate(R.layout.fragment_signuporlogin, container, false);
    registerButton = (Button) view.findViewById(R.id.registerFragmentButton);
    loginButton = (Button) view.findViewById(R.id.loginFragmentButton);

    setupListeners();
    return view;
  }

  private void setupListeners() {
    registerButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        initRegisterFragment(RegisterFragment.newInstance());
      }
    });

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //accountFragmentListener.onLoginPressed();
        initLoginFragment(LoginFragment.newInstance());
      }
    });
  }

  private void initLoginFragment(LoginFragment loginFragment) {
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.accountFrameLayout, loginFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  private void initRegisterFragment(RegisterFragment registerFragment) {
    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.accountFrameLayout, registerFragment);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  public interface AccountFragmentListener {
    void onRegisterPressed();

    void onLoginPressed();
  }
}
