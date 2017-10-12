package se.polisen.hackathon.dto;

public class KeyValueDto {
    private int id;
    private String title;

    public KeyValueDto(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public KeyValueDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
