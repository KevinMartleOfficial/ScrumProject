"use strict"

import {byId, verwijderChildElementenVan} from "./util.js";

getBestellingen();
getTotaalAantal();

document.addEventListener("dblclick", () => {
    toggleFullscreen();
})

setInterval(function() {
    getBestellingen();
    getTotaalAantal();
    console.log("test");
}, 60000);

async function getBestellingen() {
    const response = await fetch("bestelling/tv");
    if (response.ok) {
        const bestellingen = await response.json();
        const tbody = byId("bestellingenBody");
        verwijderChildElementenVan(tbody);
        for (const bestelling of bestellingen){
            const tr = tbody.insertRow();
            tr.insertCell().textContent = bestelling.bestelId;
            tr.insertCell().textContent = bestelling.aantalProducten;
            tr.insertCell().textContent = bestelling.totaalGewicht;
        }
    }
}

async function getTotaalAantal() {
    const response = await fetch("bestellingen/aantal");
    if (response.ok) {
        const totaal = await response.json();
        const totaalAantalBestellingen = byId("totaalSpan");
        totaalAantalBestellingen.innerText = totaal;
    }
}

function getFullscreenElement() { //zorgt ervoor dat het compatibel is met verschillende webbrowsers
    return document.fullscreenElement
        || document.webkitFullscreenElement
        || document.mozFullscreenElement
        || document.msFullscreenElement;
}

function toggleFullscreen() {
    if (getFullscreenElement()) {
        document.exitFullscreen();
    } else {
        document.documentElement.requestFullscreen();
    }
}