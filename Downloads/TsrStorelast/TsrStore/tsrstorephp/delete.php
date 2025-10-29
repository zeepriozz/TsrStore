<?php
include("connection.php");
$id = $_POST['id'];

/*$password = "ANU12";*/
$query="Delete from additem where id='$id'";
$result = mysqli_query($con, $query);
if($result){
$response["status"]="1";
$response["message"]="Removed";
}
else{
$response["status"]="0";
$response["message"]="failed";
}
echo json_encode($response);
?>