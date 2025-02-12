$(document).ready(function () {
    // Get Current Year
    function getCurrentYear() {
        const yearElement = document.querySelector("#displayDateYear");
        if (yearElement) {
            yearElement.innerText = new Date().getFullYear();
        } else {
            console.error("Element with id 'displayDateYear' not found.");
        }
    }
    getCurrentYear();


    // Initialize Owl Carousel
    $(".owl-carousel").owlCarousel({
        loop: true,
        margin: 10,
        nav: true,
        dots: false,
        navText: [
            '<i class="fas fa-chevron-left"></i>',
            '<i class="fas fa-chevron-right"></i>'
        ]
        ,
        autoplay: true,
        autoplayHoverPause: true,
        responsive: {
            0: {
                items: 1
            },
            768: {
                items: 2
            },
            1000: {
                items: 2
            }
        }
    });
});


/** google_map js **/

function myMap() {
    const mapProp = {
        center: new google.maps.LatLng(40.712775, -74.005973),
        zoom: 18,
    };
    const map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
}