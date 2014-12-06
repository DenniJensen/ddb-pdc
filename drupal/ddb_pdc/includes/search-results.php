<?php
/**
*	function which returns a list of relevant items (10) given from the pdc
*	all items have an image, a title, a subtitle and a link to the page about if it is public domain or not
*	
*	@parameter: searchterm from the form
*/
function ddb_pdc_search_results($searchterm){

	// path to the images folder in the module has to be hardcoded here otherwise exception with file not found
	$imagespath = base_path() . "sites/all/modules/ddb_pdc/images/"; 	
	global $base_url;
	
	$ddbsearchresults = ddb_pdc_http_request('/search', 'q='.$searchterm.'&max=10'); // HTTP Request to the PDC with the searchterm
	//var_dump($ddbsearchresults);
	
	 
	 $output = '';
	 
	// status cases
	switch($ddbsearchresults->code){
		case	"500":
			drupal_get_messages();
			drupal_set_message(t('Es ist leider ein Fehler aufgetreten. Bitte überprüfen Sie Ihre Eingabe!'), 'error');
			$form = drupal_get_form('ddb_pdc_form');
			$output .= drupal_render($form);
			break;
		case	"200":
			if($ddbsearchresults->data == "[]"){
				drupal_get_messages();
				drupal_set_message(t('Ihre Suche ergab leider keine Treffer. Bitte überprüfen Sie Ihre Eingabe!'), 'error');
				$form = drupal_get_form('ddb_pdc_form');
				$output .= drupal_render($form);
				break;
			} else {
				$json_searchresults = json_decode($ddbsearchresults->data); // Get json data from the request and decode it
				drupal_get_messages();
				foreach($json_searchresults as $item){
	
					$output .= '<div class="item">';
					$output .= '<div class="item-image"><a class="colorbox-load" title="'.$item->title.'" href="'.$item->imageUrl .'"><img src="' .$item->imageUrl .'"/></a></div>';
					$output .='<div class="item-summary">';
    				$output .='<div class="item-title">' . $item->title . '</div>';
    				$output .='<div class="item-subtitle">' . $item->subtitle . '</div>';
  					$output .='</div>';
	  				$output .='<div class="item-calculate"><div class="item-calculate-button"><a href="' .$base_url.'/pdc-result/' . $item->id . '"><img src="' 					.$imagespath .'calculate.png"/> Calculate!</a></div></div>';
					$output .='</div>';
					$output .='<div style="clear:both;"></div>';
				}
			
				$_SESSION["searchresults"] = $json_searchresults;
				break;	
			}
			
		case	-10061:
			drupal_get_messages();
			drupal_set_message(t('Es konnte leider keine Verbindung mit dem Server hergestellt werden.'), 'error');
			$form = drupal_get_form('ddb_pdc_form');
			$output .= drupal_render($form);
			break;
		default:
			$form = drupal_get_form('ddb_pdc_form');
			$output .= drupal_render($form);
			
			
	}	
	
	return $output;
}