<?php
include("connection.php");
$username=$_POST['username'];
$email=$_POST['email'];
$phone=$_POST['phone'];
$password=$_POST['password'];
$otp=$_POST['otp'];



$q ="INSERT INTO userlogin  (username,email,phone,password,otp) VALUES ('$username','$email','$phone','$password','$otp')";

$result=mysqli_query($con,$q);
if($result){
    $response["status"]="1";
    $response["message"]=" Registration successful";
}
else{
    $response["status"]="0";
    $response["message"]="Registration failed";
}
echo json_encode($response);
?>
