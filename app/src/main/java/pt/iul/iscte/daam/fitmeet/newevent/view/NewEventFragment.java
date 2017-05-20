package pt.iul.iscte.daam.fitmeet.newevent.view;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import pt.iul.iscte.daam.fitmeet.R;
import pt.iul.iscte.daam.fitmeet.newevent.model.NewEventDataValidator;
import pt.iul.iscte.daam.fitmeet.newevent.presenter.NewEventContract;
import pt.iul.iscte.daam.fitmeet.newevent.presenter.NewEventPresenter;

/**
 * Main UI for the new event screen. Users can enter event details.
 */
public class NewEventFragment extends Fragment implements NewEventContract.View {

  private NewEventContract.UserActionsListener actionsListener;

  private TextView title;
  private TextView description;
  private Button difficultyPicker;
  private ImageView rowingImage;
  private ImageView runningImage;
  private ImageView trailingImage;
  private ImageView cyclingImage;
  private Drawable defaultBackground;
  private Button datePicker;

  public NewEventFragment() {
  }

  public static NewEventFragment newInstance() {
    return new NewEventFragment();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_newevent, container, false);
    title = (EditText) view.findViewById(R.id.new_event_title);
    description = (EditText) view.findViewById(R.id.new_event_description);
    difficultyPicker = (Button) view.findViewById(R.id.difficulty_button);
    rowingImage = (ImageView) view.findViewById(R.id.new_event_rowing_image);
    runningImage = (ImageView) view.findViewById(R.id.new_event_running_image);
    cyclingImage = (ImageView) view.findViewById(R.id.new_event_cycling_image);
    trailingImage = (ImageView) view.findViewById(R.id.new_event_trailing);
    datePicker = (Button) view.findViewById(R.id.new_event_date_button);

    setupViews();

    setHasOptionsMenu(true);
    setRetainInstance(true);
    return view;
  }

  private void setupViews() {
    difficultyPicker.setOnClickListener(v -> actionsListener.difficultyButtonClicked());
    datePicker.setOnClickListener(v -> actionsListener.dateButtonClicked());
    defaultBackground = rowingImage.getBackground();

    rowingImage.setOnClickListener(v -> {
      rowingImage.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
      runningImage.setBackground(defaultBackground);
      cyclingImage.setBackground(defaultBackground);
      trailingImage.setBackground(defaultBackground);
    });

    runningImage.setOnClickListener(v -> {
      rowingImage.setBackground(defaultBackground);
      runningImage.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
      cyclingImage.setBackground(defaultBackground);
      trailingImage.setBackground(defaultBackground);
    });

    cyclingImage.setOnClickListener(v -> {
      rowingImage.setBackground(defaultBackground);
      runningImage.setBackground(defaultBackground);
      cyclingImage.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
      trailingImage.setBackground(defaultBackground);
    });

    trailingImage.setOnClickListener(v -> {
      rowingImage.setBackground(defaultBackground);
      runningImage.setBackground(defaultBackground);
      cyclingImage.setBackground(defaultBackground);
      trailingImage.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    });
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    actionsListener = new NewEventPresenter(this, new NewEventDataValidator());
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.fragment_newevent_options_menu_actions, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.save:
        actionsListener.saveEvent(title.getText().toString(), description.getText().toString());
        return true;
    }
    return false;
  }

  @Override public void showNewEventError() {
    if (getView() != null) {
      Snackbar.make(getView(), getString(R.string.create_event_gather_error), Snackbar.LENGTH_LONG)
          .show();
    }
  }

  @Override public void showNewEventSuccess() {
    if (getView() != null) {
      Snackbar.make(getView(), getString(R.string.create_event_gather_created),
          Snackbar.LENGTH_LONG).show();
    }
  }

  @Override public void showDifficultyPicker() {
    final Dialog dialog = new Dialog(getContext());
    String[] levelsArray = new String[] { "Begginer", "Intermediate", "Advanced" };
    dialog.setContentView(R.layout.difficulty_picker_dialog);
    Button buttonAffirmative = (Button) dialog.findViewById(R.id.button_affirmative);
    Button buttonNegative = (Button) dialog.findViewById(R.id.button_negative);
    final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker1);
    numberPicker.setMaxValue(levelsArray.length - 1);
    numberPicker.setMinValue(0);
    numberPicker.setDisplayedValues(levelsArray);
    numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    numberPicker.setWrapSelectorWheel(false);
    buttonAffirmative.setOnClickListener(v -> {
      difficultyPicker.setText(levelsArray[numberPicker.getValue()]);
      dialog.dismiss();
    });
    buttonNegative.setOnClickListener(v -> dialog.dismiss());
    dialog.show();
  }

  @Override public void showDatePicker() {
    DatePickerFragment newFragment = new DatePickerFragment();
    newFragment.setDateButton(datePicker);
    newFragment.show(getFragmentManager(), "datePicker");
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    trailingImage.setOnClickListener(null);
    rowingImage.setOnClickListener(null);
    cyclingImage.setOnClickListener(null);
    trailingImage.setOnClickListener(null);
    difficultyPicker.setOnClickListener(null);
  }
}
