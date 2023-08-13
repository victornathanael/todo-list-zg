package controller;

import view.UserInputUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static controller.ConsoleUtils.clearConsole;
import static view.UserInputUtils.println;

public class TaskController {
    public static final String filePath = "src/ToDo.csv";

    public static void setStatusOfTask(int taskId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder content = new StringBuilder();
            String line;
            int lineNumber = 1;
            boolean foundTask = false;
            Scanner scanner = new Scanner(System.in);

            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        String id = parts[0];
                        if (taskId == Integer.parseInt(id)) {
                            foundTask = true;
                            System.out.print("Qual o novo status da tarefa? ");
                            String newStatus = scanner.nextLine();
                            if (updateTaskStatus(parts, taskId, newStatus)) {
                                println("Status da tarefa alterado com sucesso.");
                            } else {
                                println("Erro ao atualizar o status da tarefa.");
                            }
                        }
                        content.append(String.join(",", parts)).append(System.lineSeparator());
                    }
                } else {
                    content.append(line).append(System.lineSeparator());
                }
                lineNumber++;
            }

            if (!foundTask) {
                clearConsole();
                println("O id que você digitou não existe.");
            } else {
                updateFileContent(content);
            }

            reader.close();
        } catch (IOException e) {
            handleException(e);
        }
    }

    public static void handleException(Exception e) {
        println("Ocorreu um erro: " + e.getMessage());
    }

    private static boolean updateTaskStatus(String[] parts, int taskId, String newStatus) {
        if (taskId == Integer.parseInt(parts[0])) {
            parts[6] = "\"" + newStatus + "\"";
            return true;
        }
        return false;
    }

    public static void updateFileContent(StringBuilder content) throws IOException {
        FileWriter writer = new FileWriter(TaskController.filePath);
        writer.write(content.toString());
        writer.close();
    }

    public static void listTasksByCategory() {
        String category = UserInputUtils.getStringInput("Digite a categoria: ");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/ToDo.csv"))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6 && parts[5].replace("\"", "").equalsIgnoreCase(category)) {
                        displayTaskInfo(parts);
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    public static void listTasksByPriority() {
        int priority = UserInputUtils.getIntInput("Digite a prioridade: ");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/ToDo.csv"))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6 && Integer.parseInt(parts[4].replace("\"", "")) == priority) {
                        displayTaskInfo(parts);
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    public static void listTasksByStatus() {
        String status = UserInputUtils.getStringInput("Digite o status: ");
        try (BufferedReader reader = new BufferedReader(new FileReader("src/ToDo.csv"))) {
            String line;
            int lineNumber = 1;
            System.out.printf("%-5s %-30s %-20s %-15s %-10s %-20s %-15s%n",
                    "ID", "Nome", "Descrição", "Data de Término", "Prioridade", "Categoria", "Status");
            System.out.println("===========================================================================");
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6 && parts[6].replace("\"", "").equalsIgnoreCase(status)) {
                        displayTaskInfo(parts);
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void displayTaskInfo(String[] parts) {
        clearConsole();
        String id = parts[0];
        String name = parts[1];
        String description = parts[2];
        String dateOfCompletion = parts[3];
        String priorityLevel = parts[4];
        String category = parts[5];
        String status = parts[6];

        String formattedTask = String.format("[id: %s, name: %s, description: %s, dateOfCompletion: %s, priorityLevel: %s, category: %s, status: %s]",
                id, name, description, dateOfCompletion, priorityLevel, category, status);

        System.out.println(formattedTask);
        System.out.println("==========================================================================================================================");
    }

}
