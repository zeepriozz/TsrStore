<?php
// Include the database connection file
include 'connect.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Get the ID from the POST request
    $id = $_POST['id'];

    // SQL query to delete the record with the specified ID
    $sql = "DELETE FROM productorder WHERE id = $id";

    // Execute the query
    if (mysqli_query($con, $sql)) {
        // Redirect back to the page with the table after successful deletion and add a success parameter
        echo "<script>alert('Deleted successfully');window.location.replace('index.php');</script>";
        exit();
    } else {
        echo "Error deleting record: " . mysqli_error($con);
    }
}

// Close the connection
mysqli_close($con);
?>
