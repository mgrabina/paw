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
  checkOperationType();
  checkChipNames();

});

function setUpFilters(){

   $(".filter-click").each(function(index) {
    $(this).on("click", function(){
        var field = $(this).data("field");
        var json = {};

        if ($(this).data("value")){
          json[field] = $(this).data("value");
        } else {
          json[field] = $(this).text();
        }
        
        addManyQueryParamsAndRedirect(json);
    });
  });

   $(".chip-click").each(function(index) {
    $(this).on("click", function(){
        removeManyQueryParamsAndRedirect([$(this).data("field")]);
    });
  });


   $('#price-filter').click(function(e){
      
      var min = $("#min-price").val();
      var max = $("#max-price").val();
      var json = {};

      if (max >= min){
        json["minPrice"] = min;
        json["maxPrice"] = max;
        addManyQueryParamsAndRedirect(json);
      } 

  });

   $('#area-filter').click(function(e){
      
      var min = $("#min-s").val();
      var max = $("#max-s").val();
      var json = {};

      if (max >= min){
        json["minArea"] = min;
        json["maxArea"] = max;
        addManyQueryParamsAndRedirect(json);
      } 

  });

  $('#remove-filters').click(function(e){
    window.location =  window.location.origin;
  });

  $('#general-filter').click(function(e){
    
    var gValue = $("#garage-input").is(":checked") * 1;
    var json = {}; 

    json["garage"] = gValue;

    if (gValue == 1) {
      addManyQueryParamsAndRedirect(json);
    } else {
      removeManyQueryParamsAndRedirect(['garage']);
    }


  });

   
   

}

function checkOperationType(){

  var params = urlParamsToJson(location.search);
  var sell = $("#sell-button");
  var rent = $("#rent-button");
  var temporal_rent = $("#temporal-rent-button");

  console.log(params);
  if (params.operation != null) {

    $('#operationTypeContainer').find('.active').removeClass('active');

    if (params.operation == "rent") {
      rent.addClass('active');
    } else if (params.operation == "temporal_rent"){
      temporal_rent.addClass('active');
    } else {
      sell.addClass('active');
    }
  } else {
    sell.addClass('active');
  }

  $('.tabs').tabs();
  var active = $('.tabs .active').attr('href');
  $('.tabs-content ' + active).show();
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

  $('.contact-modal-link').click(function(e){
    $("#contact-modal").modal();
    $("#property-id-input-modal").val($(this).data("id"));
    $("#property-name-input-modal").html($(this).data("name"));
  });


}

function checkChipNames(){
  
    $(".chip-text").each(function( index ) {

      var fullText = $(this).text();

      if (!isNaN(fullText)){

        var params = urlParamsToJson(location.search);
        
        if (params.minPrice == fullText)
          $(this).text(">= " + fullText);
        else if (params.maxPrice == fullText)
          $(this).text("<= " + fullText);
        else if (params.rooms == fullText)
          $(this).text(fullText + "r");
        else if( params.minArea == fullText)
          $(this).text(">= " + fullText + "m2");
        else if (params.maxArea == fullText)
          $(this).text("<= " + fullText + "m2");
        else if (params.date == fullText)
          $(this).text("<= " + fullText + "d");
        else if (params.garage == fullText)
          $(this).text("Garage");

      }
      
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
