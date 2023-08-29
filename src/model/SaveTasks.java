package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SaveTasks {

    private static final String filePath = "src/ToDo.csv";
    private static final String TaskDefaultHeader = "id,name,description,dateOfCompletion,priorityLevel,category,status";

    public static void save(Task task) {
        try {
            if (!fileExists()) {
                createNewFile();
            }

            saveTaskToFile(task);
            System.out.println("A task foi gravada com sucesso em ToDo.csv");
        } catch (IOException e) {
            handleException(e);
        }
    }

    private static boolean fileExists() {
        return new File(SaveTasks.filePath).exists();
    }

    private static void createNewFile() throws IOException {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(SaveTasks.filePath, true))) {
            printWriter.println(TaskDefaultHeader);
        }
    }

    private static void saveTaskToFile(Task task) throws IOException {
        List<Task> tasks = loadTasksFromFile();
        tasks.add(task);

        tasks.sort(Comparator.comparingInt(Task::getPriorityLevel).reversed());

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(filePath))) {
            printWriter.println(TaskDefaultHeader);
            for (Task t : tasks) {
                printWriter.println(t.toCsvString());
            }
        }
    }

    public static List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 7) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String description = parts[2];
                        String dateOfCompletion = parts[3];
                        int priority = Integer.parseInt(parts[4]);
                        String category = parts[5];
                        String status = parts[6];

                        Task task = new Task(id, name, description, dateOfCompletion, priority, category, status);
                        tasks.add(task);
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            handleException(e);
        }

        return tasks;
    }

    private static void handleException(Exception e) {
        System.out.println("Ocorreu um erro ao salvar a tarefa: " + e.getMessage());
    }
}


