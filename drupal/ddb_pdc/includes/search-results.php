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
	$json_searchresults = json_decode($ddbsearchresults->data); // Get json data from the request and decode it 
	
	$output = '';
	
	foreach($json_searchresults as $item){
	
		$output .= '<div class="item">';
		$output .= '<div class="item-image"><img src="' .$item->imageUrl .'"/></div>';
		$output .='<div class="item-summary">';
    	$output .='<div class="item-title"><a href="#">' . $item->title . '</a></div>';
    	$output .='<div class="item-subtitle">' . $item->subtitle . '</div>';
  		$output .='</div>';
  		$output .='<div class="item-calculate"><div class="item-calculate-button"><a href="' .$base_url.'/pdc-result/' . $item->id . '"><img src="' .			$imagespath .'calculate.png"/> Calculate!</a></div></div>';
		$output .='</div>';
		$output .='<div style="clear:both;"></div>';
}
		$_SESSION["searchresults"] = $json_searchresults;
	
	return $output;
}