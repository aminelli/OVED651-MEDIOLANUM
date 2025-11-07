function bodyResponseConvert(response) {
  if (response.status === 200) return response.json();
}

function createTable(productList) {
  console.dir(productList);
  const size = productList.length;
  const tbody = document.getElementById("table-body");
  for (let i = 0; i < size; i++) {
    const tdNome = document.createElement("td");
    tdNome.innerText = productList[i]["nome"];
    const tdMarca = document.createElement("td");
    tdMarca.innerText = productList[i]["marca"];
    const tdPrezzo = document.createElement("td");
    tdPrezzo.innerText = productList[i]["prezzo"];
    const tdCliente = document.createElement("td");
    tdCliente.innerText = productList[i]["clienteId"]["id"];
    const tr = document.createElement("tr");
    tr.appendChild(tdNome);
    tr.appendChild(tdMarca);
    tr.appendChild(tdPrezzo);
    tr.appendChild(tdCliente);
    tbody.appendChild(tr);
  }
}
function loadData() {
  const url = "http://localhost:8090/shop/product/price/0/to/500";

  const request = {
    headers: {
      Accept: "application/json",
    },
  };
  const p1 = fetch(url, request);

  const p2 = p1.then(bodyResponseConvert);
  p2.then(createTable);

  console.dir(p1);
}
loadData();
