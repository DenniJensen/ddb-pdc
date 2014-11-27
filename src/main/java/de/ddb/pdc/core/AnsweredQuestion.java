package de.ddb.pdc.core;

/**
 * An answered question contains the question and the answer given to it.
 */
public class AnsweredQuestion {

  private final Question question;

  private final Answer answer;

  private final String note;
  
  /**
   * Creates a new answered question. Pass the question and the answer to this
   * question as arguments. Additionally specify a note if necessary to add
   * made assumptions or other pieces of information.
   *
   * @param question The question
   * @param answer The answer to the question
   * @param note A note on how the answer was found for the question
   */
  public AnsweredQuestion(Question question, Answer answer, String note) {
    this.question = question;
    this.answer = answer;
    this.note = note;
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

  /**
   * Get the note.
   *
   * @return the note to this answered question. null if no note is defined.
   */
  public String getNote() {
    return this.note;
  }

  /**
   * Checks if this answered question has a note.
   *
   * @return true if this answered question has an additional note. false
   *         otherwise
   */
  public boolean hasNote() {
    if (this.note == null || this.note.equals("")) { //$NON-NLS-1$
      return false;
    }
    return true;
  }
}
