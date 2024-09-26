"use strict";
import {byId, setText, toon, verberg} from "./util.js";

const leveringsBonId = JSON.parse(sessionStorage.getItem("inkomendeLeveringsId"));

setText("leveringsBonId", leveringsBonId);

byId("knop")

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
    }
} else {
    toon("storing");
}