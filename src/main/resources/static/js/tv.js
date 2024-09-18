"use strict"

import {byId, verwijderChildElementenVan, toon, verberg} from "./util.js";

getBestellingen();
getTotaalAantal();

document.addEventListener("dblclick", () => {
    toggleFullscreen();
})

setInterval(function() {
    getBestellingen();
    getTotaalAantal();
}, 60000);

async function getBestellingen() {
    const response = await fetch("bestelling/tv");
    if (response.ok) {
        toon("bestellingenTabel")
        const bestellingen = await response.json();
        const tbody = byId("bestellingenBody");
        verwijderChildElementenVan(tbody);
        for (const bestelling of bestellingen){
            const tr = tbody.insertRow();
            tr.insertCell().textContent = bestelling.bestelId;
            tr.insertCell().textContent = bestelling.aantalProducten;
            tr.insertCell().textContent = parseInt(bestelling.totaalGewicht)/1000;
        }
    } else {
        verberg("bestellingenTabel")
        toon("storingTv");
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