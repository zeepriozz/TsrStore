<?php
include("connection.php");

$shopphonenumber=$_POST['shopphonenumber'];


$originalImgName=$_FILES['filename']['name'];
$tempName=$_FILES['filename']['tmp_name'];
$folder="uploads/";

if(move_uploaded_file($tempName,$folder.$originalImgName)){
    $query="Update shopregistration set logo='$originalImgName' where shopphonenumber='$shopphonenumber'";
     if(mysqli_query($con,$query)){
   $response['status']="1";
   $response['message']="file uploaded successfully";  

}
else{
    $response['status']="0";
    $response['message']="Data insertion failed";
}
}
else{
    $response['status']="0";
    $response['message']="File moving failed";
}
echo json_encode($response);
?>