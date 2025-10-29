<?php
include ("connection.php");
$po=$_POST["shopcategory"];
// $st="A";
$stmt = $con->prepare("SELECT id,shopname,shopcategory, availableproducts, productname, price,location,description,image FROM additem where type='$po'");

   $stmt->execute();
   
    $stmt->bind_result($id,$shopname,$shopcategory,$availableproducts,$productname,$price,$location,$description,$originalImgName);
   
    $products = array();
   

    while($stmt->fetch()){
        $temp = array();
        $temp['proid'] = $id;
        $temp['productname']=$productname;
        $temp['price']=$price;
        $temp['package'] =$package;
        $temp['description'] = $description;
        $temp['image']=$image;
            $temp['shopid']=$shopid;
       
            $temp['shopname'] =$shopname;
            $temp['shopemail'] = $shopemail;
            $temp['shoplocation']=$shoplocation;
                $temp['shopphone']=$shopphone;
        array_push($products, $temp);
    }
   
     
    echo json_encode($products);
?>