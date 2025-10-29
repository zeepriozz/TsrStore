<?php
include("connection.php");
$id =$_POST["id"];
$price =$_POST["price"];


$query="update additem set price='$price' where id='$id '";
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