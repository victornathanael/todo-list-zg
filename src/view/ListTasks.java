package view;

import controller.TaskController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static view.UserInputUtils.println;

public class ListTasks {
    public static void listTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(TaskController.FILE_PATH))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {

                        String name = parts[1];
                        String description = parts[2];
                        String dateOfCompletion = parts[3];
                        String priorityLevel = parts[4];
                        String category = parts[5];
                        String status = parts[6];

                        println("Nome: " + name);
                        println("Descrição: " + description);
                        println("Data de término: " + dateOfCompletion);
                        println("Prioridade: " + priorityLevel);
                        println("Categoria: " + category);
                        println("Status: " + status);
                        println("==============================");
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            TaskController.handleException(e);
        }
    }
}
