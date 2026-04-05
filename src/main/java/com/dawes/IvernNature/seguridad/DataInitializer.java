package com.dawes.IvernNature.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.IvernNature.modelo.CasoQuizzVO;
import com.dawes.IvernNature.modelo.CursoVO;
import com.dawes.IvernNature.modelo.QuizzVO;
import com.dawes.IvernNature.modelo.RolVO;
import com.dawes.IvernNature.modelo.UsuarioVO;
import com.dawes.IvernNature.repositorio.RolPersistance;
import com.dawes.IvernNature.repositorio.UsuarioPersistance;
import com.dawes.IvernNature.servicio.interfaces.CasoQuizzService;
import com.dawes.IvernNature.servicio.interfaces.CursoService;
import com.dawes.IvernNature.servicio.interfaces.QuizzService;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioPersistance usuarioPersistance;
    @Autowired
    private RolPersistance rolPersistance;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private CursoService cursoService;
    @Autowired
    private QuizzService quizzService;
    @Autowired
    private CasoQuizzService casoQuizzService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        
        // --- 1. INICIALIZACIÓN DE ROLES Y USUARIOS ---
        RolVO rolAdmin = rolPersistance.findByNombre("ADMIN").orElse(null);
        if (rolAdmin == null) {
            rolAdmin = new RolVO();
            rolAdmin.setNombre("ADMIN");
            rolAdmin = rolPersistance.save(rolAdmin);
            System.out.println("-> Rol ADMIN creado.");
        }
        
        RolVO rolTeacher = rolPersistance.findByNombre("ROLE_TEACHER").orElse(null);
        if (rolTeacher == null) {
            rolTeacher = new RolVO();
            rolTeacher.setNombre("ROLE_TEACHER");
            rolPersistance.save(rolTeacher);
            System.out.println("-> Rol ROLE_TEACHER creado.");
        }

        RolVO rolUser = rolPersistance.findByNombre("ROLE_USER").orElse(null);
        if (rolUser == null) {
            rolUser = new RolVO();
            rolUser.setNombre("ROLE_USER");
            rolPersistance.save(rolUser);
            System.out.println("-> Rol ROLE_USER creado.");
        }

        UsuarioVO adminUser = usuarioPersistance.findByNombreUsuario("admin");
        if (adminUser == null) {
            adminUser = new UsuarioVO();
            adminUser.setAvatar("/images/default/avatar.jpg");
            adminUser.setNombreUsuario("admin");
            adminUser.setNombreYApellidos("Administrador del Sistema");
            adminUser.setCorreoElectronico("admin@ivernnature.com");
            adminUser.setPassword(passwordEncoder.encode("Admin123")); 
            adminUser.setRol(rolAdmin); 
            usuarioPersistance.save(adminUser);
            System.out.println("-> Usuario Administrador creado.");
        }

        // --- 2. INICIALIZACIÓN DEL MINIJUEGO DE AGRICULTURA ---
        
        // Buscar o crear el curso "Agricultura"
        CursoVO cursoAgri = null;
        List<CursoVO> todosLosCursos = cursoService.findAll();
        for (CursoVO c : todosLosCursos) {
            if ("Agricultura".equalsIgnoreCase(c.getNombreGrupo())) {
                cursoAgri = c;
                break;
            }
        }

        if (cursoAgri == null) {
            cursoAgri = new CursoVO();
            cursoAgri.setNombreGrupo("Agricultura");
            cursoAgri.setImagenUrl("agricultura.jpg"); // imagen tiene que estar en assets
            cursoAgri = cursoService.save(cursoAgri);
            System.out.println("-> Curso 'Agricultura' creado automáticamente.");
        }

        // Buscar o crear el Quizz dentro del curso                
        boolean existeQuizzEjemplo = false;
        if(cursoAgri.getQuizzes() != null) {
            for(QuizzVO q : cursoAgri.getQuizzes()){
                if("Clínica Anti Plagas - Básico".equals(q.getTitulo())){
                    existeQuizzEjemplo = true;
                    break;
                }
            }
        }

        if (!existeQuizzEjemplo) {
            QuizzVO quizzEjemplo = new QuizzVO();
            quizzEjemplo.setTitulo("Clínica Anti Plagas - Básico");
            quizzEjemplo.setCurso(cursoAgri);
            quizzEjemplo = quizzService.save(quizzEjemplo);
            System.out.println("-> Quizz de ejemplo creado.");

            // 2.3 Insertar los casos (Tus datos de JS)
            crearCaso(quizzEjemplo, "fungicida", "Manchas blancas polvorientas (Oídio)", "oidio.jpeg", "¡Bien! El fungicida eliminó el Oídio.", "Son manchas blancas superficiales, típicas de hongos.");
            crearCaso(quizzEjemplo, "fungicida", "Manchas marrones con aureola amarilla (Mildiu)", "mildiu.jpg", "¡Correcto! Has controlado el brote de Mildiu.", "Estas manchas necróticas suelen ser causadas por hongos.");
            crearCaso(quizzEjemplo, "fungicida", "Fruta podrida con moho gris (Botrytis)", "botrytis.jpeg", "¡Salvado! La podredumbre se ha detenido.", "El moho grisáceo es inconfundiblemente un hongo.");
            
            crearCaso(quizzEjemplo, "insecticida", "Colonia de bichitos negros (Pulgón)", "pulgon.jpg", "¡Excelente! El pulgón ha sido eliminado.", "Fíjate bien, son pequeños insectos chupadores.");
            crearCaso(quizzEjemplo, "insecticida", "Hojas comidas con agujeros (Orugas)", "oruga.jpg", "¡Bien hecho! Las orugas dejarán de comerse la cosecha.", "Esos mordiscos grandes son de un insecto masticador.");
            crearCaso(quizzEjemplo, "insecticida", "Puntos blancos diminutos y telarañas (Araña Roja)", "arana_roja.jpg", "¡Correcto! El acaricida/insecticida funcionó.", "Las telarañas finas indican presencia de ácaros o insectos.");
            
            crearCaso(quizzEjemplo, "agua", "Tierra agrietada y planta mustia", "sequia_tierra.jpg", "¡La planta revive! Necesitaba agua urgentemente.", "El suelo está seco y la planta decaída. No es una enfermedad.");
            crearCaso(quizzEjemplo, "agua", "Puntas de las hojas secas y crujientes", "stress_hidrico.jpg", "¡Bien! Has corregido el estrés hídrico.", "Cuando falta agua, las puntas son las primeras en secarse.");
            
            crearCaso(quizzEjemplo, "abono", "Hojas amarillas, nervios verdes (Clorosis Férrica)", "clorosis.jpg", "¡El hierro ha devuelto el color verde!", "El patrón amarillo indica carencia nutricional, no plaga.");
            crearCaso(quizzEjemplo, "abono", "Planta enana con color púrpura (Falta de Fósforo)", "falta_fosforo.jpg", "¡El aporte de Fósforo estimulará el crecimiento!", "El color morado y poco crecimiento es falta de nutrientes.");

            System.out.println("-> 10 casos clínicos creados y asociados al Quizz.");
        }
    }

    // Método auxiliar para no repetir tanto código
    private void crearCaso(QuizzVO quizz, String solucion, String texto, String img, String msjExito, String pista) {
        CasoQuizzVO caso = new CasoQuizzVO();
        caso.setQuizz(quizz);
        caso.setSolucion(solucion);
        caso.setTexto(texto);
        caso.setImg(img); // Guardamos solo el nombre, la ruta la pone el HTML
        caso.setMensajeExito(msjExito);
        caso.setPistaError(pista);
        casoQuizzService.guardar(caso);
    }
}