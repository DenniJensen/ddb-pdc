package de.ddb.pdc.core;

/**
 * Exception thrown by the calculator if no result can be computed.
 *
 * This exception will be thrown by the public domain calculator in cases where
 * the answer could not be determined by the questions asked. This will be the
 * case for (e.g.) too complex legal situations.
 */
public class CannotCalculateException extends Exception {
  private static final long serialVersionUID = 2761669376389804736L;
}
