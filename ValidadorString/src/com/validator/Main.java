package com.validator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<Character, Character> gramaticaMap = new HashMap<>();
        gramaticaMap.put('<', '>');
        gramaticaMap.put('[', ']');
        gramaticaMap.put('{', '}');
        gramaticaMap.put('(', ')');

        try {
            String valoresFinais = "";
            File arq = new File("prog.txt");
            Scanner leitor = new Scanner(arq);
            while (leitor.hasNextLine()) {
                String entrada = leitor.nextLine().trim();
                valoresFinais += entrada + " - ";
                boolean haErro = false;
                Stack<Character> stack = new Stack<>();

                for (int i = 0; i < entrada.length(); i++) {

                    if (gramaticaMap.containsKey(entrada.charAt(i))) {
                        stack.push(entrada.charAt(i));
                    } else {
                        if (stack.isEmpty()) {
                            valoresFinais += "Inválido";
                            haErro = true;
                            break;
                        }
                        char lastBracket = stack.pop();

                        if (gramaticaMap.get(lastBracket) != entrada.charAt(i)) {
                            valoresFinais += "Inválido";
                            haErro = true;
                            break;
                        }
                    }
                }
                if (!haErro) {
                    if (stack.isEmpty()) {
                        valoresFinais += "OK";
                    } else {
                        valoresFinais += "Inválido";
                    }
                }
                valoresFinais += "\r\n";
            }
            Path caminho = Paths.get("prog-check.txt");
            List<String> listaValoresFinal = Arrays.asList(valoresFinais);
            Files.write(caminho, listaValoresFinal, StandardCharsets.UTF_8);
            leitor.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ocorreu um erro!");
            e.printStackTrace();
        }
    }
}
