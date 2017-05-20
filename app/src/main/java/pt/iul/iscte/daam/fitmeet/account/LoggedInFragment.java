package pt.iul.iscte.daam.fitmeet.account;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import pt.iul.iscte.daam.fitmeet.R;
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

    return view;
  }

  private void bindViews(View view) {
    welcomeMessage = (TextView) view.findViewById(R.id.welcome_message);
    logoutButton = (Button) view.findViewById(R.id.logoutButton);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    presenter = new LoggedInPresenter();
    attachPresenter(presenter);
    super.onViewCreated(view, savedInstanceState);
  }

  public interface OnFragmentInteractionListener {
    void onFragmentInteraction(Uri uri);
  }
}
