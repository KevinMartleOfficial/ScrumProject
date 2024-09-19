"use strict";
import {byId, toon, verberg} from "./util.js";
let bestelId = null;

fetchArtikellen();

byId("knop").onclick = async () => {
    const response = await fetch(`uitgaandelevering/add`, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: bestelId
    });

    const uitgaandeleveringNr = await response.json();
    sessionStorage.clear();
    window.location = "./index.html";
}

async function fetchArtikellen() {
    const response = await fetch("bestellingen/eerste");
    if (response.ok) {
        const bestelLijst = await response.json();
        vulTabel(bestelLijst)
        bestelId = bestelLijst[0].bestelId;
        console.log(bestelId)
    } else {
        byId("BestellingId").innerHTML="Bestelling nr: "+bestelId;
    }else{
        toon("storing");
    }
}

function checkboxLijstAanmaken() {
    const checkboxList = document.getElementsByClassName("checkboxes");
    console.log(checkboxList);
    var checkboxStatus = new Array(checkboxList.length).fill(0);

    for (let i = 0; i < checkboxList.length; i++) {
        checkboxList[i].onchange = () => telVinkjes();
    }

    function telVinkjes() {
        var aantalVinkjes = 0;
        for (var i = 0; i < checkboxList.length; i++) {
            if (checkboxList[i].checked) {
                aantalVinkjes++;
                checkboxStatus[i] = 1;
            } else {
                checkboxStatus[i] = 0;
            }
        }

        // Controle
        console.log(checkboxStatus);

        if (aantalVinkjes === checkboxList.length) {
            toon("knop");
        } else {
            verberg("knop");
        }
    }
}

function vulTabel(bestelLijst) {
    const tabel = byId("tabelBestellingOverzicht")
    for (const bestelling of bestelLijst) {
        const tr = tabel.insertRow();
        const a = document.createElement("a");
        a.setAttribute("class", bestelling.artikelId);
        a.innerHTML = bestelling.artikelNaam
        a.href = "";
        a.addEventListener("click", event => {
            event.preventDefault();
            sessionStorage.setItem("artikelId", bestelling.artikelId);
            window.location = "./artikelOverzicht.html"
        });
        tr.insertCell().appendChild(a);
        tr.insertCell().textContent = bestelling.aantal;
        tr.insertCell().textContent = bestelling.magazijnPlaats;
        const input = document.createElement("input");
        input.setAttribute("type", "checkbox");
        input.className = "checkboxes";
        tr.insertCell().appendChild(input);
        checkboxLijstAanmaken();
    }
}




