var colorForOpType = ["#589017", "#f4b342", "#f4eb41"]; //Should use toggleclass
var actualStep = 0;
var stepZero = [false, false, false];
var stepOne = [false, false, false, false, false, false, false, false];
var stepTwo = [false];
var imageCarousel;

var placeSearch, autocomplete;


$(document).ready(function(){

	var title, description, opType, delType, price, street, number, floor, apartment, neighborhood, propType, cArea, tArea, tPrice, rooms, baths, garage;

	setUpVariables();
	setUpFormSelectors();
	setUpButtons();
	setUpChangeHooks();
	imagesCarouselInstances = initImagesCarousel();
	checkImages();
	checkTabIndexing();

	$('input#p-title, textarea#p-desc').characterCounter();

});

function initImagesCarousel() {

	imageCarousel = $('.carousel.carousel-slider');

	imageCarousel.carousel({
    	fullWidth: true,
    	indicators: false
  	});

  	$('.moveNextCarousel').click(function(e){
      e.preventDefault();
      e.stopPropagation();
      $('#imageCarousel').carousel('next');
  });

  $('.movePrevCarousel').click(function(e){
    var id = $(this).data("id");
    e.preventDefault();
    e.stopPropagation();
    $('#imageCarousel').carousel('next');
  });

}

function checkImages(){


	$("#imagesFiles").change( function(evt) {


	    var tgt = evt.target || window.event.srcElement,
	        files = tgt.files;

	    // FileReader support
	    if (FileReader && files && files.length) {

	    	//check image
			
           	$("#noPicture").remove();
	    	$('#imageCarousel').find('.carousel-item').remove();

	    	var imageCheck = true;
            for (var i = 0; i < files.length; i++) {

            	var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.IMAGE, null]];
 				
 				imageCheck = imageCheck && checkField($("#file-upload-line"), files[i], rules);

            	var fr = new FileReader();

            	fr.onload = function (e) {
		           	var carouselItem = '<a class="carousel-item" href="">' +
		    					'<div class="image-cont">' + 
		    						'<img src="' + e.target.result + '"/>' +
		    					'</div>' +
		    				  '</a>'; 

		            $('#imageCarousel').append(carouselItem);
		            var childs = $('#imageCarousel').children().size();

		            if ((childs - 1) > 1) {
		            	$("#carouselArrows").removeClass('invisible');
		            } else {
		            	$("#carouselArrows").addClass('invisible');
		            }
		            
		            if (imageCarousel.hasClass('initialized')){
	    				imageCarousel.removeClass('initialized')
					}

					imageCarousel.carousel({
				    	fullWidth: true,
				    	indicators: false
				  	});
	        	}

  				fr.readAsDataURL(files[i]);	
            }

            stepTwo[0] = imageCheck;
	      

	    } else {
	        // fallback -- perhaps submit the input to an iframe and temporarily store
	        // them on the server until the user's session ends.
	    }

	});

}

function setUpVariables(){

	title = $('#p-title');
	description = $('#p-desc');
	opType = $('#operationSelector');
	delType = $('#delSelector');
	price = $('#p-price');
	street = $('#p-street');
	number = $('#p-number');
	floor = $('#p-floor');
	apartment = $('#p-ap');
	neighborhood = $('#p-neigh');
	propType = $('#typeSelector');
	cArea = $('#p-cArea');
	tArea = $('#p-tArea');
	tPrice = $('#p-tPrice');
	rooms = $('#p-rooms');
	baths = $('#p-baths');
	garage = $('#garageSelector');


}

function setUpFormSelectors(){
	opType.formSelect();
	delType.formSelect();
	propType.formSelect();
	garage.formSelect();
}

function moveTo(div, offset) {

	$('.builder-inner-box').animate({
	        scrollTop: div * offset
	}, 1000);

	actualStep = div;

	var bc;

	if (div == 0) {
		bc = $("#bread-publi");
		title.focus();
	} else if (div == 1) {
		bc =  $("#bread-info");
		street.focus();
	} else {
		$("#bread-img");
	}

	breadcrumbActive(bc);
}

function breadcrumbActive(div){
	$('#bread-wrapper-col').find('.active').removeClass('active');
	$(div).addClass("active");
}

function setUpButtons(){

	$("#bread-publi").click(function() {
		moveTo(0, 800);
	});

	$("#bread-info").click(function() {
		if (actualStep < 1) return;
		moveTo(1, 800);
	});

	$("#bread-img").click(function() {
		if (actualStep < 2) return;
		 moveTo(2, 800);
	});

	$("#nextZero").click(function() {

		if (checkStep(stepZero)) {
			moveTo(1, 800);	
		}
	});

	$("#nextOne").click(function() {

		if (checkStep(stepOne)) {
			moveTo(2, 800);	
		}
	});

	$("#submitBtn").click(function() {

		if (stepTwo[0]) {
			$("#mainForm").submit(); 
		}
		
	});



}

function setUpChangeHooks(){

	title.on("input", function(e) {
 		var input = $(this).val();
 		$('#title-preview').text(input);

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [6, 80] ]];

 		stepZero[0] = checkField($(this), input, rules);
	});

	description.on("input", function(e) {
 		var input = $(this).val();
 		$('#description-preview').text(input);

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [6, 300] ]];

 		stepZero[1] = checkField($(this), input, rules);
	});

	opType.change(function() {
		$(".property-card").css("border-color", colorForOpType[$(this).val()]);
	});

	delType.change(function() {
		$("#delType-preview").text($("#delSelector option:selected").text());
	});

	price.on("input", function(e) {
 		var input = $(this).val();
 		$('#price-preview').text("US$ " + formatCurrency(input));

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 10000000000] ]];

 		stepZero[2] = checkField($(this), input, rules);

	});

	street.on("input", function(e) {
 		var input = $(this).val();
 		$('#street-preview').text(input + " ");

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [3, 30] ]];

 		stepOne[0] = checkField($(this), input, rules);
	});


	// neighborhood.on("change", function(e) {
 // 		var input = $(this).val();
	// 	console.log("ACA: " + input);
 // 		$('#neighborhood-preview').text(input);

 // 		var rules = [[RulesEnum.NOT_NULL, null],
 // 					[RulesEnum.LIMITED_SIZE, [5, 30] ]];
 // 		stepOne[4] = checkField($(this), input, rules);
	// });

	cArea.on("input", function(e) {
 		var input = $(this).val();

 		if (input != ""){
 			$('#meters-post').removeClass("invisible");
 		} else {
 			$('#meters-post').addClass("invisible");
 		}

 		$('#cArea-preview').text(input);

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 9999999] ]];
 		stepOne[4] = checkField($(this), input, rules);
	});

	//Not in card

	floor.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [0, 200] ]];
 		stepOne[1] = checkField($(this), input, rules);
	});

	apartment.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [1, 10] ]];
 		stepOne[2] = checkField($(this), input, rules);
	});

	tArea.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 9999999] ]];
 		stepOne[4] = checkField($(this), input, rules);
	});

	tPrice.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 100000] ]];
 		stepOne[5] = checkField($(this), input, rules);
	});

	rooms.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 99] ]];
 		stepOne[6] = checkField($(this), input, rules);
	});

	baths.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 99] ]];
 		stepOne[7] = checkField($(this), input, rules);
	});


}

function checkTabIndexing() {

	$('body').keydown(function(e) {
    	
    	var code = e.keyCode || e.which;
    	var $focused = $(':focus');
    	var objId = $focused[0].id;

    	if (code == '9') {
    		if (objId == 'nextZero'){
    			e.preventDefault();
    			title.focus();
    		}

    		if (objId == 'nextOne'){
    			e.preventDefault();
    			street.focus();
    		}    		

    	}
 	});
}

function checkStep(state) {

	var res = true;
	state.forEach( function(value, index, array) {
        res = res && value;
	});

	return res;
}

function formatCurrency(n, c, d, t) {
  var c = isNaN(c = Math.abs(c)) ? 0 : c,
    d = d == undefined ? "." : d,
    t = t == undefined ? "." : t,
    s = n < 0 ? "-" : "",
    i = String(parseInt(n = Math.abs(Number(n) || 0))),
    j = (j = i.length) > 3 ? j % 3 : 0;

	return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
}

function checkField(field, inputData, rules) {

	if (field == neighborhood) return true;

	var valid = validateField(inputData, rules);

	if (!valid) {
		$(field).removeClass("valid");
		$(field).removeClass("validate");
		$(field).addClass("invalid");
	} else {
		$(field).removeClass("invalid");
		$(field).addClass("valid");
	}

	return valid;

}

function initGoogleMapsAutocomplete() {
	// Create the autocomplete object, restricting the search to geographical
	// location types.
	autocomplete = new google.maps.places.Autocomplete(
	    /** @type {!HTMLInputElement} */(document.getElementById('p-street')),
	    {types: ['geocode']});

	autocomplete.setComponentRestrictions(
            {'country': ['ar']});

	// When the user selects an address from the dropdown, populate the address
	// fields in the form.
	autocomplete.addListener('place_changed', fillInAddress);
}


function fillInAddress() {
	// Get the place details from the autocomplete object.
	var place = autocomplete.getPlace();

	var n = place.address_components[2].short_name;
	neighborhood.val(n);
	$('#neighborhood-preview').text(n);
	stepOne[3] = n.length >= 3;

}

function geolocate() {
	console.log("asd");
	  navigator.geolocation.getCurrentPosition(function(position) {
	  	console.log("recibi");
	    var geolocation = {
	      lat: position.coords.latitude,
	      lng: position.coords.longitude
	    };
	    var circle = new google.maps.Circle({
	      center: geolocation,
	      radius: position.coords.accuracy
	    });
	    autocomplete.setBounds(circle.getBounds());
	  });
}
   

