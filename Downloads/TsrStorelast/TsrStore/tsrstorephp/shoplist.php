<?php
include ("connection.php");
$po=$_POST["shopcategory"];
// $st="A";
$stmt = $con->prepare("SELECT id,shopname,shopcategory, availableproducts, productname, price,location,description,image FROM additem where type='$po'");

   $stmt->execute();
   
    $stmt->bind_result($shopname,$shopcategory,$availableproducts,$productname,$price,$location,$description,$image)";

   
    $products = array();
   

    while($stmt->fetch()){
        $temp = array();
        $temp['id'] = $id;
        $temp['productname']=$productname;
        $temp['price']=$price;
        $temp['description'] = $description;
        $temp['image']=$image;
        $temp['shopid']=$shopid;
       
        $temp['shopname'] =$shopname;

        $temp['shoplocation']=$shoplocation;
      
        array_push($products, $temp);
    }
   
     
    echo json_encode($products);
?>