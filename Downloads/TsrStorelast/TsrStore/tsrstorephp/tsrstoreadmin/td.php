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
            <th style="padding: 12px; border: 1px solid #dee2e6;">Username</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Email</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Phone</th>
            <th style="padding: 12px; border: 1px solid #dee2e6;">Action</th>
        </tr>
    </thead>
    <tbody>
        <?php
        include 'connect.php';
        // SQL query to select id, username, email, and phone from userlogin
        $sql = "SELECT id, username, email, phone FROM userlogin";
        $result = $con->query($sql);

        // Check if there are results
        if ($result->num_rows > 0) {
            // Output data of each row
            while($row = $result->fetch_assoc()) {
                echo "<tr style='border: 1px solid #dee2e6;'>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['id'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['username'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['email'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>" . $row['phone'] . "</td>";
                echo "<td style='padding: 10px; border: 1px solid #dee2e6;'>";
                echo "<form action='shopdele.php' method='POST'>";
                echo "<input type='hidden' name='id' value='" . $row['id'] . "'>";
                echo "<button type='submit' class='btn btn-danger btn-sm'>Delete</button>";
                echo "</form>";
                echo "</td>";
                echo "</tr>";
            }
        } else {
            echo "<tr><td colspan='5' style='text-align:center; padding: 10px;'>No users found</td></tr>";
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
