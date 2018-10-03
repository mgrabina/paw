$(document).ready(function() {
    $("#submit-btn").attr("disabled", "true");
    $("#u-repeat-password").change(function () {
       if($(this).val() == $("#u-password").val()){
           $("#submit-btn").removeAttr("disabled");
       }else{
           $("#submit-btn").attr("disabled", "true");
       }
    });
});
