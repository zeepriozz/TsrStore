<?php
include ("connection.php");
$shopphonenumber = $_POST['shopphonenumber'];
$q = "select * from shopregistration where shopphonenumber ='$shopphonenumber'";
$result = mysqli_query($con,$q) or die(mysqli_errror($con));
if($result)
{
    $response['status'] ="1";
    $response['message'] = "data fetched";
}
else
{
    $response['status'] ="0";
    $response['message'] = "enterd phone number is not registered";
}
echo json_encode($response);
?>