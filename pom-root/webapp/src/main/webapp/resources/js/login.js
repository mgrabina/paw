$(document).ready(function() {
    if(window.location.search.substring(1).indexOf("error") >= 0){
        $("#invalid-login-credentials").show();
    }else{
        $("#invalid-login-credentials").hide();
    }
});