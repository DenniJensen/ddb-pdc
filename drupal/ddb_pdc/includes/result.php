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
	var_dump($pdcresult);
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
	
	switch($json_pdcresult->publicDomain){
		case null:
			$publicdomain = "unknownpublicdomain";
			$resultImgAlt = "The license status of this work could not be determined due to missing or unclear metadata.";
			break;
			
		case true:
			$publicdomain = "publicdomain";
			$resultImgAlt = "This work is probably in the public domain.";
			break;
			
		case false:
			$publicdomain = "notpublicdomain";
			$resultImgAlt = "This work is probably not in the public domain.";
			break;
	}
	
	$output = '';

	$output .= '<div class="item ' . $publicdomain . '" >';
	$output .= '<div class="item-image"><a class="colorbox-load" title="'.$title.'" href="'.$imageURL .'"><img src="' .$imageURL .'"/></a></div>';
	$output .='<div class="item-summary">';
    $output .='<div class="item-title">' . $title . '</div>';
    $output .='<div class="item-subtitle">' . $subtitle . '</div>';
  	$output .='</div>';
  	$output .='<div class="item-license"><img src="' . $imagespath . $publicdomain . '.png" title="'. $resultImgAlt .'"/></div>';
	$output .='</div>';
	
	$output .='<div class="pdc-questions-wrapper">';
	foreach($json_pdcresult->trace as $question){
		switch($question->answer){
			case "yes":
				$questionResult = "positive";
				$questionResultTrace = "Yes";
				break;
				
			case "assumed yes":
				$questionResult = "positiveAssumed";
				$questionResultTrace = "Yes (assumed)";
				break;
				
			case "no":
				$questionResult = "negative";
				$questionResultTrace = "No";
				break;
				
			case "assumed no":
				$questionResult = "negativeAssumed";
				$questionResultTrace = "No (assumed)";
				break;
				
			default:	
				$questionResult = "unknown";
				$questionResultTrace = "Unknown";
				break;
		}
		
		$output .='<div class="pdc-questions '.$questionResult.'">';
		$output .='<div class="pdc-question"><img src="' . $imagespath . 'question_'.$questionResult.'.png" alt="" />' . $question->question . '</div>';
		$output .='<div class="pdc-answer"><img src="' . $imagespath . 'answer_'.$questionResult.'.png" alt="" />'.$questionResultTrace.'<span class="notes">'.$question->note.'</span></div>';
		$output .='</div>';	
		
	}
	
	$output .='</div>';
	
	return $output;
}

?>