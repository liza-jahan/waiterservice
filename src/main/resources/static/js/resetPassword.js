// Function to send the reset code (dummy function)
function sendResetCode() {
    const phoneNumber = document.getElementById('phoneNumber').value;

    if (phoneNumber) {
        alert('Reset code has been sent to ' + phoneNumber);
        document.getElementById('verifySection').style.display = 'block'; // Show the verification section
    } else {
        alert('Please enter a valid phone number.');
    }
}

// Function to reset the password (dummy function)
function resetPassword() {
    const resetCode = document.getElementById('resetCode').value;
    const newPassword = document.getElementById('newPassword').value;

    if (resetCode && newPassword) {
        alert('Password has been reset successfully!');
        window.location.href = 'login.html'; // Redirect to the login page
    } else {
        alert('Please enter both reset code and new password.');
    }
}

// Function to go back to login page
function goBackToLogin() {
    window.location.href = 'login.html'; // Redirect back to the login page
}