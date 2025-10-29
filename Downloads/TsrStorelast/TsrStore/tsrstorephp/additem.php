<?php
include("connection.php");

// Collect POST data
$shopid = $_POST['shopid'];
$shopname = $_POST['shopname'];
$shopnumber = $_POST['shopnumber'];
$shopcategory = $_POST['shopcategory'];
$category = $_POST['category'];
$availableproducts = $_POST['availableproducts'];
$productname = $_POST['productname'];
$price = $_POST['price'];
$package = $_POST['package'];
$location = $_POST['location'];
$description = $_POST['description'];

// Image upload handling
$originalImgName = $_FILES['filename']['name'];
$tempName = $_FILES['filename']['tmp_name'];
$folder = "uploads/";

if (move_uploaded_file($tempName, $folder . $originalImgName)) {
    // SQL query with proper variable embedding
    $query = "INSERT INTO additem (shopid, shopname, shopnumber, shopcategory,category, availableproducts, productname, price,package, location, description, image) 
              VALUES ('$shopid', '$shopname', '$shopnumber', '$shopcategory','$category', '$availableproducts', '$productname', '$price','$package', '$location', '$description', '$originalImgName')";

    // Execute the query and handle the response
    if (mysqli_query($con, $query)) {
        $response['status'] = "1";
        $response['message'] = "File uploaded successfully";  
    } else {
        $response['status'] = "0";
        $response['message'] = "Data insertion failed: " . mysqli_error($con);
    }
} else {
    $response['status'] = "0";
    $response['message'] = "File moving failed";
}

echo json_encode($response);
?>
