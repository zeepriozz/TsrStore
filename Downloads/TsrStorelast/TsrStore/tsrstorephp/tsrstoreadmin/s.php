<?php
// Include the database connection file
include 'connect.php';

// Initialize variables to hold the counts
$totalUsers = $con->query("SELECT COUNT(*) AS total_users FROM users")->fetch_assoc()['total_users'];
$totalShops = $con->query("SELECT COUNT(*) AS total_shops FROM shops")->fetch_assoc()['total_shops'];
$totalSales = $con->query("SELECT SUM(totalprice) AS total_sales FROM sales")->fetch_assoc()['total_sales'] ?? 0;

// Close the database connection
$con->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body { background-color: #f8f9fa; }
        .card { margin-bottom: 20px; }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2 class="my-4">Dashboard</h2>
    <div class="row">
        <div class="col-md-4">
            <div class="card bg-primary text-white">
                <div class="card-body">
                    <h5 class="card-title">Total Users</h5>
                    <p class="card-text"><?= $totalUsers ?></p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card bg-success text-white">
                <div class="card-body">
                    <h5 class="card-title">Total Shops</h5>
                    <p class="card-text"><?= $totalShops ?></p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card bg-warning text-dark">
                <div class="card-body">
                    <h5 class="card-title">Total Sales</h5>
                    <p class="card-text">$<?= number_format($totalSales, 2) ?></p>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
