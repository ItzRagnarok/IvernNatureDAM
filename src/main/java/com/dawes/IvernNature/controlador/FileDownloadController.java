//package com.dawes.IvernNature.controlador;
//
//import com.dawes.IvernNature.modelo.ContenidoEducativoVO;
//import com.dawes.IvernNature.servicio.interfaces.ContenidoEducativoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Controller
//public class FileDownloadController {
//
//    @Autowired
//    private ContenidoEducativoService contenidoEducativoService;
//
//    @GetMapping("/download/{id}")
//    public ResponseEntity<InputStreamResource> descargarArchivo(@PathVariable("id") int id) throws IOException {
//        ContenidoEducativoVO contenido = contenidoEducativoService.findById(id).orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
//
//        FileInputStream fis = new FileInputStream(contenido.getUrl());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "attachment; filename=" + contenido.getTitulo());
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.parseMediaType(contenido.getTipoArchivo()))
//                .body(new InputStreamResource(fis));
//    }
//}
