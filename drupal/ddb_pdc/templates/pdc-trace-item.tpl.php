<?php
/**
 * @file
 * Displays an item from the trace of a public domain calculator result.
 *
 * Available variables:
 * - $trace_item: The PDC result trace item to display.
 *   - $trace_item->question: The answered question.
 *   - $trace_item->answer: The given answer (one of "yes", "no", "assumed_yes",
 *     "assumed_no" and "unknown").
 *   - $trace_item->note: A note explaining the answer.
 */

$icon_path = $directory . '/images/icons/';
?>

<div class="ddb-pdc-trace-item">

  <div class="ddb-pdc-question">
    <img src="<?php print $icon_path . 'question.png'; ?>"
         alt="">
    <?php print htmlspecialchars($trace_item->question); ?>
  </div>

  <div class="ddb-pdc-answer">
    <img src="<?php print $icon_path . 'answer_' . $trace_item->answer . '.png'; ?>"
         alt="" />

    <?php
      switch ($trace_item->answer) {
        case 'yes':
          print 'Ja';
          break;
        case 'assumed_yes':
          print 'Ja (Annahme)';
          break;
        case 'no':
          print 'Nein';
          break;
        case 'assumed_no':
          print 'Nein (Annahme)';
          break;
        case 'unknown':
          print 'Unbekannt';
          break;
      }
    ?>

    <div class="ddb-pdc-notes">
      <img src="<?php print $icon_path . 'notes.png'; ?>" alt="">
      <div class="ddb-pdc-note-text">
        <?php print $trace_item->note; ?>
      </div>
    </div>
  </div>

</div>
