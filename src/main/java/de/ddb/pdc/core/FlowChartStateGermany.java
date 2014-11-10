package de.ddb.pdc.core;

import java.util.NoSuchElementException;

/**
 * This is the flow chart representation of Germany. A public calculator can use
 * this flow chart to decide the public domain calculator problem for Germany.
 */
enum FlowChartStateGermany implements FlowChartState {

  /**
   * Dummy first state
   */
  INITIAL,

  /**
   * Result: Work is in the public domain
   */
  RESULT_PUBLIC_DOMAIN(true),

  /**
   * Result: Work is not in the public domain
   */
  RESULT_NOT_PUBLIC_DOMAIN(false),

  /**
   * Result: Result can not be calculated
   */
  RESULT_CANT_CALCULATE(),

  /*
   * Flow chart for Literature or Artistic work
   */
  LA_PLACE_AUTHOR(Question.AUTHOR_FROM_TRIPTIS, RESULT_CANT_CALCULATE,
      RESULT_PUBLIC_DOMAIN),
  LA_ORIGIN_OF_WORK2(Question.COUNTRY_OF_ORIGIN_TRIPTIS, RESULT_CANT_CALCULATE,
      LA_PLACE_AUTHOR),
  LA_CREATED_70_AGO(Question.CREATED_MORE_THAN_70_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  LA_PUBLISHED_MORE_THAN_70_YEARS_AGO(
      Question.PUBLISHED_MORE_THAN_70_YEARS_AGO, RESULT_PUBLIC_DOMAIN,
      RESULT_NOT_PUBLIC_DOMAIN),
  LA_AUTHOR_TRIPTIS(Question.AUTHOR_FROM_TRIPTIS, RESULT_CANT_CALCULATE,
      RESULT_PUBLIC_DOMAIN),
  LA_COUNTRY_OF_ORIGIN_TRIPTIS(Question.COUNTRY_OF_ORIGIN_TRIPTIS,
      RESULT_CANT_CALCULATE, LA_AUTHOR_TRIPTIS),
  LA_NOT_PUBLISHED_WITHIN_70_YEARS_OF_DEATH(
      Question.PUBLISHED_MORE_THAN_25_YEARS_AGO, RESULT_PUBLIC_DOMAIN,
      RESULT_NOT_PUBLIC_DOMAIN),
  LA_PUBLISHED2(Question.PUBLISHED_MORE_THAN_70_YEAR_AFTER_CREATION,
      LA_NOT_PUBLISHED_WITHIN_70_YEARS_OF_DEATH,
      LA_PUBLISHED_MORE_THAN_70_YEARS_AGO),
  LA_AUTHOR_EEA2(Question.WORK_PUBLISHED_OR_COMMUNICATED, LA_PUBLISHED2,
      LA_CREATED_70_AGO),
  LA_ORIGIN_OF_WORK(Question.COUNTRY_OF_ORIGIN_EEA, LA_AUTHOR_EEA2,
      LA_ORIGIN_OF_WORK2),
  LA_AUTHOR_ANONYMOUS(Question.AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA,
      LA_AUTHOR_EEA2, LA_ORIGIN_OF_WORK),
  LA_AUTHOR_NOT_NATURAL(Question.AUTHOR_ANONYMOUS, LA_AUTHOR_ANONYMOUS,
      RESULT_CANT_CALCULATE),
  LA_PUBLISHED_WITHIN_70_YEARS_OF_DEATH(
      Question.AUTHOR_DIED_MORE_THAN_70_YEARS_AGO, RESULT_PUBLIC_DOMAIN,
      RESULT_NOT_PUBLIC_DOMAIN),
  LA_PUBLISHED(Question.PUBLISHED_WITHIN_70_YEARS_OF_DEATH,
      LA_PUBLISHED_WITHIN_70_YEARS_OF_DEATH,
      LA_NOT_PUBLISHED_WITHIN_70_YEARS_OF_DEATH),
  LA_AUTHOR_EEA(Question.WORK_PUBLISHED_OR_COMMUNICATED, LA_PUBLISHED,
      LA_PUBLISHED_WITHIN_70_YEARS_OF_DEATH),
  LA_COUNTRY_OF_ORIGIN(Question.COUNTRY_OF_ORIGIN_EEA, LA_AUTHOR_EEA,
      LA_COUNTRY_OF_ORIGIN_TRIPTIS),
  LA_AUTHOR_NATURAL(Question.AUTHOR_FROM_EUROPEAN_ECONOMIC_AREA, LA_AUTHOR_EEA,
      LA_COUNTRY_OF_ORIGIN),
  LA_AUTHOR_TYPE(Question.AUTHOR_NATURAL_PERSON, LA_AUTHOR_NATURAL,
      LA_AUTHOR_NOT_NATURAL),
  LA_OFFICIAL_DECISION(Question.ANNOUNCEMENT_BY_AUTHORITY,
      RESULT_PUBLIC_DOMAIN, LA_AUTHOR_TYPE),
  LA_GOVERNMENT_DIRECTIVE(Question.GOVERNMENT_DIRECTIVE, RESULT_PUBLIC_DOMAIN,
      LA_OFFICIAL_DECISION),
  LA_ACT_OF_PARLIAMENT(Question.ACT_OF_PARLIAMENT, RESULT_PUBLIC_DOMAIN,
      LA_GOVERNMENT_DIRECTIVE),
  LA_IS_COURT_DECISION(Question.COURT_DECISION_OR_DECISION_FORMULA,
      RESULT_PUBLIC_DOMAIN, LA_ACT_OF_PARLIAMENT),
  LA_IS_OFFICIAL_WORK(Question.OFFICIAL_WORK_INTENDED_TO_BE_GENERALLY_RECEIVED,
      RESULT_PUBLIC_DOMAIN, LA_IS_COURT_DECISION),
  LITERATUR_OR_ARTISTIC_WORK(LA_IS_OFFICIAL_WORK),

  /*
   * Flow chart for non original photograph
   */
  NOP_CREATED_50_YEARS_AGO(Question.CREATED_MORE_THAN_50_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  NOP_PUBLISHED_50_YEARS_AGO(Question.PUBLISHED_MORE_THAN_50_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  NON_ORIGINAL_PHOTOGRAPH(Question.WORK_PUBLISHED_OR_COMMUNICATED,
      NOP_PUBLISHED_50_YEARS_AGO, NOP_CREATED_50_YEARS_AGO),

  /*
   * Flow chart for scientific edition of an out-of-copyright work
   */

  SEOW_NOT_PUBLISHED(Question.CREATED_MORE_THAN_25_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  SEOW_WAS_PUBLISHED(Question.PUBLISHED_MORE_THAN_25_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  SEOW_PUBLISHED(Question.WORK_PUBLISHED_OR_COMMUNICATED, SEOW_WAS_PUBLISHED,
      SEOW_NOT_PUBLISHED),
  SCIENTIFIC_EDITION_OF_OOC_WORK(SEOW_PUBLISHED),

  /*
   * Flow chart for Phonogram
   */
  P_CREATED(Question.CREATED_MORE_THAN_50_YEARS_AGO, RESULT_PUBLIC_DOMAIN,
      RESULT_NOT_PUBLIC_DOMAIN),
  P_PUBLISHED_MORE_THAN_50_YEARS_AGO(Question.PUBLISHED_MORE_THAN_50_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  P_COMMUNICATED(Question.PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION,
      P_PUBLISHED_MORE_THAN_50_YEARS_AGO, P_CREATED),
  PHONOGRAM(Question.PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION,
      P_PUBLISHED_MORE_THAN_50_YEARS_AGO, P_COMMUNICATED),

  /*
   * Flow chart for Broadcast
   */
  BROADCAST(Question.FIRST_BROADCAST_25_YEARS, RESULT_PUBLIC_DOMAIN,
      RESULT_NOT_PUBLIC_DOMAIN),

  /*
   * Flow chart for Performance
   */
  PER_PERFORMED_MORE_THAN_50_YEARS(Question.PERFORMED_MORE_THAN_50_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  PER_FIXATION_PUBLISHED(Question.FIXATION_PUBLISHED_MORE_THAN_50_YEARS_AGO,
      RESULT_PUBLIC_DOMAIN, RESULT_NOT_PUBLIC_DOMAIN),
  PERFORMANCE(Question.FIXATION_PUBLISHED_50_YEARS_FROM_PERFORMANCE,
      PER_FIXATION_PUBLISHED, PER_PERFORMED_MORE_THAN_50_YEARS),

  /*
   * Flow chart for database
   */
  DB_LAST_CHANGE(Question.CHANGED_OR_COMPLETED_WITHIN_LAST_15_YEARS,
      RESULT_NOT_PUBLIC_DOMAIN, RESULT_PUBLIC_DOMAIN),
  DB_LAST_15_YEARS(Question.PUBLISHED_IN_LAST_15_YEARS,
      RESULT_NOT_PUBLIC_DOMAIN, RESULT_PUBLIC_DOMAIN),
  DB_PUBLISHED(Question.PUBLISHED_AFTER_COMPLETION_OR_CHANGE, DB_LAST_15_YEARS,
      DB_LAST_CHANGE),
  DB_COUNTRY_RIGHTSHOLDER(Question.RIGHTSHOLDER_RESIDENT_EEA, DB_PUBLISHED,
      RESULT_PUBLIC_DOMAIN),
  DB_OPERATIONS_LINKED(Question.OPERATION_LINKED_TO_ECONOMY_EEA, DB_PUBLISHED,
      RESULT_PUBLIC_DOMAIN),
  DB_OFFICE(Question.COUNTRY_REGISTERED_OFFICE_EEA, DB_OPERATIONS_LINKED,
      RESULT_PUBLIC_DOMAIN),
  DB_COUNTRY_REGISTERED(Question.REGISTERED_IN_EEA, DB_PUBLISHED,
      RESULT_PUBLIC_DOMAIN),
  DB_FORMED_LAW(Question.FORMED_UNDER_LAW_OF_EEA, DB_COUNTRY_REGISTERED,
      DB_OFFICE),
  DATABASE(Question.RIGHT_HOLDER_COMPANY_OR_FIRM, DB_FORMED_LAW,
      DB_COUNTRY_RIGHTSHOLDER),

  /*
   * Flow chart for a fixation of a film
   */
  FF_FIXATED(Question.CREATED_MORE_THAN_50_YEARS_AGO, RESULT_PUBLIC_DOMAIN,
      RESULT_NOT_PUBLIC_DOMAIN),
  FF_PUBLISHED(Question.PUBLISHED_MORE_THAN_50_YEARS_AGO, RESULT_PUBLIC_DOMAIN,
      RESULT_NOT_PUBLIC_DOMAIN),
  FIXATION_OF_FILM(Question.PUBLISHED_WITHIN_50_YEAR_AFTER_CREATION,
      FF_PUBLISHED, FF_FIXATED);


  /**
   * This is the next state of the flow chart. No conditions apply to move on to
   * this next state.
   */
  private FlowChartState unconditionalNextState;

  /**
   * This is the next state of the flow chart if the question is answered with
   * yes.
   */
  private FlowChartState positiveNextState;

  /**
   * This is the next state of the flow chart if the question is answered with
   * no
   */
  private FlowChartState negativeNextState;

  /**
   * The question to decide the current jump.
   */
  private Question question;

  /**
   * The result of the calculation. true if the result is public domain. false
   * if the result is not public domain.
   */
  private boolean result;

  /**
   * Set to true to indicate that a result was reached.
   */
  private boolean resultReached = false;

  /**
   * Create a state that unconditionally jumps to the next state.
   *
   * @param unconditionalJump the next state to jump to
   */
  private FlowChartStateGermany(FlowChartStateGermany unconditionalJump) {
    this.unconditionalNextState = unconditionalJump;
  }

  /**
   * Create a conditional jump to another state. Which jump is taken depends on
   * the answer to the question. Jump to onYes when the question is answered
   * with yes or to onNo otherwise.
   *
   * @param question the question to decide the jump
   * @param onYes jump target if question is answered yes
   * @param onNo jump target if question is answered no
   */
  private FlowChartStateGermany(Question question, FlowChartStateGermany onYes,
      FlowChartStateGermany onNo) {
    this.question = question;
    this.positiveNextState = onYes;
    this.negativeNextState = onNo;
  }

  /**
   * Create a result state. If the flow reaches this state then a decision can
   * be made.
   *
   * @param result The result of the calculation. true if the work is in the
   *        public domain.
   */
  private FlowChartStateGermany(boolean result) {
    this.result = result;
    this.resultReached = true;
  }

  /**
   * Create a state that does not leed anywhere. If such a state is reached the
   * computation can not be continued.
   */
  private FlowChartStateGermany() {

  }

  /**
   * @{inheritDoc}
   */
  @Override
  public FlowChartState getInitialState(Category category)
      throws UnsupportedCategoryException {
    switch (category) {
      case BROADCAST:
        return FlowChartStateGermany.BROADCAST;
      case FIRST_FIXATION_OF_A_FILM:
        return FlowChartStateGermany.FIXATION_OF_FILM;
      case LITERARY_OR_ARTISTIC_WORK:
        return FlowChartStateGermany.LITERATUR_OR_ARTISTIC_WORK;
      case NON_ORIGINAL_PHOTOGRAPH:
        return FlowChartStateGermany.NON_ORIGINAL_PHOTOGRAPH;
      case PERFORMANCE:
        return FlowChartStateGermany.PERFORMANCE;
      case PHONOGRAM:
        return FlowChartStateGermany.PHONOGRAM;
      case SCIENTIFIC_EDITION_OF_AN_OUT_OF_COPYRIGHT_WORK:
        return FlowChartStateGermany.SCIENTIFIC_EDITION_OF_OOC_WORK;
      case UNORIGINAL_DATABASE:
        return FlowChartStateGermany.DATABASE;
      default:
        throw new UnsupportedCategoryException(category);
    }
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public Question getQuestion() throws NoSuchElementException {
    if (this.question != null) {
      return this.question;
    } else if (this.unconditionalNextState != null) {
      return this.unconditionalNextState.getQuestion();
    }

    throw new NoSuchElementException();
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public FlowChartState getNextState(Answer answer)
      throws IllegalStateException {
    if (unconditionalNextState != null) {
      return unconditionalNextState.getNextState(answer);
    } else if (positiveNextState != null && negativeNextState != null) {
      if (answer == Answer.YES) {
        return positiveNextState;
      } else {
        return negativeNextState;
      }
    }
    throw new IllegalStateException();
  }

  /**
   * @{inheritDoc}
   */
  @Override
  public boolean getResult() throws CannotCalculateException {
    if (this.resultReached) {
      return this.result;
    } else if (this.unconditionalNextState != null) {
      return this.unconditionalNextState.getResult();
    }
    throw new CannotCalculateException();
  }

  /**
   * @{inheritDoc}
   */
  @SuppressWarnings("nls")
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("{Flow chart of germany. ");
    builder.append("The current state is: ");
    builder.append(this.toConstantString());
    return builder.append("}").toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toConstantString() {
    return super.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasResult() {
    if (this.resultReached) {
      return true;
    }
    return (this.unconditionalNextState == null
        && this.positiveNextState == null && this.negativeNextState == null);
  }
}
