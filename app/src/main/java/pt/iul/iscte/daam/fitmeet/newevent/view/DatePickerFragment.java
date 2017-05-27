package pt.iul.iscte.daam.fitmeet.newevent.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Utilizador on 5/20/2017.
 */

public class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {

  private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
  private Button dateButton;

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
    dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
    return dialog;
  }

  public void onDateSet(DatePicker view, int year, int month, int day) {
    dateButton.setText(dateFormat.format(new GregorianCalendar(year, month, day).getTime()));
  }

  public void setDateButton(Button dateButton) {
    this.dateButton = dateButton;
  }
}

