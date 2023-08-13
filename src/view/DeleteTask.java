package view;

import controller.TaskController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static controller.ConsoleUtils.clearConsole;
import static controller.TaskController.handleException;
import static controller.TaskController.updateFileContent;
import static view.UserInputUtils.println;

public class DeleteTask {
    public static void deleteTask(int taskId) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TaskController.filePath));
            StringBuilder content = new StringBuilder();
            String line;
            int lineNumber = 1;
            boolean foundTask = false;
            while ((line = reader.readLine()) != null) {
                if (lineNumber > 1) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        String id = parts[0];
                        if (taskId == Integer.parseInt(id)) {
                            foundTask = true;
                            clearConsole();
                            println("A tarefa " + "(" + parts[1].replace("\"", "") + ")" + " foi excluída");
                        } else {
                            content.append(line).append(System.lineSeparator());
                        }
                    }
                } else {
                    content.append(line).append(System.lineSeparator());
                    lineNumber++;
                }
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
}
