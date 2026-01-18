package com.timeTracker.session;

public class PaywallException extends RuntimeException {
  public PaywallException() {
    super("Free sessions limit reached. Payment required.");
  }

}
