var colorForOpType = ["#589017", "#f4b342", "#f4eb41"]; //Should use toggleclass
var actualStep = 0;
var stepZero = [false, false, false];

$(document).ready(function(){

	var title, description, opType, delType, price, street, number, floor, apartment, neighborhood, propType, cArea, tArea, tPrice, rooms, baths, garage;

	setUpVariables();
	setUpFormSelectors();
	setUpButtons();
	setUpChangeHooks();

	$('input#p-title, textarea#p-desc').characterCounter();

	//moveTo(2, 600);

});

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
	} else if (div == 1) {
		bc =  $("#bread-info");
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
		moveTo(0, 600);
	});

	$("#bread-info").click(function() {
		if (actualStep < 1) return;
		moveTo(1, 600);
	});

	$("#bread-img").click(function() {
		if (actualStep < 2) return;
		 moveTo(2, 600);
	});

	$("#nextZero").click(function() {

		if (checkStep(stepZero)) {
			moveTo(1, 600);	
		}
	});

	$("#nextOne").click(function() {

		if (checkStep(stepOne)) {
			moveTo(2, 600);	
		}
	});

}

function setUpChangeHooks(){

	title.on("input", function(e) {
 		var input = $(this).val();
 		$('#title-preview').text(input);

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [6, 30] ]];

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
 					[RulesEnum.LIMITED_SIZE, [3, 20] ]];

 		checkField($(this), input, rules);
	});

	number.on("input", function(e) {
 		var input = $(this).val();
 		$('#number-preview').text(input + " - ");

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [3, 5] ]];
 		checkField($(this), input, rules);

	});

	neighborhood.on("input", function(e) {
 		var input = $(this).val();
 		$('#neighborhood-preview').text(input);

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [5, 20] ]];
 		checkField($(this), input, rules);
	});

	cArea.on("input", function(e) {
 		var input = $(this).val();

 		if (input != ""){
 			$('#meters-post').removeClass("invisible");
 		} else {
 			$('#meters-post').addClass("invisible");
 		}

 		$('#cArea-preview').text(input);

 		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 100000000] ]];
 		checkField($(this), input, rules);
	});

	//Not in card

	floor.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [0, 100] ]];
 		checkField($(this), input, rules);
	});

	apartment.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_SIZE, [1, 10] ]];
 		checkField($(this), input, rules);
	});

	tArea.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 100000000] ]];
 		checkField($(this), input, rules);
	});

	tPrice.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 100000] ]];
 		checkField($(this), input, rules);
	});

	rooms.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 1000] ]];
 		checkField($(this), input, rules);
	});

	baths.on("input", function(e) {
		var input = $(this).val();

		var rules = [[RulesEnum.NOT_NULL, null],
 					[RulesEnum.LIMITED_NUMBER, [1, 1000] ]];
 		checkField($(this), input, rules);
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


