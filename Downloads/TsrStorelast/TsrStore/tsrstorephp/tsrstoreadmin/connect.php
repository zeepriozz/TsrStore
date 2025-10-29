<?php 
$con=new mysqli("localhost","root","");
$db=mysqli_select_db($con,"tsrstore") or die(mysqli_error($con));
?>