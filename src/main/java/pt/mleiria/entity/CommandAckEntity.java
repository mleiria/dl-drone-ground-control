package pt.mleiria.entity;

import jakarta.persistence.*;

@Entity
public class CommandAckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
    @SequenceGenerator(name = "idgenerator", initialValue = 1000)
    private Long id;

    private Long missionNameId;

    private int command;
    private int result;
    private int progress;
    private int resultParam2;
    private int targetSystem;
    private int targetComponent;

    // Getters and Setters
    public Long getMissionNameId() {
        return missionNameId;
    }

    public void setMissionNameId(Long missionNameId) {
        this.missionNameId = missionNameId;
    }

    public Long getId() {
        return id;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getResultParam2() {
        return resultParam2;
    }

    public void setResultParam2(int resultParam2) {
        this.resultParam2 = resultParam2;
    }

    public int getTargetSystem() {
        return targetSystem;
    }

    public void setTargetSystem(int targetSystem) {
        this.targetSystem = targetSystem;
    }

    public int getTargetComponent() {
        return targetComponent;
    }

    public void setTargetComponent(int targetComponent) {
        this.targetComponent = targetComponent;
    }




    @Override
    public String toString() {
        return "CommandAckEntity{" +
                "command=" + command +
                ", result=" + result +
                ", progress=" + progress +
                ", resultParam2=" + resultParam2 +
                ", targetSystem=" + targetSystem +
                ", targetComponent=" + targetComponent +
                '}';
    }

}
