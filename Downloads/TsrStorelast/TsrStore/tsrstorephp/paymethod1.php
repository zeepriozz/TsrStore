<?php
include("connection.php");

// Retrieve data from POST request
$username = $_POST["username"];
$userid = $_POST["userid"];
$payment = $_POST["payment"];
$totalprice = $_POST["totalprice"];
$date = $_POST["date"];
 // Field to identify the specific product being updated

// Prepare the query to update payment method and date for the specific product order
$query = "UPDATE productorder 
          SET payment='$payment', totalprice='$totalprice', date='$date' 
          WHERE username='$username' && userid='$userid' ";

// Execute the query
$result = mysqli_query($con, $query);

// Prepare the response
if ($result) {
    $response["status"] = "1";
    $response["message"] = "Payment method and date updated successfully.";
} else {
    $response["status"] = "0";
    $response["message"] = "Failed to update payment method and date: " . mysqli_error($con);
}

// Return the response as JSON
echo json_encode($response);
?>
