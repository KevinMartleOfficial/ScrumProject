"use strict";
import {byId, setText, toon, verberg} from "./util.js";

const leveringsBonId = JSON.parse(sessionStorage.getItem("inkomendeLeveringsId"));
const leveringsbonNummer = (sessionStorage.getItem("leveringsbonNummer"));
setText("leveringsbonNummer", "Leveringsbon nr: " + leveringsbonNummer);

byId("knop").onclick = async () => {
    await fetch(`inkomendeleveringslijn/verhoog`, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: leveringsBonId
    });
    sessionStorage.clear();
    window.location = "./index.html";
}

const response = await fetch(`inkomendeleveringslijn/${leveringsBonId}`);

// om te testen
//const response = await fetch(`inkomendeleveringslijn/1`);

if (response.ok) {
    const leveringsOverzicht = await response.json();
    const tabelLeveringsOverzicht = byId("tabelLeveringsOverzicht");
    let teller = 0;
    for (const artikel of leveringsOverzicht) {
        const tr = tabelLeveringsOverzicht.insertRow();
        const a = document.createElement("a");
        a.setAttribute("class", artikel.artikelId);
        a.innerHTML = artikel.artikelNaam;
        a.id = teller;
        a.href = "";
        a.addEventListener("click", event => {
            event.preventDefault();
            const leveringsbonLijst = JSON.parse(sessionStorage.getItem("leveringsbonLijst"));
            console.log(leveringsbonLijst[a.id].artikelId);
            sessionStorage.setItem("artikelId", leveringsbonLijst[a.id].artikelId);
            window.location = "./artikelOverzicht.html";
        });
        tr.insertCell().appendChild(a);
        tr.insertCell().innerText = artikel.aantalGoedgekeurd;
        tr.insertCell().innerText = artikel.magazijnPlaats;
        const input = document.createElement("input");
        input.setAttribute("type", "checkbox");
        input.className = "checkboxes";
        tr.insertCell().appendChild(input);
        checkboxLijstAanmaken();
        teller++;
    }
    herstelCheckboxStatus();
} else {
    toon("storing");
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