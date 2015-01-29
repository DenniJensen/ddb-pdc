<?php
  $icon_path = $directory . '/images/icons/';
?>

<?php
  drupal_get_messages();
  drupal_set_message($error_message, 'error');
?>

<div class="item <?php print $public_domain_status; ?>" >
  <div class="item-image">
    <?php if ($pdc_result->imageUrl): ?>
      <img src="<?php print $pdc_result->imageUrl; ?>"/>
    <?php endif; ?>
  </div>

  <div class="item-summary">
    <div class="item-title">
      <?php print htmlspecialchars($pdc_result->title); ?>
    </div>
    <div class="item-subtitle">
      <?php print htmlspecialchars($pdc_result->subtitle); ?>
    </div>
  </div>

  <div class="item-license">
    <div class="item-license-img">
      <img src="<?php print $icon_path . $public_domain_status . ".png"; ?>"">
    </div>
    <div class="item-license-text">
      <?php print $public_domain_text; ?>
    </div>
  </div>
</div>

<div class="ddb-pdc-trace <?php print $public_domain_status ?>">
  <?php foreach ($pdc_result->trace as $trace_item): ?>
    <?php
      print theme('ddb_pdc_trace_item', array('trace_item' => $trace_item));
    ?>
  <?php endforeach; ?>
</div>

<?php if ($general_assumptions): ?>
  <div class="pdc-general-assumptions">
    <div id="accordion" class="ddb-pdc-general-assumptions">
      <h3 class="accordion-title">
        <a href="#">Grundannahmen</a>
      </h3>

      <div class="accordion-content">
        <?php foreach ($general_assumptions as $question): ?>
          <?php print theme('ddb_pdc_trace_item', array('trace_item' => $question)); ?>
        <?php endforeach; ?>
      </div>
    </div>
  </div>
<?php endif; ?>
