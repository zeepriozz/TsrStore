<?php
include("connection.php");

$shopnumber=$_POST["shopnumber"];

$stmt = $con->prepare( "SELECT id,shopid,shopname,shopnumber,shopcategory,availableproducts, productname,price,location,description,image FROM additem where  shopnumber='$shopnumber' ");

//Executting the query

$stmt->execute();

//binding result to the query

$stmt->bind_result($id,$shopid,$shopname,$shopnumber,$shopcategory,$availableproducts,$productname,$price,$location,$description,$image);

$p=array();

while($stmt->fetch()){
    $temp=array();
    $temp['id']=$id;
    $temp['shopid']=$shopid;
    $temp['shopname']=$shopname;
    $temp['shopnumber']=$shopnumber;
    $temp['shopcategory']=$shopcategory;
    $temp['availableproducts']=$availableproducts;
    $temp['productname']=$productname;
    $temp['price']=$price;
    $temp['location']=$location;
    $temp['description']=$description;
    $temp['image']=$image;
   
  
    array_push($p,$temp);

}
echo json_encode($p);
?>