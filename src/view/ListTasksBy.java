package view;

import static controller.TaskController.*;
import static view.ListTasks.listTasks;
import static view.UserInputUtils.getStringInput;

public class ListTasksBy {
    public static void listTasksBy() {
        listTasks();
        String criteria = getStringInput("Você deseja listar as tarefas por categoria, prioridade ou status? ");
        switch (criteria.toLowerCase().trim()) {
            case "categoria" -> listTasksByCategory();
            case "prioridade" -> listTasksByPriority();
            case "status" -> listTasksByStatus();
            default -> System.out.println("Critério inválido.");
        }
    }
}
