<?php 
include("connection.php");

$shopname=$_POST["shopname"];
$password=$_POST["password"];
$query="SELECT * FROM `shopregistration` WHERE shopname='$shopname' && password='$password'";
$result=mysqli_query($con,$query);
$row=mysqli_fetch_row($result);
if(mysqli_num_rows($result)>0)
{
    $response["status"]="1";
    $response["message"]="Login Successful";

    $response["id"]=$row[0];
    $response["shopname"]=$row[1];
    $response["address"]=$row[2];
    $response["shopphonenumber"]=$row[3];
    $response["password"]=$row[4];
    $response["logo"]=$row[5];


  

}
else
{
    $response["status"]="0";
    $response["message"]="Login failed";
    $response["id"]="";
    $response["shopname"]="";
    $response["address"]="";
    $response["shopphonenumber"]="";
    $response["password"]="";
    $response["logo"]="";
}
echo json_encode($response);
?>