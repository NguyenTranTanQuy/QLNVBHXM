function sortTable(tableId) {
  $(document).ready(function() {
    $('#' + tableId + ' th').each(function() {
      $(this).click(function() {
        let table = $(this).parents('table').eq(0)
        let rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()))
        this.asc = !this.asc
        if (!this.asc) {
          rows = rows.reverse()
        }
        for (let i = 0; i < rows.length; i++) {
          table.append(rows[i])
        }
        // Remove the sorted class from all other header cells
        table.find('th').removeClass('sorted')
        // Add the sorted class to the clicked header cell
        $(this).addClass('sorted')
      })
    })
  })

  function comparer(index) {
    return function(a, b) {
      let valA = getCellValue(a, index)
      let valB = getCellValue(b, index)
      return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.toString().localeCompare(valB)
    }
  }

  function getCellValue(row, index) {
    return $(row).children('td').eq(index).text()
  }
}
