$(document).ready(function(){
 
 	$('.tabs').tabs();
 	$('.dropdown-trigger').dropdown();
 	$('.carousel.carousel-slider').carousel({
    	fullWidth: true,
    	indicators: true
  	});

  	var elem = document.querySelector('#filters-id');
	var instance = M.Collapsible.init(elem, {
  		accordion: false
	});

	setUpControllers();
  setUpFilters();

});

function setUpFilters(){

   $(".filter-click").each(function(index) {
    $(this).on("click", function(){
        addQueryParam($(this).data("field"), $(this).text());

    });
  });

   $(".chip-click").each(function(index) {
    $(this).on("click", function(){
        removeQueryParam($(this).data("field"));

    });
  });

   


}

function setUpControllers(){

  $(".property-card").hover( function() {
    		$( this ).addClass( "selected-card" );
  		}, function() {
    		$( this ).removeClass( "selected-card" );
  		}
	);

  $('.moveNextCarousel').click(function(e){
      var id = $(this).data("id");
      e.preventDefault();
      e.stopPropagation();
      $('#carousel-' + id).carousel('next');
  });

  $('.movePrevCarousel').click(function(e){
    var id = $(this).data("id");
    e.preventDefault();
    e.stopPropagation();
    $('#carousel-' + id).carousel('next');
  });
	

}

function addFavorite(propertyId) {
    $.ajax({
        type: "POST",
        url: ""+getContextPath()+"/api/addFavourite",
	    data: {"propertyId": propertyId},
    	success: function(res) {
			//add favourite
        }
	});
}

function deleteFavorite(propertyId) {
    $.ajax({
        type: "POST",
        url: ""+getContextPath()+"/api/deleteFavourite",
        data: {"propertyId": propertyId},
        success: function(res) {
            //add favourite
        }
    });
}

/*
    Adds many query params
    Format JSON
    {
        query1 : value1,
        query2 : value2
    }
 */
function addManyQueryParamsAndRedirect(json){
    var currentParams = urlParamsToJson(location.search);
    for (var item in json){
        currentParams[item] = json[item];
    }
    var newSearchQuery = Object.keys(currentParams).map(function(k) {
        return encodeURIComponent(k) + '=' + encodeURIComponent(currentParams[k])
    }).join('&');
    window.location.replace(location.origin  + '?' + newSearchQuery);
}

/*
    Removes many query params and redirect
    Format array
    [ 'query1', 'query2']
 */
function removeManyQueryParamsAndRedirect(array) {
    var currentParams = urlParamsToJson(location.search);
    array.forEach(function (item) {
        delete currentParams[item];
    });
    var newSearchQuery = Object.keys(currentParams).map(function(k) {
        return encodeURIComponent(k) + '=' + encodeURIComponent(currentParams[k])
    }).join('&');
    window.location.replace(location.origin  + '?' + newSearchQuery);
}
//Helper Function
function urlParamsToJson(url) {
    var hash;
    var myJson = {};
    if (url == "" || url == "?")
        return myJson;
    var hashes = url.slice(url.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        myJson[hash[0]] = hash[1];
    }
    return myJson;
}

function getPage(number) {
	addManyQueryParamsAndRedirect({
        "page" : number
    });
}
