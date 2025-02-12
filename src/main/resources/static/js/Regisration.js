
function submitRegistration() {
    const fullName = document.getElementById("fullName").value;
    const dob = document.getElementById("dob").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const mobile = document.getElementById("mobile").value;
    const gender = document.getElementById("gender").value;
    const occupation = document.getElementById("occupation").value;
    const nid = document.getElementById("nid").value;
    const bkash = document.getElementById("bkash").value;
    const state = document.getElementById("state").value;

    // Save data to localStorage
    const registrationData = {
        fullName, dob, email, password, mobile, gender, occupation, nid, bkash, state
    };
    localStorage.setItem('registrationData', JSON.stringify(registrationData));


}