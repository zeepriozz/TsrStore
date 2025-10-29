<?php
include("connection.php");

$username = $_POST["username"];
$userid = $_POST["userid"];
$payment = $_POST["payment"];
// $totalprice = $_POST["totalprice"];
$date = $_POST["date"];

// Ensure that the query updates the record based on unique identifiers
$query = "UPDATE productorder SET payment='$payment', date='$date' WHERE username='$username' AND userid='$userid'";

// Execute the query
$result = mysqli_query($con, $query);
$response = array();

if ($result) {
    $response["status"] = "1";
    $response["message"] = "Order updated successfully";
} else {
    $response["status"] = "0";
    $response["message"] = "Failed to update order: " . mysqli_error($con);
}

// Return JSON response
echo json_encode($response);
?>
