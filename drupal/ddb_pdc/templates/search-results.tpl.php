<?php
/**
 * @file
 * Search results theme implementation to display a list of all items found by the PDC.
 * All items have an image, a title, a subtitle and a link to the page about if it is public domain or not.
 * 
 * Available variables:
 * - $searchterm
 * - $searchform: The rendered search form to show it also after an error message.
 * - $imagespath: The relative link to the images folder of the module.
 * - $json_searchresults (array): The list of all items found by the PDC.
 * - $ddbResultsAmount: The amount of all found items to calculate pagination.
 *
 * @see ddb_pdc_search_results()
 *
 */
 global $base_url;
?>
<?php if (isset($error_message)): ?>
  <div class="ddb_pdc_form">
    <?php 
    drupal_get_messages();
    drupal_set_message(t($error_message), 'error');
    print $searchform;
    ?>
  </div>
<?php else: ?>
  <div id="rows" class="wrapper-dropdown">
    <ul class="dropdown">
      <li class="first">
        <a href="<?php print $base_url.'/search-results/' . $searchterm . '?start=0&max=100&page=1&limit=100'; ?>">100</a>
      </li>
      <li>
        <a href="<?php print $base_url.'/search-results/' . $searchterm . '?start=0&max=50&page=1&limit=50'; ?>">50</a>
      </li>
      <li>
        <a href="<?php print $base_url.'/search-results/' . $searchterm . '?start=0&max=30&page=1&limit=30'; ?>">30</a>
      </li>
      <li class="last">
        <a href="<?php print $base_url.'/search-results/' . $searchterm . '?start=0&max=10&page=1&limit=10'; ?>">10</a>
      </li>
    </ul>
  </div>
  <?php foreach($ddbItems as $item): ?>
    <div class="item">
      <?php if (@GetImageSize($item->imageUrl)): ?>
        <div class="item-image">
          <img src="<?php print $item->imageUrl; ?>"/>
        </div>
      <?php endif; ?>
      <div class="item-summary">
        <div class="item-title">
          <?php print $item->title; ?>
        </div>
        <div class="item-subtitle">
          <?php print $item->subtitle; ?>
        </div>
      </div>
      <div class="item-calculate">
        <div class="item-calculate-button">
          <a href="<?php print $base_url.'/pdc-result/' . $item->id; ?>">
            <img src="<?php print $imagespath .'icons/calculate.png'; ?>"/> Berechne!
          </a>
        </div>
      </div>
    </div>
    <div style="clear:both;"></div>
  <?php endforeach; ?>
  <?php print $pagination; ?>  
<?php endif; ?>