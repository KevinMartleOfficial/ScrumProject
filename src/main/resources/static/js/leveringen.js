"use strict";
import {byId, setText, toon, verberg} from "./util.js";

byId("toevoegen").onclick = async function () {
    verbergFouten();
    const lnaam = byId("lnaam");
    if (!lnaam.checkValidity()) {
        setText("storing", "leveranciersnaam niet ingevuld");
        toon("storing")
        return;
    }
    const lbnr = byId("lbnr");
    if (!lbnr.checkValidity()) {
        setText("storing", "leveringsbonnummer niet ingevuld");
        toon("storing");
        return;
    }
    const lbdatum = byId("lbdatum");
    if (!lbdatum.checkValidity()) {
        setText("storing", "leveringsbon datum niet ingevuld");
        toon("storing");
        return;
    }
    const leveringsbon = {
        naam: lnaam.value,
        leveringsbonNummer: lbnr.value,
        leveringsbondatum: lbdatum.value
    };
    voegToe(leveringsbon);
}

function verbergFouten() {
    verberg("storing");
}

async function voegToe(leveringsbon) {
    const response = await fetch("inkomende-leveringen/toevoegen",
        {
            method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(leveringsbon)
        });

    if (response.ok) {
        window.location = "artikelsLeveringen.html"
    } else {
    setText("storing", "Storing");
            toon("storing");
    }

    console.log(leveringsbon);
}
