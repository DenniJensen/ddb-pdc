package de.ddb.pdc.core;

/**
 * An answered question contains the question and the answer given to it.
 */
public class AnsweredQuestion {

  private final Question question;

  private final Answer answer;

  /**
   * Creates a new answered question. Pass the question and the answer to this
   * question as arguments.
   *
   * @param question The question
   * @param answer The answer to the question
   */
  public AnsweredQuestion(Question question, Answer answer) {
    this.question = question;
    this.answer = answer;
  }

  /**
   * Get the question.
   *
   * @return the question that was answered
   */
  public Question getQuestion() {
    return this.question;
  }

  /**
   * Get the answer.
   *
   * @return the answer given to the question
   */
  public Answer getAnswer() {
    return this.answer;
  }
}
