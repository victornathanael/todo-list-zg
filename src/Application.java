import controller.TaskController;
import view.DeleteTask;
import view.ListTasks;
import view.ListTasksBy;
import model.Task;
import view.UserInputUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static controller.ConsoleUtils.clearConsole;
import static model.SaveTasks.save;
import static view.TaskMenu.taskMenu;
import static view.UserInputUtils.*;

public class Application {
    private static final String FILE_PATH = "src/ToDo.csv";
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MENU_CREATE_TASK = 1;
    private static final int MENU_LIST_TASKS = 2;
    private static final int MENU_LIST_TASKS_BY = 3;
    private static final int MENU_SET_STATUS = 4;
    private static final int MENU_DELETE_TASK = 5;
    private static final int MENU_EXIT = 6;

    public static void main(String[] args) {
        while (true) {
            taskMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case MENU_CREATE_TASK -> createTask();
                case MENU_LIST_TASKS -> listTasks();
                case MENU_LIST_TASKS_BY -> listTasksBy();
                case MENU_SET_STATUS -> setStatusOfTask();
                case MENU_DELETE_TASK -> deleteTask();
                case MENU_EXIT -> exit();
                default -> {
                    clearConsole();
                    println("Insira um número válido");
                }
            }

            if (choice == 6) {
                break;
            }
        }
    }

    private static void createTask() {
        clearConsole();
        String name = getStringInput("Digite o nome da sua tarefa: ");

        String description = getStringInput("Digite a descrição: ");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        String dateOfCompletion = null;

        while (date == null) {
            dateOfCompletion = getStringInput("Digite a data de término (formato dd/MM/yyyy): ");
            try {
                date = dateFormat.parse(dateOfCompletion);
            } catch (ParseException e) {
                println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy.");
            }
        }
        int priorityLevel = getIntInput("Digite o nível de prioridade (1 - 5): ");
        if (priorityLevel >= 5) {
            priorityLevel = 5;
        } else if (priorityLevel <= 1) {
            priorityLevel = 1;
        }

        getStringInput(""); // Limpar o buffer

        String category = getStringInput("Digite a categoria: ");

        String status = null;
        String statusTodoDoingOrDone = null;
        while (statusTodoDoingOrDone == null) {
            status = getStringInput("Digite o nome status (todo, doing ou done): ");
            if (status.equalsIgnoreCase("todo") || status.equalsIgnoreCase("doing") || status.equalsIgnoreCase("done")) {
                statusTodoDoingOrDone = "";
            } else {
                println("Digite um status válido.");
            }
        }
        clearConsole();
        int id = createId();
        Task task = new Task(id, name, description, dateOfCompletion, priorityLevel, category, status);

        println("A Tarefa foi criada com sucesso!");
        save(task);
    }

    private static void listTasks() {
        clearConsole();
        ListTasks.listTasks();
        countTasksByStatus();
        File file = new File("src/ToDo.csv");
        if (!file.exists()) {
            println("Crie tarefas antes de tentar listar elas.");
        }
    }

    private static void listTasksBy() {
        clearConsole();
        ListTasksBy.listTasksBy();
    }

    private static void setStatusOfTask() {
        clearConsole();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/ToDo.csv"))) {
            String line;
            int lineNumber = 1;

            System.out.printf("%-5s %-30s %-15s%n", "ID", "Nome", "Status");
            System.out.println("===============================================");
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {

                        String id = parts[0];
                        String name = parts[1];
                        String status = parts[6];

                        System.out.printf("%-5s %-30s %-15s%n", id, name, status);
                        System.out.println("===============================================");
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            clearConsole();
            println("Ocorreu um erro: " + e.getMessage());
        }

        int taskId = UserInputUtils.getIntInput("Digite o ID da tarefa: ");
        if (taskId > 0) {
            TaskController.setStatusOfTask(taskId);
        } else {
            System.out.println("ID inválido.");
        }
    }

    private static void deleteTask() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/ToDo.csv"))) {
            clearConsole();
            String line;
            int lineNumber = 1;

            System.out.printf("%-5s %-15s%n", "ID", "Nome");
            System.out.println("==================================");


            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {

                        String id = parts[0];
                        String name = parts[1];

                        System.out.printf("%-5s %-15s%n", id, name);
                        System.out.println("==================================");
                    }
                }
                lineNumber++;
            }


            int taskId = UserInputUtils.getIntInput("Digite o ID da tarefa a ser excluída: ");
            if (taskId > 0) {
                DeleteTask.deleteTask(taskId);
            } else {
                System.out.println("ID inválido.");
            }


        } catch (IOException e) {
            clearConsole();
            println("Ocorreu um erro: " + e.getMessage());
        }
    }

    private static void exit() {
        clearConsole();
        println("Saindo...");

    }

    public static int createId() {
        int currentId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/currentId.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                currentId = Integer.parseInt(line);
            }
        } catch (IOException e) {
            println("Ocorreu um erro: " + e.getMessage());
        }

        int nextId = currentId + 1;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/currentId.txt"))) {
            writer.write(String.valueOf(nextId));
        } catch (IOException e) {
            println("Ocorreu um erro: " + e.getMessage());
        }

        return nextId;
    }

    public static void countTasksByStatus() {
        int todoCount = 0;
        int doingCount = 0;
        int doneCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        String status = parts[6].replace("\"", "");
                        if (status.equalsIgnoreCase("todo")) {
                            todoCount++;
                        } else if (status.equalsIgnoreCase("doing")) {
                            doingCount++;
                        } else if (status.equalsIgnoreCase("done")) {
                            doneCount++;
                        }
                    }
                }
                lineNumber++;
            }

            System.out.println("Tasks ToDo: " + todoCount);
            System.out.println("Tasks Doing: " + doingCount);
            System.out.println("Tasks Done: " + doneCount);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


