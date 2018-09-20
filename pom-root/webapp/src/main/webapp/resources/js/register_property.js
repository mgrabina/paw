var colorForOpType = ["#589017", "#f4b342", "#f4eb41"]; //Should use toggleclass

$(document).ready(function(){

	var title, description, opType, delType, price, street, number, floor, apartment, neighborhood, propType, cArea, tArea, tPrice, rooms, baths, garage;

	setUpVariables();
	setUpFormSelectors();
	


	setUpChangeHooks();
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

function setUpChangeHooks(){

	title.on("input", function(e) {
 		var input = $(this).val();
 		$('#title-preview').text(input);
	});

	description.on("input", function(e) {
 		var input = $(this).val();
 		$('#description-preview').text(input);
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
	});

	street.on("input", function(e) {
 		var input = $(this).val();
 		$('#street-preview').text(input + " ");
	});

	number.on("input", function(e) {
 		var input = $(this).val();
 		$('#number-preview').text(input + " - ");
	});

	neighborhood.on("input", function(e) {
 		var input = $(this).val();
 		$('#neighborhood-preview').text(input);
	});



}

function formatCurrency(n, c, d, t) {
  var c = isNaN(c = Math.abs(c)) ? 0 : c,
    d = d == undefined ? "." : d,
    t = t == undefined ? "." : t,
    s = n < 0 ? "-" : "",
    i = String(parseInt(n = Math.abs(Number(n) || 0))),
    j = (j = i.length) > 3 ? j % 3 : 0;

  return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
};
