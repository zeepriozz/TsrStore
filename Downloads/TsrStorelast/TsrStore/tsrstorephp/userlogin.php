<?php 
include("connection.php");

$otp=$_POST["otp"];
$query="SELECT * FROM `userlogin` WHERE otp='$otp'";
$result=mysqli_query($con,$query);
$row=mysqli_fetch_row($result);
if(mysqli_num_rows($result)>0)
{
    $response["status"]="1";
    $response["message"]="Login Successful";
    $response["id"]=$row[0];
    $response["username"]=$row[1];
    $response["email"]=$row[2];
    $response["phone"]=$row[3];
    $response["password"]=$row[4];
   

  

}
else
{
    $response["status"]="0";
    $response["message"]="Login failed";
    $response["id"]="";
    $response["username"]="";
    $response["email"]="";
    $response["phone"]="";
    $response["password"]="";
  
}
echo json_encode($response);
?>