package entity;

import util.IdGenerator;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 8479482587972428727L;

    private int id;
    private String avatarFullname;
    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int idRole;

    private User(UserBuilder builder) {
        id = IdGenerator.getUserId();
        login = builder.login;
        firstName = builder.firstName;
        lastName = builder.lastName;
        password = builder.password;
        email = builder.email;
        idRole = builder.idRole;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setAvatarFullname(String avatarFullname) {
        this.avatarFullname = avatarFullname;
    }

    public String getAvatarFullname() {
        return avatarFullname;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static class UserBuilder {

        private String login;
        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private int idRole;

        public UserBuilder setIdRole(int idRole){
            this.idRole = idRole;
            return this;
        }

        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, password, email);
    }
}
