package com.dawes.IvernNature.servicio.implementacion;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FileTypeUtils {

    private static final Map<String, String> FILE_ICON_MAP = new HashMap<>();
    private static final Map<String, String> FILE_TYPE_MAP = new HashMap<>();
    
    static {
        FILE_ICON_MAP.put("application/pdf", "fa-file-pdf");
        FILE_ICON_MAP.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "fa-file-word");
        FILE_ICON_MAP.put("image/jpeg", "fa-file-image");
        // Agrega otros tipos de archivo y sus iconos aquí
        
        FILE_TYPE_MAP.put("application/pdf", "PDF");
        FILE_TYPE_MAP.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "Word");
        FILE_TYPE_MAP.put("image/jpeg", "Imagen");
        // Agrega otros tipos de archivo y sus nombres simples aquí
    }
    
    public String getIconClass(String tipoArchivo) {
        return FILE_ICON_MAP.getOrDefault(tipoArchivo, "fa-file");
    }
    
    public String getSimpleType(String tipoArchivo) {
        return FILE_TYPE_MAP.getOrDefault(tipoArchivo, "Archivo");
    }
}
