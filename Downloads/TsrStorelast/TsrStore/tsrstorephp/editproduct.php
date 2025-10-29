<?php
include("connection.php");
$id =$_POST["id"];
$productname =$_POST["productname"];
$price =$_POST["price"];
$description =$_POST["description"];

$query="update additem set productname='$productname',price='$price',description='$description' where id='$id '";
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