function hideErrorMessages() {
    let errorMessages = document.getElementsByClassName('error-message');
    Array.prototype.forEach.call(errorMessages, function(element) {
        element.style.display = 'none';
    });
}

document.addEventListener("DOMContentLoaded", function() {
    setTimeout(hideErrorMessages, 3000);
});