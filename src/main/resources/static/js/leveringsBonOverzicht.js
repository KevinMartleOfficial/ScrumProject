"use strict";
import {byId, setText, toon, verberg} from "./util.js";

const leveringsBonId = JSON.parse(sessionStorage.getItem("inkomendeLeveringsId"));
const leveringsbonNummer = JSON.parse(sessionStorage.getItem("leveringsbonNummer")); //moet nog gehaald worden uit session storage die nog niet is aangemaakt
setText("leveringsbonNummer", "Leveringsbon nr: " /*+ leveringsbonNummer */); //Vandaar dat leveringsbonNummer hier nog tss commentaar staat.

byId("knop").onclick = async () => {
    await fetch(`inkomendeleveringslijn/verhoog`, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: leveringsBonId
    });
    sessionStorage.clear();
    window.location = "./index.html";
}

//const response = await fetch(`inkomendeleveringslijn/${leveringsBonId}`);

// om te testen
const response = await fetch(`inkomendeleveringslijn/1`);

if (response.ok) {
    const leveringsOverzicht = await response.json();
    const tabelLeveringsOverzicht = byId("tabelLeveringsOverzicht");
    for (const artikel of leveringsOverzicht) {
        const tr = tabelLeveringsOverzicht.insertRow();
        tr.insertCell().innerText = artikel.artikelNaam;
        tr.insertCell().innerText = artikel.aantalGoedgekeurd;
        tr.insertCell().innerText = artikel.magazijnPlaats;
        const input = document.createElement("input");
        input.setAttribute("type", "checkbox");
        input.className = "checkboxes";
        tr.insertCell().appendChild(input);
        checkboxLijstAanmaken();
    }
} else {
    toon("storing");
}

function checkboxLijstAanmaken() {
    const checkboxList = document.getElementsByClassName("checkboxes");

    for (let i = 0; i < checkboxList.length; i++) {
        checkboxList[i].onchange = () => {
            telVinkjes();
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