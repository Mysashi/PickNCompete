document.addEventListener('DOMContentLoaded', function() {
    // Get all collapsibles
    const collapsibles = document.querySelectorAll(".collapsible");

    collapsibles.forEach(collapsible => {
        const header = collapsible.querySelector(".collapsible-header");
        const content = collapsible.querySelector(".collapsible-content");
        const addItemButton = collapsible.querySelector(".add-item-button");
        const itemTemplate = document.getElementById("item-template");

        // Add click event to the header to toggle the content
        header.addEventListener("click", () => {
               content.classList.toggle("active");

        });

        // Add click event to the "Add Item" button

    });
});