"use strict";
import {byId, toon, verberg} from "./util.js";

// tijdelijke leveringsbonNummer
// byId("leveringsbonNummer").innerText = sessionStorage.getItem("leveringsbonNummer");
byId("leveringsbonNummer").innerText = 1;

//tijdelijke leveringsboninhoud
// const leveringsbonLijst = sessionStorage.getItem("leveringsbonLijst");
const leveringsbonLijst = [
    {
        "artikelId": 4,
        "artikelNaam": "keukenstoel",
        "artikelEannummer": 5499999000040,
        "aantal": 5
    },
    {
        "artikelId": 29,
        "artikelNaam": "kruk",
        "artikelEannummer": 5499999000293,
        "aantal": 8
    },
    {
        "artikelId": 39,
        "artikelNaam": "TV-meubel",
        "artikelEannummer": 5499999000392,
        "aantal": 12
    },
    {
        "artikelId": 64,
        "artikelNaam": "Staande lamp",
        "artikelEannummer": 5499999000644,
        "aantal": 7
    },
    {
        "artikelId": 94,
        "artikelNaam": "Cirkelzaag 400W",
        "artikelEannummer": 5499999000941,
        "aantal": 4
    }
];

vulTabel(leveringsbonLijst);

function vulTabel(leveringsbonLijst) {
    const tabel = byId("tabelLeveringsBonOverzicht");
    for (const artikel of leveringsbonLijst) {
        const tr = tabel.insertRow();
        const a = document.createElement("a");
        a.setAttribute("class", artikel.artikelId);
        a.innerHTML = artikel.artikelNaam;
        a.href = "";
        a.addEventListener("click", event => {
            event.preventDefault();
            sessionStorage.setItem("artikelId", artikel.artikelId);
            window.location = "./artikelOverzicht.html"
        });
        tr.insertCell().appendChild(a);
        tr.insertCell().textContent = artikel.artikelEannummer;
        tr.insertCell().textContent = artikel.aantal;
        const input = document.createElement("input");
        input.setAttribute("type", "number");
        input.setAttribute("min", 0);
        input.setAttribute("max", artikel.aantal);
        input.className = "aantalGoedgekeurd";
        tr.insertCell().appendChild(input);
        const afgekeurdSpan = document.createElement("span");
        afgekeurdSpan.innerText = artikel.aantal - input.value;
        tr.insertCell().appendChild(afgekeurdSpan);
        input.onchange = () => {
            afgekeurdSpan.innerText = artikel.aantal - input.value;
        }
    }
}