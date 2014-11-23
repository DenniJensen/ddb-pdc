<?php
/**
*	function which returns license information about a work 
*	also the questions which were relevant for the decision tree are listed
*	
*	@parameter: ID of an item chosen from the previous page
*	if you know the DDB ID without to search in the module, you also can use www.example.com/pdc-result/$ddbid
*/
function ddb_pdc_result ( $ddbid ){
	
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
	
	($json_pdcresult->publicDomain) ? $publicdomain = "publicdomain" : $publicdomain = "notpublicdomain";
	
	$output = '';

	$output .= '<div class="item ' . $publicdomain . '" >';
	$output .= '<div class="item-image"><img src="' .$imageURL .'"/></div>';
	$output .='<div class="item-summary">';
    $output .='<div class="item-title"><a href="#">' . $title . '</a></div>';
    $output .='<div class="item-subtitle">' . $subtitle . '</div>';
  	$output .='</div>';
  	$output .='<div class="item-license"><img src="' . $imagespath . $publicdomain . '.png"/></div>';
	$output .='</div>';
	
	$output .='<div class="pdc-questions-wrapper">';
	foreach($json_pdcresult->trace as $question){
		if($question->answer){
			$output .='<div class="pdc-questions positive">';
			$output .='<div class="pdc-question"><img src="' . $imagespath . 'question_true.png" alt="" />' . $question->question . '</div>';
			$output .='<div class="pdc-answer"><img src="' . $imagespath . 'answer_true.png" alt="" />Yes</div>';
			$output .='</div>';
		} else{
			$output .='<div class="pdc-questions negative">';
			$output .='<div class="pdc-question"><img src="' . $imagespath . 'question_false.png" alt="" />' . $question->question . '</div>';
			$output .='<div class="pdc-answer"><img src="' . $imagespath . 'answer_false.png" alt="" />No</div>';
			$output .='</div>';	
		}
	}
	$output .='</div>';
	
	return $output;
}

?>