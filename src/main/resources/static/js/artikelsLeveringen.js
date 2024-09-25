import {byId, toon, verberg, verwijderChildElementenVan} from "./util.js";

byId("leveringsbonKnop").onclick = () => {
    const EanTabel = document.getElementsByName("tbody");
    console.log(EanTabel);

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

function vulLinkseTabel(productLijst){
    const tabel = byId("linkseTabel");
    verwijderChildElementenVan(tabel);
    for (const item of productLijst){
        const tr = tabel.insertRow();
        tr.insertCell().textContent = item.ean;
        const button = document.createElement("button");
        button.setAttribute("type", "button");
        button.setAttribute("id", item.id);
        button.innerHTML="toevoegen";
        button.addEventListener("click",event => {
            vulRechtseTabel(item.ean)
        })
        tr.insertCell().appendChild(button);
    }
}

function vulRechtseTabel(ean) {
    const tabel = byId("rechtseTabel");
    const tr = tabel.insertRow();
    tr.insertCell().textContent = ean;

    const input = document.createElement("input");
    input.setAttribute("placeholder", "aantal");
    tr.insertCell().appendChild(input);

    const deleteCell = tr.insertCell();
    const deleteButton = document.createElement("button");
    deleteButton.textContent = "verwijder";

    deleteButton.addEventListener("click", function() {
        tabel.deleteRow(tr.rowIndex-1);
    });

    deleteCell.appendChild(deleteButton);
}