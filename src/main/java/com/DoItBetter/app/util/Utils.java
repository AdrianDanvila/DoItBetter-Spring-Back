package com.DoItBetter.app.util;

import java.io.File;

public class Utils {

  private Utils() {
  }

  private static final String UPLOAD_DIRECTORY = "uploads/";

  public static void deleteImagesFromUpload(long id) {
    String numeroStr = id + "_";
    File folder = new File(UPLOAD_DIRECTORY);

    if (folder.exists() && folder.isDirectory()) {
      File[] archivos = folder.listFiles();

      if (archivos != null) {
        for (File archivo : archivos) {
          if (archivo.isFile()) {
            String nombreArchivo = archivo.getName();
            if (nombreArchivo.startsWith(numeroStr)) {
              if (archivo.delete()) {
                System.out.println("Archivo eliminado: " + nombreArchivo);
              } else {
                System.out.println("No se pudo eliminar: " + nombreArchivo);
              }
            }
          }
        }
      } else {
        System.out.println("No se pudo obtener la lista de archivos en el directorio.");
      }
    } else {
      System.out.println("El directorio especificado no existe o no es un directorio.");
    }
  }
}