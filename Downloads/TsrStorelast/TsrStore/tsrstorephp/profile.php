<?php
include("connection.php");
$id=$_POST["id"];
$shopname =$_POST["shopname"];
$shopphonenumber =$_POST["shopphonenumber"];
$password =$_POST["password"];
$logo=$_POST["logo"];
//$amb=$_POST['ambulance_type'];

$query="update shopregistration set shopname='$shopname',shopphonenumber='$shopphonenumber',password='$password' where id='$id'";
$result=mysqli_query($con,$query);
if($result)
{
$response["status"]="1";
$response["message"]="updation successfull";
}
else
{
    $response["status"]="0";
    $response["message"]="updation faild";
}
echo json_encode($response);
?>