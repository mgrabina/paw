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

});

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