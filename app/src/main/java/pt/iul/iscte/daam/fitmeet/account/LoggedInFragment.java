package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.Utils.SharedPreferencesUtils;
import pt.iul.iscte.daam.fitmeet.view.FragmentView;

public class LoggedInFragment extends FragmentView implements LoggedInView {

  private OnFragmentInteractionListener mListener;
  private LoggedInPresenter presenter;
  private TextView welcomeMessage;
  private Button logoutButton;

  public LoggedInFragment() {
  }

  public static LoggedInFragment newInstance(String name) {
    LoggedInFragment fragment = new LoggedInFragment();
    Bundle args = new Bundle();
    args.putString("username", name);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_logged_in, container, false);
    bindViews(view);

    setupListeners();
    setupWelcomeMessage();
    return view;
  }

  private void setupWelcomeMessage() {
    Bundle args = getArguments();
    welcomeMessage.setText(
        getResources().getString(R.string.welcome_message, args.getString("username")));
  }

  private void setupListeners() {
    logoutButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        presenter.pressedLogout();
      }
    });
  }

  private void bindViews(View view) {
    welcomeMessage = (TextView) view.findViewById(R.id.welcome_message);
    logoutButton = (Button) view.findViewById(R.id.logoutButton);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    presenter = new LoggedInPresenter(this, LoginStatusManager.getInstance(
        getContext().getSharedPreferences(SharedPreferencesUtils.SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE)));
    attachPresenter(presenter);
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void finish() {
    getActivity().onBackPressed();
  }

  public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);
  }
}
