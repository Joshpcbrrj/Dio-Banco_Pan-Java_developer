package dio.web.api.model;

//Atributos
public class Usuario {
    private Integer id;
    private String login;
    private String password;

    //Construtor vazio
    public Usuario(){}

    //Construtor de login
    public Usuario(String login, String password){
        this.login = login;
        this.password = password;
    }

    //Getters e setters ID
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //Getters e setters Login
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    //Getters e setters Password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //Método toString
    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
