<?php
//function to return the pagination string

function getPaginationString($totalitems, $searchterm)
{
	//defaults
	$pagestring  = "&page=";
	$limitstring = "&limit=";
	$adjacents   = 2;
	$page        = $_GET['page'];
	$limit       = $_GET['limit'];
	
	$imagespath = base_path() . "sites/all/modules/ddb_pdc/images/";
	global $base_url;
	
	//other vars
	$prev     = $page - 1; //previous page is page - 1
	$next     = $page + 1; //next page is page + 1
	$lastpage = ceil($totalitems / $limit); //lastpage is = total items / items per page, rounded up.
	$lpm1     = $lastpage - 1; //last page minus 1
	
	$lastpageAmount = ($totalitems % $limit == 0) ? $limit : $totalitems % $limit;

	$pagination     = "";
	
	if ($lastpage > 1) {
		$pagination .= "<div class=\"paginationWrapper\">";
		$pagination .= "<ul class=\"pagination\">";
		
		//previous button
		if ($page > 1) {
			$pagination .= '<li class="pager_previous">';
			$pagination .= '<a href="' . $base_url . '/search-results/' . $searchterm . '?start=' . (($prev - 1) * $limit) . '&max=' . $limit . $pagestring . $prev . $limitstring . $limit . '">';
			$pagination .= '<img src="' . $imagespath . '/pagination/previous.png"/>';
			$pagination .= '</a>';
			$pagination .= '</li>';
		} else {
			$pagination .= '<li class="pager_previous">';
			$pagination .= '<img src="' . $imagespath . '/pagination/previousUnable.png"/>';
			$pagination .= '</li>';
		}
		
		//pages	
		if ($lastpage < 7 + ($adjacents * 2)) //not enough pages to bother breaking it up
			{
			for ($counter = 1; $counter <= $lastpage; $counter++) {
				if ($counter == $page) {
					$pagination .= "<li class=\"paginationCurrent\">$counter</li>";
				} elseif ($counter == $lastpage) {
					$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
				} else {
					$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
				}
			}
		} elseif ($lastpage >= 7 + ($adjacents * 2)) //enough pages to hide some
			{
			//close to beginning; only hide later pages
			if ($page < 1 + ($adjacents * 3)) {
				for ($counter = 1; $counter < 4 + ($adjacents * 2); $counter++) {
					if ($counter == $page) {
						$pagination .= "<li class=\"paginationCurrent\">$counter</li>";
					} elseif ($counter == $lastpage) {
						$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
					} else {
						$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
					}
				}
				$pagination .= "<li class=\"paginationElipses\">...</li>";
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($lpm1 - 1) * $limit) . '&max=' . $limit . $pagestring . $lpm1 . $limitstring . $limit . "\">$lpm1</a></li>";
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($lastpage - 1) * $limit) . '&max=' . $lastpageAmount . $pagestring . $lastpage . $limitstring . $limit . "\">$lastpage</a></li>";
			}
			//in middle; hide some front and some back
			elseif ($lastpage - ($adjacents * 2) > $page && $page > ($adjacents * 2)) {
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=0&max=' . $limit . $pagestring . "1" . $limitstring . $limit . "\">1</a></li>";
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (1 * $limit) . '&max=' . $limit . $pagestring . "2" . $limitstring . $limit . "\">2</a></li>";
				$pagination .= "<li class=\"paginationElipses\">...</li>";
				for ($counter = $page - $adjacents; $counter <= $page + $adjacents; $counter++) {
					if ($counter == $page) {
						$pagination .= "<li class=\"paginationCurrent\">$counter</li>";
					} elseif ($counter == $lastpage) {
						$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
					} else {
						$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
					}
				}
				$pagination .= "<li class=\"paginationElipses\">...</li>";
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($lpm1 - 1) * $limit) . '&max=' . $limit . $pagestring . $lpm1 . $limitstring . $limit . "\">$lpm1</a></li>";
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($lastpage - 1) * $limit) . '&max=' . $lastpageAmount . $pagestring . $limitstring . $limit . $lastpage . "\">$lastpage</a></li>";
			}
			//close to end; only hide early pages
			else {
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=0&max=' . $limit . $pagestring . "1" . $limitstring . $limit . "\">1</a></li>";
				$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (1 * $limit) . '&max=' . $limit . $pagestring . "2" . $limitstring . $limit . "\">2</a></li>";
				$pagination .= "<li class=\"paginationElipses\">...</li>";
				for ($counter = $lastpage - (1 + ($adjacents * 3)); $counter <= $lastpage; $counter++) {
					if ($counter == $page) {
						$pagination .= "<li class=\"paginationCurrent\">$counter</li>";
					} elseif ($counter == $lastpage) {
						$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
					} else {
						$pagination .= "<li class=\"paginationPage\"><a href=\"" . $base_url . '/search-results/' . $searchterm . '?start=' . (($counter - 1) * $limit) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit . "\">$counter</a></li>";
					}
				}
			}
		}
		
		//next button
		if ($page == $lpm1) {
			$pagination .= '<li class="pager_next">';
			$pagination .= '<a href="' . $base_url . '/search-results/' . $searchterm . '?start=' . (($next - 1) * $limit) . '&max=' . $lastpageAmount . $pagestring . $next . $limitstring . $limit . '">';
			$pagination .= '<img src="' . $imagespath . '/pagination/next.png"/>';
			$pagination .= '</a>';
			$pagination .= '</li>';
			
		} elseif ($page < $lpm1) {
			$pagination .= '<li class="pager_next">';
			$pagination .= '<a href="' . $base_url . '/search-results/' . $searchterm . '?start=' . (($next - 1) * $limit) . '&max=' . $limit . $pagestring . $next . $limitstring . $limit . '">';
			$pagination .= '<img src="' . $imagespath . '/pagination/next.png"/>';
			$pagination .= '</a>';
			$pagination .= '</li>';
			
		} else {
			$pagination .= '<li class="pager_next">';
			$pagination .= '<img src="' . $imagespath . '/pagination/nextUnable.png"/>';
			$pagination .= '</li>';
		}
		$pagination .= "</ul>\n";
		$pagination .= "</div>";
	}
	
	
	
	return $pagination;
	
}
?>