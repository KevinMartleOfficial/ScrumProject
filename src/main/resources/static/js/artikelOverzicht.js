"use strict";

let data = null;

async function getArtikel(){

    const artikelId  = sessionStorage.getItem("artikelId");
    const response = await fetch(`bestellingen/artikel/${artikelId}/detailoverzicht`)
    data = await response.json();
}

async function init() {
    await getArtikel();

    const bovenPaginaContainer = document.getElementById("bovenPaginaContainer")
    const detailArtikelNummer = document.createElement("p");
    const terugKnop = document.createElement("button");
    terugKnop.id = "terugknop"
    const tablecontainer = document.getElementById("artikelOverzichtContainer")
    terugKnop.textContent = "Terug";
    terugKnop.onclick = function(){
        window.history.back();
    }

    detailArtikelNummer.textContent = "Detail artikel: " + data.artikelId;

    bovenPaginaContainer.appendChild(detailArtikelNummer);
    bovenPaginaContainer.appendChild(terugKnop);

    const table = document.getElementById("artikelOverzichtTable")
    const tbody = document.createElement("tbody");

    for(const key in data){
        const tr = document.createElement("tr");
        const tdKey = document.createElement("td");
        const tdValue = document.createElement("td");
        tdKey.textContent = key;
        tdValue.textContent = data[key];
        tr.appendChild(tdKey);
        tr.appendChild(tdValue);
        tbody.appendChild(tr);
    }
    table.appendChild(tbody);

    tablecontainer.appendChild(table)

}

init();