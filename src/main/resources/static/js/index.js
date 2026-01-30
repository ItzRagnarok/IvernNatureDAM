import { cabeceraMenu } from "../parciales/cabecera.js"
import { footerPagina } from "../parciales/footer.js"


window.onload = function () {
    let header = document.querySelector("header");
    let footer = document.querySelector("footer");
    header.innerHTML = cabeceraMenu;
    footer.innerHTML = footerPagina;

}
