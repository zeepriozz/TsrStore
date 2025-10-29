<?php
include("connection.php");  // Include your database connection file

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

// Prepare the SQL query to insert order details into the 'productorder' table
$q = "INSERT INTO productorder (userid, username, usernumber, productid, productname, productprice, productimage, shopid, shopname, size, totalquantity, totalprice) 
      VALUES ('$userid', '$username', '$usernumber', '$productid', '$productname', '$productprice', '$productimage', '$shopid', '$shopname', '$size', '$totalquantity', '$totalprice')";

// Execute the query and check if it was successful
$result = mysqli_query($con, $q);

if ($result) {
    // Get the last inserted order ID
    $inserted_id = mysqli_insert_id($con);

    // Prepare and execute the update query for the 'additem' table to update product stock
    $updateQuery = "UPDATE additem SET package = package - '$totalquantity' WHERE shopid = '$shopid' AND productname = '$productname'";
    $updateResult = mysqli_query($con, $updateQuery);

    if ($updateResult) {
        // If both queries were successful, return a success response
        $response["status"] = "1";
        $response["message"] = "Purchase successful and stock updated";
        $response["id"] = $inserted_id;  // Return the inserted order ID
    } else {
        // If the order was successful but stock update failed
        $response["status"] = "0";
        $response["message"] = "Purchase successful but stock update failed: " . mysqli_error($con);
        $response["id"] = $inserted_id;  // Return the order ID even if stock update failed
    }
} else {
    // If the order insert failed
    $response["status"] = "0";
    $response["message"] = "Purchase failed: " . mysqli_error($con);
    $response["id"] = "";  // No order ID since the insert failed
}

// Return the response as JSON
echo json_encode($response);
?>
