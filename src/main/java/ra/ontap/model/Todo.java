package ra.ontap.model;

public class Todo {
    private  Integer id;
    private String content;
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public Boolean getStatus() {
        return status;
    }

    public Todo(Integer id, String content, Boolean status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Todo() {
    }
}
