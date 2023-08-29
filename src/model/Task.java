package model;

public class Task {
    private int id;
    private String name;
    private String description;
    private String dateOfCompletion;
    private int priorityLevel;
    private String category;
    private String status;

    public Task(int id, String name, String description, String dateOfCompletion, int priorityLevel, String category, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateOfCompletion = dateOfCompletion;
        this.priorityLevel = priorityLevel;
        this.category = category;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(String dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toCsvString() {
        return id + "," + name + "," + description + "," + dateOfCompletion + "," +
                priorityLevel + "," + category + "," + status;
    }

}
