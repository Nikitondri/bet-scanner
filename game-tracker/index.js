function addTableHeader(table) {
  const headers = ['id', 'date', 'tournament', 'sport_kind', 'reference'];

  const thead = document.createElement('thead');
  const tr = document.createElement('tr');

  headers.forEach(headerText => {
    const th = document.createElement('th');
    th.innerText = headerText;
    tr.appendChild(th);
  });

  thead.appendChild(tr);
  table.appendChild(thead);
}

function addDataToTable(data, table) {
  const tbody = document.createElement('tbody');

  data.forEach(rowData => {
    const tr = document.createElement('tr');
    const idCell = document.createElement('td');
    const dateCell = document.createElement('td');
    const tournamentCell = document.createElement('td');
    const sportKindCell = document.createElement('td');
    const referenceCell = document.createElement('td');

    idCell.innerText = rowData.id;
    dateCell.innerText = rowData.date;
    tournamentCell.innerText = rowData.tournament;
    sportKindCell.innerText = rowData.sportKind;
    referenceCell.innerText = rowData.reference;

    tr.appendChild(idCell);
    tr.appendChild(dateCell);
    tr.appendChild(tournamentCell);
    tr.appendChild(sportKindCell);
    tr.appendChild(referenceCell);

    tbody.appendChild(tr);
  });

  table.appendChild(tbody);
}

function sortDataByDate() {
  const table = document.getElementById('data-table');
  const tbody = table.querySelector('tbody');
  const rows = Array.from(tbody.getElementsByTagName('tr'));

  rows.sort((a, b) => {
    const dateA = new Date(a.cells[1].innerText);
    const dateB = new Date(b.cells[1].innerText);
    return dateA - dateB;
  });

  tbody.innerHTML = '';
  rows.forEach(row => tbody.appendChild(row));
}

function loadData() {
  fetch('http://localhost:8080/marathonbet/')
  .then(response => response.json())
  .then(data => {
    const table = document.getElementById('data-table');
    addTableHeader(table);
    addDataToTable(data, table);
  })
  .catch(error => {
    console.error('request error: ', error);
  });
}

const loadButton = document.getElementById('load-data-button');
loadButton.addEventListener('click', loadData);

const sortButton = document.getElementById('sort-data-button');
sortButton.addEventListener('click', sortDataByDate);
