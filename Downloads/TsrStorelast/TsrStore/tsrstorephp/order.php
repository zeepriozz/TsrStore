<?php
include ("connection.php");

// Prepare the SQL statement
$stmt = $con->prepare("SELECT id,userid,username, usernumber,productid, productname, productprice, productimage, shopid, shopname,totalquantity,totalprice, payment ,date FROM productorder");

// Check if the preparation was successful
if ($stmt === false) {
    die("Error preparing statement: " . $con->error);
}

// Execute the statement
$stmt->execute();

// Bind the result variables
$stmt->bind_result($id,$userid, $username, $usernumber,$productid,$productname, $productprice, $productimage, $shopid, $shopname,$totalquantity,$totalprice,$payment,$date);

$products = array();

// Fetch the results into an array
while ($stmt->fetch()) {
    $temp = array();
    $temp['id'] = $id;
    $temp['userid'] = $userid;
    $temp['username'] = $username;
    $temp['usernumber'] = $usernumber;
    $temp['productid'] = $productid;
    $temp['productname'] = $productname;
    $temp['productprice'] = $productprice;
    $temp['productimage'] = $productimage;
    $temp['shopid'] = $shopid;
    $temp['shopname'] = $shopname;
    $temp['totalquantity'] = $totalquantity;
    $temp['totalprice'] = $totalprice;
    $temp['payment'] = $payment;
    $temp['date'] = $date;

    array_push($products, $temp);
}

// Close the statement
$stmt->close();

// Return the products as a JSON response
echo json_encode($products);
?>
