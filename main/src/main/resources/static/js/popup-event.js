const openPopupButton = document.getElementById('openPopupButton');
const closePopupButton = document.getElementById('closePopupButton');
const popupContainer = document.getElementById('popupContainer');
const itemInput = document.getElementById('itemInput');
const addItemButton = document.getElementById('addItemButton');
const itemList = document.getElementById('itemList');

// Get items from localStorage or initialize an empty array
let items = JSON.parse(localStorage.getItem('popupItems')) || [];

// Initially render items on page load
renderItems();

openPopupButton.addEventListener('click', function() {
    popupContainer.style.display = 'flex';
});

closePopupButton.addEventListener('click', function() {
    popupContainer.style.display = 'none';
});


popupContainer.addEventListener('click', function(event) {
    if(event.target === popupContainer){
        popupContainer.style.display = 'none';
    }
});


addItemButton.addEventListener('click', function() {
    const newItem = itemInput.value.trim();
    if (newItem) {
        items.push({ initial: newItem, from: '', to: '' }); // Initial structure
        saveItems();
        renderItems();
        itemInput.value = '';
    }
});

function renderItems() {
     itemList.innerHTML = '';
     items.forEach((item, index) => {
         const li = document.createElement('li');
         const initialText = document.createElement('span');
         initialText.textContent = item.initial + " ";
         li.appendChild(initialText);

         const fromLabel = document.createElement('label');
         fromLabel.textContent = 'от: ';
         const fromInput = document.createElement('input');
         fromInput.type = 'text';
         fromInput.value = item.from;
         fromInput.addEventListener('blur', () => {
             items[index].from = fromInput.value;
             saveItems();
         });
         li.appendChild(fromLabel);
         li.appendChild(fromInput);

         const toLabel = document.createElement('label');
         toLabel.textContent = 'до: ';
         const toInput = document.createElement('input');
         toInput.type = 'text';
         toInput.value = item.to;
         toInput.addEventListener('blur', () => {
             items[index].to = toInput.value;
             saveItems();
         });
         li.appendChild(toLabel);
         li.appendChild(toInput);

         const deleteButton = document.createElement('button');
         deleteButton.textContent = 'X';
         deleteButton.addEventListener('click', () => {
             items.splice(index, 1); // Remove item from array
             saveItems();
             renderItems(); // Re-render the list
         });
         li.appendChild(deleteButton);

         itemList.appendChild(li);
     });
 }

function saveItems() {
    localStorage.setItem('popupItems', JSON.stringify(items));
}