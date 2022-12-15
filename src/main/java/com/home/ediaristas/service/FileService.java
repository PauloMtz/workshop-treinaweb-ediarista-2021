package com.home.ediaristas.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    
    private final Path pastaUpload = Paths.get("uploads");

    // método para gravar o arquivo
    public String gravar(MultipartFile file) throws IOException {
        // se a pasta não existir, será criada
        if(!Files.exists(pastaUpload)) {
            Files.createDirectories(pastaUpload);
        }

        // antes de salvar, cria um nome para ele
        var originalFilename = file.getOriginalFilename();

        // quebra o nome do arquivo no ponto, ficando na posição zero
        // o nome do arquivo e na posição um a extensão
        // nesse caso, pega a posição 1, que é a extensão
        var extensao = originalFilename.split("\\.")[1];

        // cria um nome aleatório
        var filename = UUID.randomUUID().toString() + "." + extensao;

        // grava o arquivo na pasta
        Files.copy(file.getInputStream(), pastaUpload.resolve(filename));

        // retorna o nome do arquivo criado
        return filename;
    }

    // método para carregar
    public Resource carregar(String filename) throws MalformedURLException {
        var filePath = pastaUpload.resolve(filename);
        return new UrlResource(filePath.toUri());
    }
}
