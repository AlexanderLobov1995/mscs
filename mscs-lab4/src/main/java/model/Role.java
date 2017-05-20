package model;

import java.util.LinkedList;
import java.util.List;

public class Role {
    private String name;
    private List<String> levels;

    public Role(String name, List<String> levels) {
        this.name = name;
        this.levels = new LinkedList<String>(levels);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }
}
