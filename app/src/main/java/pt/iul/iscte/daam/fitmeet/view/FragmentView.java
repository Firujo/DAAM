package pt.iul.iscte.daam.fitmeet.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.View;

/**
 * Created by filipe on 08-04-2017.
 */

public class FragmentView extends Fragment {

  private FragmentPresenter presenter;

  protected void attachPresenter(FragmentPresenter presenter) {
    this.presenter = presenter;
    presenter.onCreate();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    presenter.onCreateView();
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    presenter.onViewCreated();
  }

  @Override public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override public void onStop() {
    super.onStop();
    presenter.onStop();
  }
}
