package pt.mleiria.entity;

import jakarta.persistence.*;

@Entity
public class CommandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private Long id;
    private String name;
    private String defaultAction;
    private String options;
    private String description;

    // Constructors
    public CommandEntity() {
    }
    public CommandEntity(String name, String defaultAction, String options, final String description) {
        this.name = name;
        this.defaultAction = defaultAction;
        this.options = options;
        this.description = description;
    }
    public CommandEntity(String name, String defaultAction, String description) {
        this.name = name;
        this.defaultAction = defaultAction;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDefaultAction() {
        return defaultAction;
    }
    public void setDefaultAction(String defaultAction) {
        this.defaultAction = defaultAction;
    }
    public String getOptions() {
        return options;
    }
    public void setOptions(String options) {
        this.options = options;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CommandEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", defaultAction='" + defaultAction + '\'' +
                ", options='" + options + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
