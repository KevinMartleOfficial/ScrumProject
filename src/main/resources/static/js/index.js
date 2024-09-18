"use strict"
import {byId, verwijderChildElementenVan, toon, verberg} from "./util.js";

async function fetchArtikellen(){
    const response = await fetch("bestellingen/gfgf");
    if (response.ok){
        const aantalBestellingen = await response.json();
    }else{
        toon("storing");
        verberg("buttons")
    }
}