// Function to enable the edit form fields
function enableEditFields() {
    document.getElementById('editButton').style.display = 'none';
    document.getElementById('actionButtons').style.display = 'block';

    // Show the input fields
    document.querySelectorAll('.edit-form').forEach(function(form) {
        form.style.display = 'block';
    });

    // Hide the profile details (text)
    document.querySelectorAll('.profile-info-item span').forEach(function(span) {
        span.style.display = 'none';
    });
}

// Function to submit all changes
function submitAllChanges() {
    // Collect the new data from input fields
    document.getElementById('profileName').innerText = document.getElementById('editName').value;
    document.getElementById('profileDob').innerText = document.getElementById('editDob').value;
    document.getElementById('profileEmail').innerText = document.getElementById('editEmail').value;
    document.getElementById('profilePhone').innerText = document.getElementById('editPhone').value;
    document.getElementById('profileGender').innerText = document.getElementById('editGender').value;
    document.getElementById('profileOccupation').innerText = document.getElementById('editOccupation').value;
    document.getElementById('profileAddress').innerText = document.getElementById('editAddress').value;
    document.getElementById('profileBkash').innerText = document.getElementById('editBkash').value;

    // Hide input fields and show text
    document.querySelectorAll('.edit-form').forEach(function(form) {
        form.style.display = 'none';
    });
    document.querySelectorAll('.profile-info-item span').forEach(function(span) {
        span.style.display = 'block';
    });

    document.getElementById('editButton').style.display = 'block';
    document.getElementById('actionButtons').style.display = 'none';
}

// Function to cancel edit
function cancelEdit() {
    // Hide input fields and show text
    document.querySelectorAll('.edit-form').forEach(function(form) {
        form.style.display = 'none';
    });
    document.querySelectorAll('.profile-info-item span').forEach(function(span) {
        span.style.display = 'block';
    });

    document.getElementById('editButton').style.display = 'block';
    document.getElementById('actionButtons').style.display = 'none';
}
function updateProfileImage() {
    const input = document.getElementById('imageInput');
    const image = document.getElementById('profileImage');

    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function(e) {
            image.src = e.target.result;
        };

        reader.readAsDataURL(input.files[0]);
    }
}