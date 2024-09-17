"use strict"

import {byId} from "./util.js";

getBestellingen();

async function getBestellingen() {
    const response = await fetch("bestelling/tv");
    if (response.ok) {
        const bestellingen = await response.json();
        const tbody = byId("bestellingenBody")
        for (const bestelling of bestellingen){
            const tr = tbody.insertRow();
            tr.insertCell().textContent = bestelling.bestelId;
            tr.insertCell().textContent = bestelling.aantalProducten;
            tr.insertCell().textContent = bestelling.totaalGewicht;
        }
    }
}