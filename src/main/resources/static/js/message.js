document.addEventListener("DOMContentLoaded", function() {
    const messageContainers = document.getElementsByClassName("hidden-message");
    function showMessage() {
        for (let i = 0; i < messageContainers.length; i++) {
            messageContainers[i].style.display = "block"; // Show each message container
        }
        setTimeout(function() {
            for (let i = 0; i < messageContainers.length; i++) {
                messageContainers[i].style.display = "none"; // Hide each message container after 5 seconds
            }
        }, 5000); // 5000 milliseconds (5 seconds)
    }
    // Call the showMessage function
    showMessage();
});
