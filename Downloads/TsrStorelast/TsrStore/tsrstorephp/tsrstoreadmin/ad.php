<?php
// Include the database connection file
include 'b.php';



?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <!-- User Login Information Table -->
    <table class="table" style="width: 95%; margin: 20px auto; border-collapse: collapse;">
    <thead>
        <tr style="background-color: #007bff; color: white;">
            <th style="padding: 12px; border: 1px solid #dee2e6;">ID</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Shop ID</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Shop Name</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Shop Number</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Shop Category</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Category</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Available Products</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Product Name</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Price</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Package</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Location</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Description</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Image</th>
        </tr>
    </thead>
    <tbody>
        <?php
        include 'connect.php';
        // SQL query to select data from additem
        $sql = "SELECT id, shopid, shopname, shopnumber, shopcategory, category, availableproducts, productname, price, package, location, description, image FROM additem";
        $result = $con->query($sql);

        // Check if there are results
        if ($result->num_rows > 0) {
            // Output data of each row
            while($row = $result->fetch_assoc()) {
                echo "<tr style='border: 1px solid #dee2e6;'>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['id'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['shopid'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['shopname'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['shopnumber'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['shopcategory'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['category'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['availableproducts'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['productname'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['price'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['package'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['location'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['description'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'><img src='uploads/" . $row['image'] . "' width='100' alt='Product Image'></td>";
                echo "</tr>";
            }
        } else {
            echo "<tr><td colspan='13' style='text-align:center; padding: 10px;'>No items found</td></tr>";
        }

        // Close the connection
        $con->close();
        ?>
    </tbody>
</table>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
