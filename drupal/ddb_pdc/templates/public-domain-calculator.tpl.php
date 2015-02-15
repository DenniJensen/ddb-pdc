<?php
/**
 * @file
 * Displays the DDB search form with the user manual article.
 *
 * Available variables:
 * - $usermanual: node view of the article with its id as parameter.
 */

?>

<div id="ddb-pdc-form">
  <?php
    print theme('ddb_pdc_search_form');
  ?>
</div>
<div class="ddb-pdc-usermanual">
  <?php
  print drupal_render($usermanual);
  ?>
</div>