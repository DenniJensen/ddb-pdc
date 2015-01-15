<?php
//function to return the pagination string
/**
 * @file
 * Pagination theme implementation to output the pagination string
 * 
 * Available variables:
 * - $searchterm
 * - $ddbResultsAmount: The amount of all found items to calculate pagination.
 * - $imagespath: The relative link to the images folder of the module.
 *
 * @see ddb_pdc_search_results()
 *
 */
//defaults
$pagestring  = "&page=";
$limitstring = "&limit=";
$adjacents   = 1;
$page        = $_GET['page'];
$limit       = $_GET['limit'];
global $base_url;
//other vars
$prev           = $page - 1; //previous page is page - 1
$next           = $page + 1; //next page is page + 1
$lastpage       = ceil( $ddbResultsAmount / $limit ); //lastpage is = total items / items per page, rounded up.
$lpm1           = $lastpage - 1; //last page minus 1
$lastpageAmount = ( $ddbResultsAmount % $limit == 0 ) ? $limit : $ddbResultsAmount % $limit;
?>
<?php
if ( $lastpage > 1 ):
?>
<div class="paginationWrapper">
<ul class="pagination">
    
    <!-- previous button -->
<?php
	if ( $page > 1 ):
?>
<li class="pager_previous"> <a href="<?php
		print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $prev - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $prev . $limitstring . $limit;
?>"> <img src="<?php
		print $imagespath . '/pagination/previous.png';
?>"/> </a> </li>
<?php
	else:
?>
<li class="pager_previous"><img src="<?php
		print $imagespath . '/pagination/previousUnable.png';
?>"/>
<?php
	endif;
?>
      
<!-- pages-->
<?php
	if ( $lastpage < 7 + ( $adjacents * 2 ) ): //not enough pages to bother breaking it up 
?>
<?php
		for ( $counter = 1; $counter <= $lastpage; $counter++ ):
?>
<?php
			if ( $counter == $page ):
?>
<li class="paginationCurrent"><?php
				print $counter;
?></li>
<?php
			elseif ( $counter == $lastpage ):
?>
<li class="paginationPage"><a href="<?php
				print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit;
?>"><?php
				print $counter;
?></a></li>
<?php
			else:
?>
<li class="paginationPage"><a href="<?php
				print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit;
?>"><?php
				print $counter;
?></a></li>
<?php
			endif;
?>
<?php
		endfor;
?>
<?php
	elseif ( $lastpage >= 7 + ( $adjacents * 2 ) ): //enough pages to hide some 
?>
<!-- close to beginning; only hide later pages -->
<?php
		if ( $page < 1 + ( $adjacents * 3 ) ):
?>
<?php
			for ( $counter = 1; $counter < 4 + ( $adjacents * 2 ); $counter++ ):
?>
<?php
				if ( $counter == $page ):
?>
<li class="paginationCurrent"><?php
					print $counter;
?></li>
<?php
				elseif ( $counter == $lastpage ):
?>
<li class="paginationPage"><a href="<?php
					print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit;
?>"><?php
					print $counter;
?></a></li>
<?php
				else:
?>
<li class="paginationPage"><a href="<?php
					print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit;
?>"><?php
					print $counter;
?></a></li>
<?php
				endif;
?>
<?php
			endfor;
?>
<li class="paginationElipses">...</li>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $lpm1 - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $lpm1 . $limitstring . $limit;
?>"><?php
			print $lpm1;
?></a></li>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $lastpage - 1 ) * $limit ) . '&max=' . $lastpageAmount . $pagestring . $lastpage . $limitstring . $limit;
?>"><?php
			print $lastpage;
?></a></li>

<!-- in middle; hide some front and some back -->
<?php
		elseif ( $lastpage - ( $adjacents * 2 ) > $page && $page > ( $adjacents * 2 ) ):
?>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=0&max=' . $limit . $pagestring . "1" . $limitstring . $limit;
?>">1</a></li>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=' . ( 1 * $limit ) . '&max=' . $limit . $pagestring . "2" . $limitstring . $limit;
?>">2</a></li>
<li class="paginationElipses">...</li>
<?php
			for ( $counter = $page - $adjacents; $counter <= $page + $adjacents; $counter++ ):
?>
<?php
				if ( $counter == $page ):
?>
<li class="paginationCurrent"><?php
					print $counter;
?></li>
<?php
				elseif ( $counter == $lastpage ):
?>
<li class="paginationPage"><a href="<?php
					print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit;
?>"><?php
					print $counter;
?></a></li>
<?php
				else:
?>
<li class="paginationPage"><a href="<?php
					print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit;
?>"><?php
					print $counter;
?></a></li>
<?php
				endif;
?>
<?php
			endfor;
?>
<li class="paginationElipses">...</li>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $lpm1 - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $lpm1 . $limitstring . $limit;
?>"><?php
			print $lpm1;
?></a></li>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $lastpage - 1 ) * $limit ) . '&max=' . $lastpageAmount . $pagestring . $limitstring . $limit . $lastpage;
?>"><?php
			print $lastpage;
?></a></li>
			
<!-- close to end; only hide early pages -->
<?php
		else:
?>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=0&max=' . $limit . $pagestring . "1" . $limitstring . $limit;
?>">1</a></li>
<li class="paginationPage"><a href="<?php
			print $base_url . '/search-results/' . $searchterm . '?start=' . ( 1 * $limit ) . '&max=' . $limit . $pagestring . "2" . $limitstring . $limit;
?>">2</a></li>
<li class="paginationElipses">...</li>
<?php
			for ( $counter = $lastpage - ( 1 + ( $adjacents * 3 ) ); $counter <= $lastpage; $counter++ ):
?>
<?php
				if ( $counter == $page ):
?>
<li class="paginationCurrent"><?php
					print $counter;
?></li>
<?php
				elseif ( $counter == $lastpage ):
?>
<li class="paginationPage"><a href="<?php
					print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $lastpageAmount . $pagestring . $counter . $limitstring . $limit;
?>"><?php
					print $counter;
?></a></li>
<?php
				else:
?>
<li class="paginationPage"><a href="<?php
					print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $counter - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $counter . $limitstring . $limit;
?>"><?php
					print $counter;
?></a></li>
<?php
				endif;
?>
<?php
			endfor;
?>
<?php
		endif;
?>
<?php
	endif;
?>		
<!-- next button -->
<?php
	if ( $page == $lpm1 ):
?>
<li class="pager_next">
<a href="<?php
		print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $next - 1 ) * $limit ) . '&max=' . $lastpageAmount . $pagestring . $next . $limitstring . $limit . '"> <img src="' . $imagespath . '/pagination/next.png';
?>"/> </a> </li>
<?php
	elseif ( $page < $lpm1 ):
?>
<li class="pager_next"> <a href="<?php
		print $base_url . '/search-results/' . $searchterm . '?start=' . ( ( $next - 1 ) * $limit ) . '&max=' . $limit . $pagestring . $next . $limitstring . $limit;
?>"> <img src="<?php
		print $imagespath . '/pagination/next.png';
?>"/> </a> </li>
<?php
	else:
?>
<li class="pager_next"> <img src="<?php
		print $imagespath . '/pagination/nextUnable.png';
?>"/> </li>
<?php
	endif;
?>
</ul>
</div>
<?php
endif;
?>