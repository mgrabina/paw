
var RulesEnum = Object.freeze({"NOT_NULL": 0, "LIMITED_SIZE": 1, "LIMITED_NUMBER": 2});
var validatorRules = [fieldNotNull, fieldLimitSize, fieldLimitNumber];

function fieldNotNull(data, params) {
    return data != null;
}

function fieldLimitSize(data, params) {
    return data.length >= params[0] && data.length <= params[1];
}

function fieldLimitNumber(data, params) {
    return data >= params[0] && data <= params[1];
}

function validateField(inputData, rules) {

    var res = true;

	rules.forEach( function(ruleData, index, array) {

    	ruleFunction = validatorRules[ruleData[0]];
        params = ruleData[1];

        res = res && ruleFunction(inputData, params);
	});

    return res;

}
