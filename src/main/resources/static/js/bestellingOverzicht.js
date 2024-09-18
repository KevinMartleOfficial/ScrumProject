"use strict";
import {byId, toon, verberg} from "./util.js";
fetchArtikellen();

byId("knop").onclick = async () => {
    // hier komt een functie om de bestelling als uitgaande levering te plaatsen
    // maak de sessionStorage leeg
    location.reload();
}


async function fetchArtikellen(){
    const response = await fetch("bestellingen/eerste");
    if (response.ok){
        const bestelLijst = await response.json();
        console.log(bestelLijst);
        vulTabel(bestelLijst)
    }else{
        toon("storing");
    }
}

var checkboxList= document.getElementsByClassName("checkboxes");
for (var i =0;i<checkboxList.length;i++){
    console.log(checkboxList[i])
    checkboxList[i].onchange = () =>telVinkjes();
}
function telVinkjes(){
    var aantalVinkjes =0;
    for (var i =0; i<checkboxList.length;i++){
        if (checkboxList[i].checked){
            aantalVinkjes++;
        }
    }
    if (aantalVinkjes == checkboxList.length){
        toon("knop");
    }else{
        verberg("knop");
    }
}

function vulTabel(bestelLijst){
    const tabel = byId("tabelBestellingOverzicht")
    for(const bestelling of bestelLijst ){
        const tr = tabel.insertRow();
        tr.insertCell().textContent = bestelling.artikelNaam;
        tr.insertCell().textContent = bestelling.aantal;
        tr.insertCell().textContent = bestelling.magazijnPlaats;
        const input = document.createElement("input")
        input.setAttribute("type", "checkbox");
        tr.insertCell().innerHTML = input;
    }

}



