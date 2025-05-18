package pt.mleiria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class GlobalPositionIntEntity {


    private String mavpackettype;
    @Id
    private long time_boot_ms;
    private int lat;
    private int lon;
    private int alt;
    private int relative_alt;
    private short vx;
    private short vy;
    private short vz;
    private int hdg;

    // Constructors
    public GlobalPositionIntEntity() {
    }

    public GlobalPositionIntEntity(String mavpackettype, long time_boot_ms, int lat, int lon, int alt, int relative_alt, short vx, short vy, short vz, int hdg) {
        this.mavpackettype = mavpackettype;
        this.time_boot_ms = time_boot_ms;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.relative_alt = relative_alt;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.hdg = hdg;
    }

    // Getters and Setters

    public String getMavpackettype() {
        return mavpackettype;
    }

    public void setMavpackettype(String mavpackettype) {
        this.mavpackettype = mavpackettype;
    }

    public long getTime_boot_ms() {
        return time_boot_ms;
    }

    public void setTime_boot_ms(long time_boot_ms) {
        this.time_boot_ms = time_boot_ms;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getRelative_alt() {
        return relative_alt;
    }

    public void setRelative_alt(int relative_alt) {
        this.relative_alt = relative_alt;
    }

    public short getVx() {
        return vx;
    }

    public void setVx(short vx) {
        this.vx = vx;
    }

    public short getVy() {
        return vy;
    }

    public void setVy(short vy) {
        this.vy = vy;
    }

    public short getVz() {
        return vz;
    }

    public void setVz(short vz) {
        this.vz = vz;
    }

    public int getHdg() {
        return hdg;
    }

    public void setHdg(int hdg) {
        this.hdg = hdg;
    }

    @Override
    public String toString() {
        return "GlobalPositionIntEntity{" +
                "mavpackettype='" + mavpackettype + '\'' +
                ", time_boot_ms=" + time_boot_ms +
                ", lat=" + lat +
                ", lon=" + lon +
                ", alt=" + alt +
                ", relative_alt=" + relative_alt +
                ", vx=" + vx +
                ", vy=" + vy +
                ", vz=" + vz +
                ", hdg=" + hdg +
                '}';
    }
}