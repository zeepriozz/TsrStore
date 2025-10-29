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
            <th style="padding: 12px; border: 1px solid #dee2e6;">User ID</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Username</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">User Number</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Product ID</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Product Name</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Product Price</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Product Image</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Shop ID</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Shop Name</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Total Quantity</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Total Price</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Payment</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Date</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Action</th> <!-- Added Action Column for Delete Button -->
        </tr>
    </thead>
    <tbody>
        <?php
        include 'connect.php';
        // SQL query to select records from the productorder table
        $sql = "SELECT id, userid, username, usernumber, productid, productname, productprice, productimage, shopid, shopname, totalquantity, totalprice, payment, date FROM productorder";
        $result = $con->query($sql);

        // Check if there are results
        if ($result->num_rows > 0) {
            // Output data of each row
            while($row = $result->fetch_assoc()) {
                echo "<tr style='border: 1px solid #dee2e6;'>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['id'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['userid'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['username'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['usernumber'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['productid'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['productname'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['productprice'] . "</td>"; 
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'><img src='uploads/" . $row['productimage'] . "' width='100' alt='Product Image'></td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['shopid'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['shopname'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['totalquantity'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['totalprice']. "</td>"; 
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['payment'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . date('Y-m-d', strtotime($row['date'])) . "</td>"; // Formatting date
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>";
                echo "<form action='dele.php' method='POST'>";
                echo "<input type='hidden' name='id' value='" . $row['id'] . "'>";
                echo "<button type='submit' class='btn btn-danger btn-sm'>Delete</button>";
                echo "</form>";
                echo "</td>";
                echo "</tr>";
            }
        } else {
            echo "<tr><td colspan='15' style='text-align:center; padding: 10px;'>No orders found</td></tr>";
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
