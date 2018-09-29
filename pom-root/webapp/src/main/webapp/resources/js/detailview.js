$(document).ready(function(){


    if($('.heart').attr('addedToFavourites') === "true") {
        $('.heart').addClass('fill');
    }

	$('.heart').click(function() {
		$(this).toggleClass('fill');

		var toPropertyId = $(this).attr("idProperty");

		$(this).hasClass('fill') ? setFavourite(toPropertyId) : deleteFavourite(toPropertyId);
    });

	function setFavourite(toPropertyId) {
        $.ajax({
            type: "POST",
            url: ""+getContextPath()+"/api/setFavourite",
            data: {"toPropertyId": toPropertyId},
            success: function(res){

            },
            error: function(err){
                //handle error
            }
        });
	}

    function deleteFavourite(toPropertyId) {
        $.ajax({
            type: "POST",
            url: ""+getContextPath()+"/api/deleteFavourite",
            data: {"toPropertyId": toPropertyId},
            success: function(res){

            },
            error: function(err){
                //handle error
            }
        });
    }
});