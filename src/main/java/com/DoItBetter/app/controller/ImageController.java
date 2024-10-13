package com.DoItBetter.app.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Paths;

@RestController
public class ImageController {

  private final ResourceLoader resourceLoader;

  public ImageController(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }

  @GetMapping("/uploads/{filename:.+}")
  public ResponseEntity<Resource> getImage(@PathVariable String filename) {
    try {
      // Cambia esta ruta a donde se guardan tus imágenes
      File file = Paths.get("uploads/" + filename).toFile();
      Resource resource = resourceLoader.getResource("file:" + file.getAbsolutePath());

      if (!resource.exists()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      }

      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
          .body(resource);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
