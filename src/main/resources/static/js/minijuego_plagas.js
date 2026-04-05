/* --- VARIABLES GLOBALES --- */
let casos = []; // Ahora empieza vacío y se llena desde la Base de Datos
let casoActual = null;
let contadorAciertos = 0;
let contadorFallos = 0;

/* --- ELEMENTOS DEL DOM --- */
const tools = document.querySelectorAll('.tool');
const dropZone = document.getElementById('drop-zone');
const plantImage = document.getElementById('case-image');
const plantText = document.getElementById('case-text');
const messageBox = document.getElementById('message-box');
const btnNext = document.getElementById('btn-next');
const scoreSpan = document.getElementById('score-count');
const failSpan = document.getElementById('fail-count');

/* --- INICIALIZACIÓN CON BASE DE DATOS --- */
async function iniciarJuego() {
	try {
		// Pedimos los datos al RestController de Spring Boot
		const respuesta = await fetch(`/api/quizz/${idQuizzActual}/datos`);
		casos = await respuesta.json();

		if (casos.length === 0) {
			plantText.textContent = "El profesor aún no ha añadido plagas a este minijuego.";
			document.querySelector('.tools-container').style.display = 'none'; // Ocultamos herramientas
			plantImage.style.display = 'none';
			return;
		}

		cargarCasoAleatorio();
	} catch (error) {
		console.error("Error cargando los casos desde BD:", error);
		plantText.textContent = "Error de conexión al cargar el minijuego.";
	}
}

// Arrancamos el juego
iniciarJuego();

btnNext.addEventListener('click', () => {
	cargarCasoAleatorio();
});

/* --- LÓGICA DEL JUEGO --- */
function cargarCasoAleatorio() {
	messageBox.style.display = 'none';
	messageBox.className = '';
	btnNext.classList.add('hidden');
	plantImage.classList.remove('filter-healed');
	plantImage.classList.add('filter-sick');
	dropZone.classList.remove('drag-over');

	// Seleccionar caso aleatorio
	let nuevoCaso;
	do {
		const randomIndex = Math.floor(Math.random() * casos.length);
		nuevoCaso = casos[randomIndex];
	} while (casoActual && nuevoCaso.idcasoquizz === casoActual.idcasoquizz && casos.length > 1);

	casoActual = nuevoCaso;

	// Renderizar (FÍJATE EN LA RUTA DE LA IMAGEN)
	plantImage.src = '/images/quizz/' + casoActual.img;
	plantText.textContent = `Diagnóstico: ${casoActual.texto}`;
	dropZone.dataset.required = casoActual.solucion;
}

/* --- DRAG AND DROP --- */
tools.forEach(tool => {
	tool.addEventListener('dragstart', (e) => {
		e.dataTransfer.setData('text/plain', tool.dataset.type);
		tool.style.opacity = '0.5';
	});
	tool.addEventListener('dragend', () => tool.style.opacity = '1');
});

dropZone.addEventListener('dragover', (e) => {
	e.preventDefault();
	dropZone.classList.add('drag-over');
});

dropZone.addEventListener('dragleave', () => {
	dropZone.classList.remove('drag-over');
});

dropZone.addEventListener('drop', (e) => {
	e.preventDefault();
	dropZone.classList.remove('drag-over');

	if (!btnNext.classList.contains('hidden')) return; // Evitar jugar si ya acertó

	const toolType = e.dataTransfer.getData('text/plain');
	const required = dropZone.dataset.required;

	checkResult(toolType, required);
});

function checkResult(selected, required) {
	messageBox.style.display = 'block';
	messageBox.classList.remove('success', 'error');

	if (selected === required) {
		// --- ACIERTO ---
		messageBox.classList.add('success');
		messageBox.innerHTML = `<strong>¡Excelente! ✅</strong><br>${casoActual.mensajeExito}`;

		plantImage.classList.remove('filter-sick');
		plantImage.classList.add('filter-healed');
		btnNext.classList.remove('hidden');

		contadorAciertos++;
		scoreSpan.textContent = contadorAciertos;

	} else {
		// --- FALLO ---
		messageBox.classList.add('error');
		messageBox.innerHTML = `<strong>Incorrecto ❌</strong><br>${casoActual.pistaError}`;

		contadorFallos++;
		failSpan.textContent = contadorFallos;
	}
}