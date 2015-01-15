<?php
/**
 * @file
 * PDC result theme implementation to display license information about a work.
 * Also the questions which were relevant for the decision tree are listed.
 *
 * Available variables:
 * - $title: Title of the item whose license information is being shown.
 * - $subtitle: Subitle of the item whose license information is being shown.
 * - $imageURL: An external link to a thumbnail image of the item whose license information is being shown.
 * - $json_pdcresult (array): The list of all relevant questions for calculating the license type of the item by the PDC.
 * - $imagespath: The relative link to the images folder of the module.
 * - $publicdomain: License type of the item (written together) for setting items css class and icon image.
 * - $publicdomainText: A short comment to the icon shown for a license type (printed).
 * - $resultImgAlt: A long comment to the icon shown for a license type (tooltip).
 *
 * @see ddb_pdc_result()
 *
 */
?>
<?php if(isset($error_message)):?>

<div class="ddb_pdc_form">
  <?php 
	drupal_get_messages();
	drupal_set_message(t($error_message), 'error');
	print $searchform;
?>
</div>
<?php else:?>
<div class="item <?php print $publicdomain; ?>" >
	<?php if (@GetImageSize($imageURL)):?>
	<div class="item-image">
    	<img src="<?php print $imageURL; ?>"/>
	</div>
    <?php endif;?>
	<div class="item-summary">
		<div class="item-title">
        	<?php print $title; ?>
        </div>
		<div class="item-subtitle">
        	<?php print $subtitle; ?>
		</div>
	</div>
	<div class="item-license">
    	<span class="item-license-img">
        	<img src="<?php print $imagespath . 'icons/' . $publicdomain . '.png" title="' . $resultImgAlt; ?>"/>
            <span class="item-license-text">
        		<?php print $publicdomainText; ?>
        	</span>
        </span>
        
	</div>
</div>
			
<div class="pdc-questions-wrapper">
<div id="accordion">
<h3 class="accordion-title"><a href="#">General Assumptions</a></h3>
<div class="accordion-content">
<?php 
$counter = 0;
foreach ($json_pdcresult->trace as $question): 
$counter++;
switch ($question->answer) {
	case "yes":
		$questionResult      = "positive";
		$questionResultTrace = "Yes";
		break;
	
	case "assumed yes":
		$questionResult      = "positiveAssumed";
		$questionResultTrace = "Yes (assumed)";
		break;
	
	case "no":
		$questionResult      = "negative";
		$questionResultTrace = "No";
		break;
	
	case "assumed no":
		$questionResult      = "negativeAssumed";
		$questionResultTrace = "No (assumed)";
		break;
	
	default:
		$questionResult      = "unknown";
		$questionResultTrace = "Unknown";
		break;
}
?>				
<div class="pdc-questions <?php print $questionResult; ?>">
	<div class="pdc-question">
    	<img src="<?php print $imagespath . 'icons/' . 'question_' . $questionResult . '.png'; ?>" alt="" />
		<?php print $question->question; ?>
    </div>
	<div class="pdc-answer">
    	<img src="<?php print $imagespath . 'icons/' . 'answer_' . $questionResult . '.png'; ?>" alt="" />
    	<?php print $questionResultTrace; ?>
		<div class="notes">
    		<img src="<?php print $imagespath . 'icons/' . 'notes.png'; ?>" alt="" />
    		<div class="note"><?php print $question->note; ?></div>
		</div>
    </div>    
</div>
				
<?php 
if($counter == 5)
	echo "</div></div>";
endforeach; 
endif;
?>
			
</div>