<?php
include("connection.php");

// Retrieve data from POST request
$userid = $_POST['userid'];
$username = $_POST['username'];
$usernumber = $_POST['usernumber'];
$productid = $_POST['productid'];
$productname = $_POST['productname'];
$price = $_POST['price'];
$package = $_POST['package'];
$description = $_POST['description'];
$image = $_POST['image'];
$shopid = $_POST['shopid'];
$shopname = $_POST['shopname'];
// $totalquantity = $_POST['totalquantity'];
// $totalprice = $_POST['totalprice'];

// Corrected query: Removed extra comma and added totalprice
$q = "INSERT INTO wishlist (userid, username, usernumber, productid,productname, price,package,description,image, shopid, shopname) 
      VALUES ('$userid', '$username', '$usernumber', '$productid','$productname', '$price','$package','$description','$image', '$shopid', '$shopname')";

// Execute the query
$result = mysqli_query($con, $q);

// Check if the query was successful
if ($result) {
    $response["status"] = "1";
    $response["message"] = "item added to wishlist";
} else {
    $response["status"] = "0";
    $response["message"] = "item added failed: " . mysqli_error($con); // Added error detail
}

// Return response as JSON
echo json_encode($response);
?>