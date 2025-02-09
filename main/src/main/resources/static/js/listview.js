 const collapsible = document.querySelector(".collapsible");
    const header = collapsible.querySelector(".collapsible-header");
    const content = collapsible.querySelector(".collapsible-content");
    const addItemButton = collapsible.querySelector(".add-item-button");
    const itemTemplate = document.getElementById("item-template");

    header.addEventListener("click", () => {
        content.classList.toggle("active");
    });

    addItemButton.addEventListener("click", () => {
      // get template content
        const newItemNode = itemTemplate.content.cloneNode(true);
        const newElement = newItemNode.querySelector('.collapsible-item');
        newElement.querySelector('span').textContent = `Criteria ${content.children.length + 1}`; // Example: "Item 1", "Item 2", etc.
        content.appendChild(newItemNode);
    });