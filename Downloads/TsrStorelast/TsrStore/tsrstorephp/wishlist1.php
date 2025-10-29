<?php
include ("connection.php");

// Prepare the SQL statement
$stmt = $con->prepare("SELECT id,userid, username, usernumber, productid,productname, price,package,description,image, shopid, shopname FROM wishlist");

// Check if the preparation was successful
if ($stmt === false) {
    die("Error preparing statement: " . $con->error);
}

// Execute the statement
$stmt->execute();

// Bind the result variables
$stmt->bind_result($id,$userid, $username, $usernumber, $productid,$productname, $price, $package,$description,$image, $shopid, $shopname);

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
    $temp['price'] = $price;
    $temp['package'] = $package;
    $temp['description'] = $description;
    $temp['image'] = $image;
    $temp['shopid'] = $shopid;
    $temp['shopname'] = $shopname;

    array_push($products, $temp);
}

// Close the statement
$stmt->close();

// Return the products as a JSON response
echo json_encode($products);
?>
