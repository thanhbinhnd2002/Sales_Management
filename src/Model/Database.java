package Model;

public class Database {
    // attributes
    private String nameDatabase;
    private String port;
    private String user;
    private String pass;

    // contructor
    public Database() {
        this.nameDatabase = "";
        this.port = "";
        this.user = "";
        this.pass = "";
    }

    public Database(String nameDatabase, String port, String user, String pass) {
        this.nameDatabase = nameDatabase;
        this.port = port;
        this.user = user;
        this.pass = pass;
    }

    public String getUrl() {
        return "jdbc:mysql://localhost:" + port + "/" + nameDatabase + "?useUnicode=true&charEncoding=utf8";
    }


    public String getNameDatabase() {
        return nameDatabase;
    }

    public void setNameDatabase(String nameDatabase) {
        this.nameDatabase = nameDatabase;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
