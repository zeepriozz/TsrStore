<?php
include("connection.php");

// Retrieve data from POST request
$userid = $_POST['userid'];
$username = $_POST['username'];
$usernumber = $_POST['usernumber'];
$productid = $_POST['productid'];
$productname = $_POST['productname'];
$productprice = $_POST['productprice'];
$productimage = $_POST['productimage'];
$shopid = $_POST['shopid'];
$shopname = $_POST['shopname'];
$totalquantity = $_POST['totalquantity'];
$totalprice = $_POST['totalprice'];
$size = $_POST['size'];
// $totalquantity = $_POST['totalquantity'];
// $totalprice = $_POST['totalprice'];

// Corrected query: Removed extra comma and added totalprice
$q = "INSERT INTO cartproduct (userid, username, usernumber,productid, productname, productprice,productimage, shopid, shopname,size,totalquantity,totalprice) 
      VALUES ('$userid', '$username', '$usernumber', '$productid','$productname', '$productprice','$productimage', '$shopid', '$shopname','$size','$totalquantity','$totalprice')";

// Execute the query
$result = mysqli_query($con, $q);

// Check if the query was successful
if ($result) {
    $response["status"] = "1";
    $response["message"] = "item added to cart";
} else {
    $response["status"] = "0";
    $response["message"] = "item added failed: " . mysqli_error($con); // Added error detail
}

// Return response as JSON
echo json_encode($response);
?>