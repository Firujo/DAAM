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
import pt.iul.iscte.daam.fitmeet.utils.SharedPreferencesUtils;
import pt.iul.iscte.daam.fitmeet.view.FragmentView;

public class LoggedInFragment extends FragmentView implements LoggedInView {

  private OnFragmentInteractionListener mListener;
  private LoggedInPresenter presenter;
  private TextView welcomeMessage;
  private Button logoutButton;

  public LoggedInFragment() {
  }

  public static LoggedInFragment newInstance() {
    LoggedInFragment fragment = new LoggedInFragment();
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
    return view;
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

  @Override public void setupWelcomeMessage(String name) {
    welcomeMessage.setText(getResources().getString(R.string.welcome_message, name));
  }

  public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);
  }
}
