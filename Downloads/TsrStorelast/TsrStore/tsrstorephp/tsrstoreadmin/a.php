<?php
// Include the database connection file
include("connect.php");

// SQL query to fetch data from the `additem` table
$query = "SELECT shopid, shopname, shopnumber, shopcategory, category, availableproducts, productname, price, package, location, description, image FROM additem";

// Execute the query
$result = mysqli_query($con, $query);

if (!$result) {
    die("Query failed: " . mysqli_error($con));
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Display Add Item Data</title>
    <!-- Bootstrap CSS for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="mb-4">Items List</h2>
    
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Shop ID</th>
                <th>Shop Name</th>
                <th>Shop Number</th>
                <th>Shop Category</th>
                <th>Category</th>
                <th>Available Products</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Package</th>
                <th>Location</th>
                <th>Description</th>
                <th>Image</th>
            </tr>
        </thead>
        <tbody>
            <?php
            // Fetch each row of the result as an associative array and display the data
            while ($row = mysqli_fetch_assoc($result)) {
                echo "<tr>";
                echo "<td>" . $row['shopid'] . "</td>";
                echo "<td>" . $row['shopname'] . "</td>";
                echo "<td>" . $row['shopnumber'] . "</td>";
                echo "<td>" . $row['shopcategory'] . "</td>";
                echo "<td>" . $row['category'] . "</td>";
                echo "<td>" . $row['availableproducts'] . "</td>";
                echo "<td>" . $row['productname'] . "</td>";
                echo "<td>" . $row['price'] . "</td>";
                echo "<td>" . $row['package'] . "</td>";
                echo "<td>" . $row['location'] . "</td>";
                echo "<td>" . $row['description'] . "</td>";
                echo "<td><img src='uploads/" . $row['image'] . "' width='100' alt='Product Image'></td>";
                echo "</tr>";
            }
            ?>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

<?php
// Free result set
mysqli_free_result($result);

// Close the connection
mysqli_close($con);
?>
