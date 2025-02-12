//
//
//
//
//
// // function validateLogin(event) {
// //     event.preventDefault();
// //     const email = document.getElementById("loginEmail").value;
// //     const password = document.getElementById("loginPassword").value;
// //
// //     if (email === "your@example.com" && password === "yourPassword") {
// //         alert("Login Successful!");
// //     } else {
// //         alert("Invalid login credentials!");
// //     }
// // }
//
//
// const loginform = {
//
//     init: function () {
//         this.bindUserBox();
//     },
//
//     bindUserBox: function () {
//         let result = {};
//
//         $(".form").delegate("input[name='un']", 'blur', function () {
//             const $self = $(this);
//
//             // this grep would be replaced by $.post tp check db for user
//             result = $.grep(users, function (elem, i) {
//                 return (elem.name == $self.val());
//             });
//
//             // This would be callback
//             if (result.length === 1) {
//                 if ($("div.login-wrap").hasClass('register')) {
//                     loginform.revertForm();
//                     return;
//                 } else {
//                     return;
//                 }
//             }
//
//             if (!$("div.login-wrap").hasClass('register')) {
//                 if ($("input[name='un']").val().length > 4)
//                     loginform.switchForm();
//             }
//
//         });
//     },
//     switchForm: function () {
//         var $html = $("div.login-wrap").addClass('register');
//         $html.children('h2').html('Register');
//         $html.find(".form input[name='pw']").after("<input type='email' placeholder='Re-type password' name='rpw' />");
//         $html.find('button').html('Sign up');
//         $html.find('a p').html('Have an account? Sign in');
//     },
//     revertForm: function () {
//         var $html = $("div.login-wrap").removeClass('register');
//         $html.children('h2').html('Login');
//         $html.find(".form input[name='rpw']").remove();
//         $html.find('button').html('Sign in');
//         $html.find('a p').html("Don't have an account? Register");
//     },
//     submitForm: function () {
//         // ajax to handle register or login
//     }
//
// }; // loginform {}
//
//
// // Init login form
// loginform.init();
//
//
// // vertical align box
// (function(elem){
//     elem.css("margin-top", Math.floor( ( $(window).height() / 2 ) - ( elem.height() / 2 ) ) );
// }($(".login-wrap")));
//
// $(window).resize(function(){
//     $(".login-wrap").css("margin-top", Math.floor( ( $(window).height() / 2 ) - ( $(".login-wrap").height() / 2 ) ) );
//
// });
//













// Function to validate login credentials
// function validateLogin(event) {
//     event.preventDefault();
//
//     const email = document.querySelector('input[name="un"]').value;
//     const password = document.querySelector('input[name="pw"]').value;
//
//
//     // Validate if phone number and password match
//
// }

// Function to show popup message
function showPopup(message) {
    document.getElementById("popupMessage").innerText = message;
    document.getElementById("popup").style.display = "flex";
}

// Function to close popup
function closePopup() {
    document.getElementById("popup").style.display = "none";
}






