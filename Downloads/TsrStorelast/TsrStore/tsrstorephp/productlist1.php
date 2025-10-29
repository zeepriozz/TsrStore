<?php
include ("connection.php");
$po=$_POST["availableproducts"];
// $st="A";
$stmt = $con->prepare("SELECT id,shopname,shopnumber,shopcategory,availableproducts,productname,price,location,description,image FROM additem where availableproducts='$po'");

   $stmt->execute();
   
    $stmt->bind_result($id,$shopname,$shopnumber,$shopcategory,$availableproducts,$productname,$price,$location,$description,$image);
   
    $products = array();
   

    while($stmt->fetch()){
        $temp = array();
        $temp['id'] = $id;
        $temp['shopname']=$shopname;
        $temp['shopnumber']=$shopnumber;
        $temp['shopcategory']=$shopcategory;
        $temp['availableproducts'] =$availableproducts;
        $temp['productname'] = $productname;
        $temp['price']=$price;
        $temp['location']=$location;
        $temp['description'] =$description;
        $temp['image'] = $image;
           
        array_push($products, $temp);
    }
   
     
    echo json_encode($products);
?>