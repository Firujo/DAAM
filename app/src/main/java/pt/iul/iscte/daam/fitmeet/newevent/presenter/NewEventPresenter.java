/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pt.iul.iscte.daam.fitmeet.newevent.presenter;

import android.support.annotation.NonNull;
import pt.iul.iscte.daam.fitmeet.newevent.model.NewEventDataValidator;
import pt.iul.iscte.daam.fitmeet.newevent.model.NewEventValidationException;
import pt.iul.iscte.daam.fitmeet.newevent.view.NewEventFragment;

/**
 * Listens to user actions from the UI ({@link NewEventFragment}), retrieves the data and updates
 * the UI as required.
 */
public class NewEventPresenter implements NewEventContract.UserActionsListener {

  @NonNull private final NewEventContract.View newEventView;
  private NewEventDataValidator newEventDataValidator;

  public NewEventPresenter(@NonNull NewEventContract.View newEventView,
      NewEventDataValidator newEventDataValidator) {
    this.newEventView = newEventView;
    this.newEventDataValidator = newEventDataValidator;
  }

  @Override public void saveEvent(String title, String description) {
    try {
      newEventDataValidator.validate(title, description);
      newEventView.showNewEventSuccess();
    } catch (NewEventValidationException e) {
      newEventView.showNewEventError();
    }
  }

  @Override public void difficultyButtonClicked() {
    newEventView.showDifficultyPicker();
  }

  @Override public void dateButtonClicked() {
    newEventView.showDatePicker();
  }
}
