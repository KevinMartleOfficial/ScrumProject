"use strict";
import {byId, toon, verberg} from "./util.js";

const leveringsbonId = JSON.parse(sessionStorage.getItem("inkomendeLeveringsId"));

// tijdelijke Mock data hieronder
// const leveringsbonNummer = 1;
byId("leveringsbonNummer").innerText = JSON.parse(sessionStorage.getItem("leveringsbonNummer"));

const leveringsbonLijst = JSON.parse(sessionStorage.getItem("leveringsbonLijst"));
// Tijdelijke mock data hieronder
 //const leveringsbonLijst = [
    //{"artikelId": 94, "artikelNaam": "Cirkelzaag 400W", "artikelEannummer": 5499999000941, "aantal": 4},
    //{"artikelId": 25, "artikelNaam": "eettafel", "artikelEannummer": 5499999000255, "aantal": 6}]

vulTabel(leveringsbonLijst);

byId("buttonBevestig").onclick = async () => {
    maakLeveringTeBevestigen();
    const leveringTeBevestigen = JSON.parse(sessionStorage.getItem("leveringTeBevestigen"));
    const response = await fetch(`inkomendeleveringslijn/add`, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(leveringTeBevestigen)
    });
    if (response.ok) {
        window.location = "./leveringsbonOverzicht.html";
    } else {
        toon("storing");
    }
}

function vulTabel(leveringsbonLijst) {
    const tabel = byId("tabelLeveringsBonOverzicht");
    for (const artikel of leveringsbonLijst) {
        console.log(leveringsbonLijst);
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
        afgekeurdSpan.className = "aantalAfgekeurd";
        afgekeurdSpan.innerText = Number(artikel.aantal - input.value);
        tr.insertCell().appendChild(afgekeurdSpan);
        input.onchange = () => {
            afgekeurdSpan.innerText = Number(artikel.aantal - input.value);
            telGoedgekeurd();
            maakGoedgekeurdEnAfgekeurdInSessionStorage();
        }
    }
    if (sessionStorage.getItem("goedgekeurdEnAfgekeurd")) {
        let goedgekeurdEnAfgekeurd = JSON.parse(sessionStorage.getItem("goedgekeurdEnAfgekeurd"));
        const aantalGoedgekeurdList = document.getElementsByClassName("aantalGoedgekeurd");
        const aantalAfgekeurdList = document.getElementsByClassName("aantalAfgekeurd");
        for (let i = 0; i < goedgekeurdEnAfgekeurd.length; i++) {
            const goedgekeurd = goedgekeurdEnAfgekeurd[i].aantalGoedgekeurd;
            const afgekeurd = goedgekeurdEnAfgekeurd[i].aantalTeruggestuurd;
            aantalGoedgekeurdList[i].value = goedgekeurd;
            aantalAfgekeurdList[i].innerText = afgekeurd;
        }
    }
    // Onderstaande zorgt ervoor dat magazijnier nooit een waarde kan ingeven dat lager of hoger ligt dan min en max
    document.querySelectorAll('input.aantalGoedgekeurd').forEach(input => {
        input.addEventListener('input', function () {let min = parseInt(input.min);
            let max = parseInt(input.max);
            let value = parseInt(input.value);

            if (value < min) {
                input.value = min;
            } else if (value > max) {
                input.value = max;
            }
        });
    });
    telGoedgekeurd();
}

function telGoedgekeurd() {
    const aantalGoedgekeurdList = document.getElementsByClassName("aantalGoedgekeurd");
    var ingevuld = 0

    for (let i = 0; i < aantalGoedgekeurdList.length; i++) {
        if (aantalGoedgekeurdList[i].value.trim() !== "") {
            ingevuld++;
        }
    }

    if (ingevuld === aantalGoedgekeurdList.length) {
        toon("buttonBevestig");
    } else {
        verberg("buttonBevestig");
    }
}

function maakGoedgekeurdEnAfgekeurdInSessionStorage() {
    const aantalGoedgekeurdList = document.getElementsByClassName("aantalGoedgekeurd");
    const aantalAfgekeurdList = document.getElementsByClassName("aantalAfgekeurd");
    if (sessionStorage.getItem("goedgekeurdEnAfgekeurd")) {
        sessionStorage.removeItem("goedgekeurdEnAfgekeurd")
    }
    const goedGekeurdEnAfgekeurd = [];
    for (let i = 0; i < leveringsbonLijst.length; i++) {
        const aantalGoedgekeurd = aantalGoedgekeurdList[i].value;
        const aantalTeruggestuurd = aantalAfgekeurdList[i].innerText;
        goedGekeurdEnAfgekeurd.push({aantalGoedgekeurd, aantalTeruggestuurd});
    }
    sessionStorage.setItem("goedgekeurdEnAfgekeurd", JSON.stringify(goedGekeurdEnAfgekeurd));
}

function maakLeveringTeBevestigen() {
    const aantalGoedgekeurdList = document.getElementsByClassName("aantalGoedgekeurd");
    const aantalAfgekeurdList = document.getElementsByClassName("aantalAfgekeurd");
    if (sessionStorage.getItem("leveringTeBevestigen")) {
        sessionStorage.removeItem("leveringTeBevestigen")
    }
    const leveringTeBevestigen = [];
    for (let i = 0; i < leveringsbonLijst.length; i++) {
        const inkomendeLeveringsId = leveringsbonId;
        const artikelId = leveringsbonLijst[i].artikelId;
        const aantalGoedgekeurd = aantalGoedgekeurdList[i].value;
        const aantalTeruggestuurd = aantalAfgekeurdList[i].innerText;
        const leveringsbonLijn = {
            inkomendeLeveringsId: parseInt(inkomendeLeveringsId),
            artikelId: parseInt(artikelId),
            aantalGoedgekeurd: parseInt(aantalGoedgekeurd),
            aantalTeruggestuurd: parseInt(aantalTeruggestuurd)
        }
        leveringTeBevestigen.push(leveringsbonLijn);
    }
    sessionStorage.setItem("leveringTeBevestigen", JSON.stringify(leveringTeBevestigen));
}