package model;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String name;
    private List<Role> roles;

    public User(String name, List<Role> roles) {
        this.name = name;
        this.roles = new LinkedList<Role>(roles);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
