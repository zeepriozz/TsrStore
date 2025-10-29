<?php
include("connection.php");
$id=$_POST["id"];
$username =$_POST["username"];
$email =$_POST["email"];
$phone =$_POST["phone"];
$password =$_POST["password"];
//$amb=$_POST['ambulance_type'];

$query="update userlogin set username='$username',email='$$email',phone='$phone',password='$password' where id='$id'";
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