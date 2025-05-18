package pt.mleiria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LocalPositionNedEntity {
    @Id
    private long time_boot_ms;
    private double x;
    private double y;
    private double z;
    private double vx;
    private double vy;
    private double vz;

    // Constructors
    public LocalPositionNedEntity() {
    }

    public LocalPositionNedEntity(long time_boot_ms, double x, double y, double z, double vx, double vy, double vz) {
        this.time_boot_ms = time_boot_ms;
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    // Getters and Setters
    public long getTime_boot_ms() {
        return time_boot_ms;
    }

    public void setTime_boot_ms(long time_boot_ms) {
        this.time_boot_ms = time_boot_ms;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVz() {
        return vz;
    }

    public void setVz(double vz) {
        this.vz = vz;
    }

    @Override
    public String toString() {
        return "LocalPositionNedEntity{" +
                "time_boot_ms=" + time_boot_ms +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", vx=" + vx +
                ", vy=" + vy +
                ", vz=" + vz +
                '}';
    }
}
