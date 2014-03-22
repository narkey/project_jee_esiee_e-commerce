<%-- 
    Document   : achat
    Created on : 21 mars 2014, 14:29:42
    Author     : zhao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jQuery UI Spinner - Default functionality</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="/resources/demos/external/jquery.mousewheel.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<c:import url="../header.jsp"></c:import>    
<script>
$(function() {
var spinner = $( "#spinner" ).spinner();
$( "#disable" ).click(function() {
if ( spinner.spinner( "option", "disabled" ) ) {
spinner.spinner( "enable" );
} else {
spinner.spinner( "disable" );
}
});
$( "#destroy" ).click(function() {
if ( spinner.data( "ui-spinner" ) ) {
spinner.spinner( "destroy" );
} else {
spinner.spinner();
}
});
$( "#getvalue" ).click(function() {
alert( spinner.spinner( "value" ) );
});
$( "#setvalue" ).click(function() {
spinner.spinner( "value", 5 );
});
$( "button" ).button();
});
</script>
</head>
<body>
<p>
<label for="spinner">Select a value:</label>
<input id="spinner" name="value">
</p>
<p>
<button id="disable">Toggle disable/enable</button>
<button id="destroy">Toggle widget</button>
</p>
<p>
<button id="getvalue">Get value</button>
<button id="setvalue">Set value to 5</button>
</p>
      
    <script src="jquery/css/start/images/jquery-ui.min.js"></script>    
    <script src="jquery/js/jquery-ui.min.js"></script> 
    <script src="bootstrap/js/jquery.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
    
   
</html>
