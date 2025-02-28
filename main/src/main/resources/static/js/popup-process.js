document.addEventListener('DOMContentLoaded', function() {
    // Получаем элементы DOM
    const openPopupButton = document.getElementById('openPopupButton');
    const popupContainer = document.getElementById('popupContainer');
    const closePopupButton = document.getElementById('closePopupButton');
    const addItemButton = document.getElementById('addItemButton');
    const itemInput = document.getElementById('itemInput');
    const itemList = document.getElementById('itemList');
    const form = document.querySelector('form'); // Получаем форму

    // Массив для хранения критериев
    let items = [];

    // Изначальная отрисовка элементов
    renderItems();

    // Обработчики событий для открытия и закрытия всплывающего окна
    openPopupButton.addEventListener('click', function() {
        popupContainer.style.display = 'flex';
    });

    closePopupButton.addEventListener('click', function() {
        popupContainer.style.display = 'none';
    });

    popupContainer.addEventListener('click', function(event) {
        if (event.target === popupContainer) {
            popupContainer.style.display = 'none';
        }
    });