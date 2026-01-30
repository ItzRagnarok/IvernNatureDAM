window.onload = function () {

    // Código para mover las label al seleccionar los input demas entradas
    const inputs = document.querySelectorAll("input, select");

    // Eventos de obtener y perder foco de los elementos de entrada
    for (let input of inputs) {
        input.addEventListener("focus", obtenFoco);
        input.addEventListener("blur", pierdeFoco);
    };

    // Desplazamos Label en función de si hay entradas o no
    let estiloLabel;  // Estilo inicial de la label para restaurarla al perder e foco
    function obtenFoco() {
        estiloLabel = (estiloLabel) ? this.nextElementSibling.style.cssText : estiloLabel;
        this.classList.add("input-foco");
        this.nextElementSibling.style.cssText = "font-size: 12px; color: var(--color-fondo-caja); top: -15px;"
    }

    function pierdeFoco() {
        if (this.value === "") {
            this.classList.remove("input-foco");
            this.nextElementSibling.style.cssText = estiloLabel;
        }
    }

    // Codigo para los efectos de desplazamiento de velo y formularios
    const velo = document.querySelector(".velo");
    const veloIzquierda = document.querySelector(".velo-izquierda");
    const veloDerecha = document.querySelector(".velo-derecha");

    const formLogin = document.querySelector("form.login");
    const formNuevo = document.querySelector("form.nuevo");

    // Botones para seleccionar formulario: login o nuevo usuario
    document.querySelector("#btnIzquierda").addEventListener("click", desplazaVelo);
    document.querySelector("#btnDerecha").addEventListener("click", desplazaVelo);

    // Operaciones de desplazamientos de elementos en función de botón pulsado
    function desplazaVelo(even) {
        if (even.target.id === "btnDerecha") {
            velo.style.width = "350px";
            velo.style.transform = "translateX(-100%)";
            veloIzquierda.style.transform = "translateX(0)";
            veloDerecha.style.transform = "translateX(-100%)";
            formLogin.style.transform = "translateY(100%)";
            formNuevo.style.transform = "translateY(0)";

        } else {
            velo.style.width = "418px";
            velo.style.transform = "translateX(0)";
            veloIzquierda.style.transform = "translateX(100%)";
            veloDerecha.style.transform = "translateX(0)";
            formLogin.style.transform = "translateY(0)";
            formNuevo.style.transform = "translateY(-100%)";
        }
    }

    /**********************************************************/
    /* Código de tratamiento del input file y pintado de avatar */
    /***********************************************************/
    // Evento patra colocar imagen seleccionada en el formulario desde el botón correspondiente

    document.querySelector("#file_avatar").addEventListener("change", ponAvatar);

    // Código para pintar la imagen del avatar
    function ponAvatar() {
        let fileEntrada = document.querySelector("#file_avatar");
        let canvas = document.querySelector(".grupo-avatar canvas");
        let ctx = canvas.getContext("2d");

        // Establecemos ancho y alto para la imagen (igual al camvas)
        const ancho = canvas.width;
        const alto = canvas.height;

        // Cresmos objeto Image para asignarle el scr de la imagen del input file
        const avatar = new Image();
        avatar.style.cssText = "width: '100px'; height: '130px'"

        // Convertimos entrada de input file a URL. Convierte imagen a Base64
        avatar.src = URL.createObjectURL(fileEntrada.files[0]);

        // Cuando la imagen esté disponible la pintamos en el canvas. Lo que conseguimos es reducir las dimensiones de la imagen independientemente del origen
        avatar.onload = function () {
            ctx.drawImage(avatar, 0, 0, ancho, alto);
            imagenAvatar = canvas.toDataURL("images/cursos", 0.8);
        };

    }


}