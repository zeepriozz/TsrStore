<?php

include("connection.php");

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Set response header to JSON
    header('Content-Type: application/json');
    
    // Read input data from request
    $input = json_decode(file_get_contents('php://input'), true);

    // Check if OTP and mobile number are provided
    if (isset($input['otp']) && !empty($input['otp']) && isset($input['mobile']) && !empty($input['mobile'])) {
        $receivedOtp = $input['otp'];
        $mobile = $input['mobile'];

        // Retrieve OTP from the database
        $stmt = $con->prepare("SELECT otp FROM otps WHERE mobile_number = ? ORDER BY created_at DESC LIMIT 1");
        $stmt->bind_param("s", $mobile);
        $stmt->execute();
        $stmt->bind_result($storedOtp);
        $stmt->fetch();

        // Validate the OTP
        if ($storedOtp && $receivedOtp == $storedOtp) {
            echo json_encode(['success' => true, 'message' => 'OTP verified successfully']);
            
            // Delete OTP after successful verification
            $stmt = $con->prepare("DELETE FROM otps WHERE mobile_number = ?");
            $stmt->bind_param("s", $mobile);
            $stmt->execute();
        } else {
            echo json_encode(['success' => false, 'message' => 'Invalid OTP']);
        }

        $stmt->close();
    } else {
        echo json_encode(['success' => false, 'message' => 'OTP and mobile number are required']);
    }
} else {
    echo json_encode(['success' => false, 'message' => 'Invalid request method']);
}

// Close the connection
$con->close();
?>
