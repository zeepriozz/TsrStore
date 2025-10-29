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
$size = $_POST['size'];
$totalquantity = $_POST['totalquantity'];
$totalprice = $_POST['totalprice'];

// Insert the order details into the productorder table
$q = "INSERT INTO productorder (userid, username, usernumber, productid, productname, productprice, productimage, shopid, shopname, size, totalquantity, totalprice) 
      VALUES ('$userid', '$username', '$usernumber', '$productid', '$productname', '$productprice', '$productimage', '$shopid', '$shopname', '$size', '$totalquantity', '$totalprice')";

$result = mysqli_query($con, $q);

// Check if the query was successful
if ($result) {
    // Get the last inserted ID from the productorder table
    $inserted_id = mysqli_insert_id($con);

    // Update the available products in the additem table
    $updateQuery = "UPDATE additem SET package = package - '$totalquantity' WHERE shopid = '$shopid' AND productname = '$productname'";
    
    if (mysqli_query($con, $updateQuery)) {
        $response["status"] = "1";
        $response["message"] = "Purchase successful and stock updated";
        $response["id"] = $inserted_id; // Return the inserted order ID

    } else {
        $response["status"] = "0";
        $response["message"] = "Purchase successful but stock update failed: " . mysqli_error($con);
        $response["id"] = $inserted_id; // Return the order ID even if the stock update fails
    }
} else {
    $response["status"] = "0";
    $response["message"] = "Purchase failed: " . mysqli_error($con);
    $response["id"] = ""; // No order ID since the insert failed
}

// Return response as JSON
echo json_encode($response);
?>
