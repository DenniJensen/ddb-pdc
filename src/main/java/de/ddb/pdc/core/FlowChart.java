package de.ddb.pdc.core;

import java.util.NoSuchElementException;

/**
 * Interface for a flow chart for calculating the public domain problem
 * 
 * @author Frank Zechert
 */
public interface FlowChart {

  /**
   * Get the initial state of the flow chart for the given category of cultural
   * good
   * 
   * @param category The category of cultural good
   * @return The initial state of the flow chart
   * @throws UnsupportedCategoryException The given category of cultural good is
   *         unsupported
   */
  public FlowChart getInitialState(Category category)
      throws UnsupportedCategoryException;

  /**
   * Get the current question. This question must be answered to move to the
   * next state of the flow chart.
   *
   * @return The current question
   * @throws NoSuchElementException There are more more questions
   */
  public Question getCurrentQuestion() throws NoSuchElementException;

  /**
   * Get the next state of the flow chart according to the answer to the current
   * question.
   * 
   * @param answer The answer to the current question
   * @return the next state of the flow chart
   * @throws IllegalStateException There are no more questions to answer or no
   *         more next states
   */
  public FlowChart getNextState(Answer answer) throws IllegalStateException;

  /**
   * Get the result of the computation. The last state in the flow chart.
   * 
   * @return The result of the computation. true if the work is in the public
   *         domain or false otherwise.
   * @throws CannotCalculateException The calculation could not decide the
   *         problem.
   */
  public boolean getResult() throws CannotCalculateException;

  /**
   * Returns the name of the current state constant.
   * 
   * @return the name of the current state constant.
   */
  public String toConstantString();

  /**
   * Returns true if the result is available. That means all questions have been
   * answered.
   * 
   * @return true if result is available.
   */
  public boolean hasResult();
}
