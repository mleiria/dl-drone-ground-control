package pt.mleiria.entity;

import jakarta.persistence.*;

@Entity
public class MissionNameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private Long id;
    private String missionName;
    private Boolean isActive;
    // Constructors
    public MissionNameEntity() {
    }
    public MissionNameEntity(String missionName, Boolean isActive) {
        this.missionName = missionName;
        this.isActive = isActive;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
