<?php
// Include the database connection file
include 'connect.php';




?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" />


    <!-- Internal CSS -->
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
        }
        .sidebar {
            height: 100vh;
            width: 250px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #343a40;
            color: white;
            padding-top: 20px;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 15px;
            text-align: left;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .main-content {
            margin-left: 250px;
            padding: 20px;
            background-color: #f8f9fa;
            height: 100vh;
        }
        .top-nav {
            background-color: #6c757d;
            color: white;
            padding: 10px;
            text-align: right;
        }
       
        .table thead {
            background-color: #6c757d;
            color: white;
        }
    </style>
</head>
<body>
<div class="sidebar">
    <h4 class="text-center">
        <a href="index.php" class="text-dark text-decoration-none">
            <i class="fas fa-tachometer-alt"></i> Admin Dashboard
        </a>
    </h4>
    <a href="ts.php"><i class="fas fa-store"></i> Shops</a>
    <a href="ad.php"><i class="fas fa-box"></i> Products</a>
    <a href="of.php"><i class="fas fa-tags"></i> Offers</a>
    <a href="td.php"><i class="fas fa-users"></i> Users</a>
    <a href="od.php"><i class="fas fa-shopping-cart"></i> Orders</a>
    <div class="text-center mt-4">

    </div>
</div>


    <!-- Main Content -->
    <div class="main-content">
        <!-- Top Navigation -->
        <div class="top-nav">
            Welcome, Admin
        </div>

 
  

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    
</body>
</html>
