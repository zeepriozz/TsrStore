<?php
include ("connection.php");
$shopphonenumber = $_POST['shopphonenumber'];
$password = $_POST['password'];

$q = "update shopregistration set password = '$password' where shopphonenumber ='$shopphonenumber'";
$result = mysqli_query($con,$q) or die(mysqli_errror($con));
if($result)
{
    $response['status'] ="1";
    $response['message'] = "Password changed successfully";
}
else
{
    $response['status'] ="0";
    $response['message'] = "Failed";
}
echo json_encode($response);
?>


