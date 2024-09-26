"use strict"
import {byId, verwijderChildElementenVan, toon, verberg} from "./util.js";

fetchArtikelen();

async function fetchArtikelen() {
    const response = await fetch("bestellingen/aantal");
    if (response.ok) {
        const aantalBestellingen = await response.json();
        console.log(aantalBestellingen);
        if (aantalBestellingen > 0) {
            window.location= "./bestellingOverzicht.html"
        }
    } else {
        toon("storing");
        verberg("leveringButton");
        verberg("retourButton");
    }
}