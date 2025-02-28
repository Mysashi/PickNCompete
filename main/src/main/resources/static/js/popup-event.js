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

    // Обработчик добавления элемента
    addItemButton.addEventListener('click', function() {
        const newItem = itemInput.value.trim();
        if (newItem) {
            const item = { initial: newItem, from: '', to: '' };
            items.push(item);
            addHiddenFields(item, items.length - 1); // Добавляем скрытые поля сразу
            renderItems();
            itemInput.value = '';
        }
    });

    // Функция отрисовки элементов списка
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
                updateHiddenFields(item, index); // Обновляем скрытые поля при изменении
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
                updateHiddenFields(item, index); // Обновляем скрытые поля при изменении
            });
            li.appendChild(toLabel);
            li.appendChild(toInput);

            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'X';
            deleteButton.addEventListener('click', () => {
                removeHiddenFields(index); //Удаляем скрытые поля при удалении элемента
                items.splice(index, 1);
                renderItems();

                // После удаления нужно переиндексировать имена всех скрытых полей
                reindexHiddenFields();
            });
            li.appendChild(deleteButton);

            itemList.appendChild(li);
        });
    }

    // Функция добавления скрытых полей
    function addHiddenFields(item, index) {
        const hiddenInitial = document.createElement('input');
        hiddenInitial.type = 'hidden';
        hiddenInitial.name = `criteria[${index}][initial]`;
        hiddenInitial.value = item.initial;
        form.appendChild(hiddenInitial);

        const hiddenFrom = document.createElement('input');
        hiddenFrom.type = 'hidden';
        hiddenFrom.name = `criteria[${index}][from]`;
        hiddenFrom.value = item.from;
        form.appendChild(hiddenFrom);

        const hiddenTo = document.createElement('input');
        hiddenTo.type = 'hidden';
        hiddenTo.name = `criteria[${index}][to]`;
        hiddenTo.value = item.to;
        form.appendChild(hiddenTo);
    }

    // Функция обновления скрытых полей
    function updateHiddenFields(item, index) {
        const hiddenInitial = form.querySelector(`input[name="criteria[${index}][initial]"]`);
        hiddenInitial.value = item.initial;

        const hiddenFrom = form.querySelector(`input[name="criteria[${index}][from]"]`);
        hiddenFrom.value = item.from;

        const hiddenTo = form.querySelector(`input[name="criteria[${index}][to]"]`);
        hiddenTo.value = item.to;
    }
        // Функция удаления скрытых полей
    function removeHiddenFields(index) {
        const hiddenInitial = form.querySelector(`input[name="criteria[${index}][initial]"]`);
        hiddenInitial.remove();

        const hiddenFrom = form.querySelector(`input[name="criteria[${index}][from]"]`);
        hiddenFrom.remove();

        const hiddenTo = form.querySelector(`input[name="criteria[${index}][to]"]`);
        hiddenTo.remove();
    }

     // Функция для переиндексации скрытых полей
    function reindexHiddenFields() {
        const hiddenFields = form.querySelectorAll('input[name^="criteria"]'); // Находим все скрытые поля criteria
        const itemArray = [];
        hiddenFields.forEach((field) => {
          const nameParts = field.name.match(/criteria\[(\d+)\]\[(.*?)\]/);
          if (nameParts) {
            const index = parseInt(nameParts[1]);
            const property = nameParts[2];
            const value = field.value;
            if (!itemArray[index]) {
              itemArray[index] = {};
            }
            itemArray[index][property] = value;
          }
        });
          let newIndex = 0;
            items.forEach((item) => {
                const hiddenInitial = form.querySelector(`input[name="criteria[${items.indexOf(item)}][initial]"]`);
                hiddenInitial.name = `criteria[${newIndex}][initial]`;

                const hiddenFrom = form.querySelector(`input[name="criteria[${items.indexOf(item)}][from]"]`);
                 hiddenFrom.name = `criteria[${newIndex}][from]`;

                const hiddenTo = form.querySelector(`input[name="criteria[${items.indexOf(item)}][to]"]`);
                 hiddenTo.name = `criteria[${newIndex}][to]`;
                 newIndex++;

            });
    }
});