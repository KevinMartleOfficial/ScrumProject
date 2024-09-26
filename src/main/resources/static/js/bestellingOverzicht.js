"use strict";
import {byId, toon, verberg} from "./util.js";

let bestelId = null;

fetchArtikelen();

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

async function fetchArtikelen() {
    const response = await fetch("bestellingen/eerste");
    if (response.ok) {
        const bestelLijst = await response.json();
        vulTabel(bestelLijst)
        bestelId = bestelLijst[0].bestelId;
        console.log(bestelId)
        byId("BestellingId").innerHTML = "Bestelling Nr: " + bestelId;
    } else {
        toon("storing");
    }
}

function slaCheckBoxStatusOp() {
    const checkboxList = document.getElementsByClassName("checkboxes");
    let checkboxStatus = [];
    for (let i = 0; i < checkboxList.length; i++) {
        checkboxStatus.push(checkboxList[i].checked ? 1 : 0);
    }
    sessionStorage.setItem("checkboxStatus", JSON.stringify(checkboxStatus));
}

function herstelCheckboxStatus() {
    const checkboxList = document.getElementsByClassName("checkboxes");
    let opgeslagenStatus = JSON.parse(sessionStorage.getItem("checkboxStatus"));

    if (sessionStorage.getItem("checkboxStatus") !== null) {
        for (let i = 0; i < checkboxList.length; i++) {
            checkboxList[i].checked = opgeslagenStatus[i] === 1;
        }
        telVinkjes();
        sessionStorage.setItem("checkboxStatus", JSON.stringify(opgeslagenStatus));
    }
}

function checkboxLijstAanmaken() {
    const checkboxList = document.getElementsByClassName("checkboxes");

    for (let i = 0; i < checkboxList.length; i++) {
        checkboxList[i].onchange = () => {
            telVinkjes();
            slaCheckBoxStatusOp();
        }
    }
}

function telVinkjes() {
    const checkboxList = document.getElementsByClassName("checkboxes");
    var aantalVinkjes = 0;

    for (let i = 0; i < checkboxList.length; i++) {
        if (checkboxList[i].checked) {
            aantalVinkjes++;
        }
    }

    if (aantalVinkjes === checkboxList.length) {
        toon("knop");
    } else {
        verberg("knop");
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
    herstelCheckboxStatus();
}