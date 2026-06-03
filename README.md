# 🌾 Ivern Nature - Plataforma Educativa Agropecuaria

Una plataforma web diseñada para democratizar el acceso a la formación agropecuaria. Su objetivo es facilitar a jóvenes agricultores y ganaderos el acceso a material educativo, la resolución de dudas y el contacto directo con profesionales del sector. 

## 🛠️ Stack Tecnológico

*   **Backend:** Java, Spring Boot
*   **Frontend:** HTML5, CSS3, JavaScript, Thymeleaf
*   **Base de Datos:** MySQL
*   **Herramientas Adicionales:** Lombok, Maven

## 👥 Arquitectura de Roles y Funcionalidades

El sistema está diseñado con un sistema de autorización basado en 4 niveles de acceso:

*   **Usuario Anónimo:** Acceso a la *landing page*, catálogo público de cursos y páginas estáticas de información.
*   **Alumno (Usuario Logueado):** Acceso al contenido de los cursos matriculados, chat de resolución de dudas, descarga de material educativo y realización de exámenes (Quizzes).
*   **Profesor:** Panel de gestión (CRUD) para crear, editar o eliminar cursos, subir material didáctico y gestionar los cuestionarios de evaluación.
*   **Administrador:** Control total de la plataforma. Hereda los permisos del Profesor y añade la capacidad de gestionar (CRUD) usuarios y dar de alta a nuevos profesores.

## 📸 Interfaz de Usuario

*Pantalla de inicio*
![Pantalla de inicio](https://github.com/user-attachments/assets/b8bc2a82-1793-49bc-9aee-3ba1257fbe69)

*Panel de Alumno / Cursos*
![Panel de Alumno / Cursos](https://github.com/user-attachments/assets/1649e9f6-1bd8-4e5b-997e-54417f0e0706)

*Panel de Administración CRUD*
![Panel de Administración CRUD](https://github.com/user-attachments/assets/5474083f-6d44-44ac-8549-a9ef497d2fc4)

## 🚀 Instalación y Despliegue en Local

Sigue estos pasos para ejecutar el proyecto en tu entorno de desarrollo local.

### Prerrequisitos
*   Tener instalado **Spring Tools Suite (STS)** o tu IDE de preferencia.
*   Tener un servidor de MySQL corriendo en tu máquina (ej. XAMPP, MySQL Workbench).
*   **Importante:** Este proyecto utiliza [Lombok](https://projectlombok.org/) para reducir el código repetitivo. Es obligatorio instalar el plugin de Lombok en tu IDE (ejecutando el `.jar` de Lombok y seleccionando el ejecutable de STS) antes de importar el proyecto para evitar errores de compilación.

### Pasos de ejecución
1. Clonar este repositorio: `git clone https://github.com/ItzRagnarok/IvernNatureDAM.git`
2. Crear una base de datos en MySQL llamada `ivernnature`.
3. Configurar las credenciales de la base de datos en el archivo `src/main/resources/application.properties`.
4. Importar el proyecto en Spring Tools Suite como "Existing Maven Project".
5. Actualizar las dependencias de Maven (Update Project).
6. Ejecutar la clase principal `IvernNatureApplication.java` como *Spring Boot App*.
7. Acceder en el navegador a `http://localhost:8080`.
