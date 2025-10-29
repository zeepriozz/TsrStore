<?php
include("connection.php");

$shopname = $_POST['shopname'];
$address = $_POST['address'];
$shopphonenumber = $_POST['shopphonenumber'];
$password = $_POST['password'];

$originalImgName = $_FILES['filename']['name'];
$tempName = $_FILES['filename']['tmp_name'];
$folder = "uploads/";

if(move_uploaded_file($tempName, $folder . $originalImgName)){
    // Insert the registration data into the shopregistration table, without shopcategory
    $query = "INSERT INTO shopregistration(shopname, address, shopphonenumber, password, logo) 
              VALUES('$shopname', '$address', '$shopphonenumber', '$password', '$originalImgName')";

    if(mysqli_query($con, $query)){
        $response['status'] = "1";
        $response['message'] = "File uploaded successfully";
    } else {
        $response['status'] = "0";
        $response['message'] = "Data insertion failed";
    }
} else {
    $response['status'] = "0";
    $response['message'] = "File moving failed";
}

echo json_encode($response);
?>
