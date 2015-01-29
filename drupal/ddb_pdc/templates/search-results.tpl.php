<?php
/**
 * @file
 * Displays the DDB items matching a search query on the PDC start page.
 *
 * Available variables:
 * - $search_term: The term that the user searched for.
 * - $items: The items to display.
 * - $total_num_results: The total number of items matching the search term,
 *   for pagination purposes.
 */
?>

<?php
  drupal_get_messages();
  drupal_set_message($error_message, 'error');
?>

<div id="ddb-pdc-form">
  <?php
    print theme('ddb_pdc_search_form', array('search_term' => $search_term));
  ?>
</div>


<?php if (empty($items)): ?>
  <p>Keine Ergebnisse.</p>
<?php else: ?>

  <ul class="items-per-page-selector">
    <?php foreach(array(100, 50, 30, 10) as $items_per_page): ?>
      <li>
        <?php
         $url = url('search-results/' . $search_term, array(
           'query' => array(
             'start' => 0,
             'max' => $items_per_page,
             'page' => 1,
             'limit' => $items_per_page
           )
         ));
        ?>
        <a href="<?php print $url; ?>">
          <?php print $items_per_page; ?>
        </a>
      </li>
    <?php endforeach; ?>
  </ul>

  <?php foreach($items as $item): ?>
    <a class="item" href="<?php print url('pdc-result/' . $item->id); ?>">
      <div class="item-image">
        <?php if ($item->imageUrl): ?>
          <img src="<?php print $item->imageUrl; ?>"/>
        <?php endif; ?>
      </div>

      <div class="item-summary">
        <div class="item-title">
          <?php print htmlspecialchars($item->title); ?>
        </div>
        <div class="item-subtitle">
          <?php print htmlspecialchars($item->subtitle); ?>
        </div>
      </div>
    </a>
  <?php endforeach; ?>

  <?php
    print theme('pagination', array(
      'searchterm' => $search_term,
      'imagespath' => $directory . '/images',
      'ddbResultsAmount' => $total_num_results
    ));
  ?>
<?php endif; ?>
