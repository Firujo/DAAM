/*
 * Copyright (c) 2017.
 * Modified by Marcelo Benites on 10/02/2017.
 */

package pt.iul.iscte.daam.fitmeet.newevent.model;

/**
 * Created by marcelobenites on 10/02/17.
 */

public class NewEventValidationException extends Exception {

  public static final int EMPTY_FIELDS = 1;
  private final int code;

  public NewEventValidationException(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
