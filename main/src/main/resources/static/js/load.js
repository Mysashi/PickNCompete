$(document).ready(function() {
  //  Находим форму (укажите селектор вашей формы)
  const form = $('#form-event'); // Замените #yourFormId на фактический ID вашей формы

  // Слушаем событие отправки формы
  form.on('submit', function(event) {
    // Получаем список критериев
    const criteriaItems = $('#itemList li');

    // Проходимся по каждому критерию
    criteriaItems.each(function(index) {
      // Получаем текст критерия
      const criteriaText = $(this).text();

      // Создаем скрытое поле
      const hiddenInput = $('<input>')
        .attr('type', 'hidden')
        .attr('name', 'criteria[]') // Используем массив, чтобы сервер мог обработать несколько значений
        .val(criteriaText);

      // Добавляем скрытое поле в форму
      form.append(hiddenInput);
    });

  });
});