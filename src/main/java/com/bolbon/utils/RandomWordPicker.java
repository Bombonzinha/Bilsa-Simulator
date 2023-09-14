package com.bolbon.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.hibernate.internal.build.AllowSysOut;

public class RandomWordPicker {

    public static String pickRandomWord(String filePath) {
        String randomWord = null;
        int lineCount = 0;
        Random random = new Random();
        int totalLines = countLines(filePath); // Función para contar las líneas del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null && randomWord == null) {
                lineCount++;
                if (random.nextInt(totalLines - lineCount + 1) == 0) {
                    // Reemplaza la palabra aleatoria con probabilidad 1/(totalLines - lineCount + 1)
                    randomWord = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return randomWord;
    }

    public static int countLines(String filePath) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        String filePath = "D:\\Eclipse\\sts-4.18.1.RELEASE\\XXX\\nombres\\words.txt";
        String randomWord = pickRandomWord(filePath);
        System.out.println("Palabra aleatoria seleccionada: " + randomWord);
    }
}
