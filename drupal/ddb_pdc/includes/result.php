<?php
/**
*	function which returns license information about a work 
*	also the questions which were relevant for the decision tree are listed
*	
*	@parameter: ID of an item chosen from the previous page
*	if you know the DDB ID without to search in the module, you also can use www.example.com/pdc-result/$ddbid
*/
function ddb_pdc_result ( $ddbid ){
	drupal_add_css(drupal_get_path('module', 'ddb_pdc') . '/css/ddb-pdc.css');
	
	// path to the images folder in the module has to be hardcoded here otherwise exception with file not found
	$imagespath = base_path() . "sites/all/modules/ddb_pdc/images/";
	
	 
	$pdcresult = ddb_pdc_http_request('/pdc/' .$ddbid, $ddbid); // HTTP Request to the PDC with the DDB ID

	$json_pdcresult = json_decode($pdcresult->data); // Get json data from the request and decode it 
	
	// Get information about the work with this ID to show it on the result page
	$json_searchresults = $_SESSION["searchresults"];
	for($i = 0; $i< count($json_searchresults); $i++){
		if($json_searchresults[$i]->id 
		== $ddbid){
			$imageURL = $json_searchresults[$i]->imageUrl;
			$title = $json_searchresults[$i]->title;
			$subtitle = $json_searchresults[$i]->subtitle;
		}
	}
	
	($json_pdcresult->publicDomain) ? $publicdomainimg = "publicdomain.png" : $publicdomainimg = "notpublicdomain.png";
	
	$output = '';

	$output .= '<div class="item">';
	$output .= '<div class="item-image"><a href="lightbox"><img src="' .$imageURL .'"/></a></div>';
	$output .='<div class="item-summary">';
    $output .='<div class="item-title"><a href="#">' . $title . '</a></div>';
    $output .='<div class="item-subtitle">' . $subtitle . '</div>';
  	$output .='</div>';
  	$output .='<div class="item-license"><img src="' . $imagespath . $publicdomainimg . '"/></div>';
	$output .='</div>';
	
	$output .='<div class="pdc-questions-wrapper">';
	foreach($json_pdcresult->trace as $question){
		($question->answer) ? $color = "positive" : $color = "negative";
		($question->answer) ? $answer = "true" : $answer = "false";
		$output .='<div class="pdc-questions ' .$color .'">';
		$output .='<div class="pdc-question">' . $question->question . '</div>';
		$output .='<div class="pdc-answer">' . $answer . '</div>';
		$output .='</div>';
	}
	$output .='</div>';
	
	return $output;
}

?>