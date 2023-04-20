function searchTable(inputId, tableId) {
  let input = document.getElementById(inputId);
  let table = document.getElementById(tableId);
  let rows = table.getElementsByTagName("tr");
  input.addEventListener("keyup", function () {
    let value = input.value.toLowerCase();
    for (let i = 1; i < rows.length; i++) {
      let show = false;
      let cells = rows[i].getElementsByTagName("td");
      for (let j = 0; j < cells.length; j++) {
        let cell = cells[j];
        if (cell.innerHTML.toLowerCase().indexOf(value) > -1) {
          show = true;
          break;
        }
      }
      if (show) {
        rows[i].style.display = "";
      } else {
        rows[i].style.display = "none";
      }
    }
  });
}
