package com.connectMentor.auth.services;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileService {

    /**
     * Adiciona conteúdo ao final de um arquivo existente.
     * Se o arquivo não existir, ele será criado.
     *
     * @param filePath Caminho do arquivo onde o conteúdo será adicionado.
     * @param content  Conteúdo a ser adicionado ao arquivo.
     */
    public void addToFile(String filePath, String content) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // O segundo argumento 'true' indica que o conteúdo
                                                                   // será adicionado ao final do arquivo.
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Salva uma imagem (ou qualquer arquivo binário) em um local especificado.
     *
     * @param fileName  Nome do arquivo a ser criado.
     * @param imageFile Conteúdo do arquivo em forma de array de bytes.
     * @param filePath  Caminho do diretório onde o arquivo será salvo.
     * @return Retorna o nome do arquivo.
     */
    public String addImage(String fileName, byte[] imageFile, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath + File.separator + fileName); // Usar File.separator
                                                                                               // para garantir
                                                                                               // compatibilidade entre
                                                                                               // sistemas operacionais.
            fos.write(imageFile);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * Atualiza o conteúdo de um arquivo, substituindo todo o conteúdo existente
     * pelo novo conteúdo fornecido.
     *
     * @param filePath   Caminho do arquivo a ser atualizado.
     * @param newContent Novo conteúdo a ser escrito no arquivo.
     */
    public void updateFile(String filePath, String newContent) {
        try (FileWriter writer = new FileWriter(filePath, false)) { // O segundo argumento 'false' indica que o arquivo
                                                                    // será sobrescrito.
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deleta um arquivo no caminho especificado.
     *
     * @param filePath Caminho do arquivo a ser deletado.
     */
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    /**
     * Cria um novo arquivo no caminho especificado.
     * Se o arquivo já existir, uma mensagem será exibida sem criar um novo arquivo.
     *
     * @param filePath Caminho do arquivo a ser criado.
     */
    public void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
