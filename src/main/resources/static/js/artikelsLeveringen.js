import { byId, toon, verberg, verwijderChildElementenVan } from "./util.js";

byId("leveringsbonKnop").onclick = () => {
    if (controleerVeldenIngevuld()){
    slaRechtseTabelOp();
    }else{
        toon("inputstoring");
    }
};

document.getElementById("eanZoeken").addEventListener('input', async () => {
    const value = document.getElementById("eanZoeken").value;
    const response = await fetch(`artikelen?naamBevat=${value}`);

    if (response.ok) {
        const data = await response.json();
        vulLinkseTabel(data);
    } else {
        toon("storing");
    }
});

function vulLinkseTabel(productLijst) {
    const tabel = byId("linkseTabel");
    verwijderChildElementenVan(tabel);
    for (const item of productLijst) {
        const tr = tabel.insertRow();
        tr.insertCell().textContent = item.ean;
        const button = document.createElement("button");
        button.setAttribute("type", "button");
        button.setAttribute("id", item.id);
        button.setAttribute("class", 'knopToevoegenEAN');
        button.innerHTML = "toevoegen";
        button.addEventListener("click", event => {
            vulRechtseTabel(item.ean, item.id, item.naam);
        });
        tr.insertCell().appendChild(button);
    }
}

function vulRechtseTabel(ean, artikelId, artikelNaam) {
    const tabel = byId("rechtseTabel");
    const tr = tabel.insertRow();
    tr.insertCell().textContent = ean;

    const input = document.createElement("input");
    input.setAttribute("placeholder", "aantal");
    tr.insertCell().appendChild(input);
    tr.dataset.artikelId = artikelId;
    tr.dataset.artikelNaam = artikelNaam;
    const deleteButton = document.createElement("button");
    deleteButton.textContent = "verwijder";
    deleteButton.setAttribute("class", 'knopVerwijderEAN');
    deleteButton.addEventListener("click", function () {
        tabel.deleteRow(tr.rowIndex - 1);
    });

    tr.insertCell().appendChild(deleteButton);


}

function slaRechtseTabelOp() {
    const tabel = byId("rechtseTabel");
    const data = [];

    for (let i = 0; i < tabel.rows.length; i++) {
        const row = tabel.rows[i];
        const artikelEannummer = row.cells[0].textContent;
        const aantal = row.cells[1].querySelector("input") ? row.cells[1].querySelector("input").value : "";

        const artikelId = row.dataset.artikelId;
        const artikelNaam = row.dataset.artikelNaam;

        data.push({ artikelId, artikelNaam, artikelEannummer, aantal });
    }
    sessionStorage.setItem("leveringsbonLijst", JSON.stringify(data));
    window.location.href="./leveringsbonValidatie.html"
}

function controleerVeldenIngevuld() {
    const rijen = [...document.getElementById("rechtseTabel").rows];
    if (rijen.length>0){
    return rijen.every(row => row.cells[1].querySelector("input").value.trim());
    }
    return false;
}