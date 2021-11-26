package te.project.aidd;

public class User {
    int id;
    String name;
    int tableId;

    public User(int id, String name, int tableId) {
        this.id = id;
        this.name = name;
        this.tableId=tableId;

    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
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

    public int getTableId() {

        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
}
