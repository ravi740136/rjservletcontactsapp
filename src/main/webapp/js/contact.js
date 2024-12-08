document.getElementById("contactForm").addEventListener("submit", function(event) {
    const name = document.getElementById("name").value;
    if (name.length < 3) {
        alert("Name must be at least 3 characters long.");
        event.preventDefault(); // Prevent form submission
    }
});
