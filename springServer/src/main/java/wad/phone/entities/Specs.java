
package wad.phone.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Andrei Stefan
 * @since Apr 8, 2019
 */
@Entity
@Table(name = "specs")
public class Specs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ram_capacity")
    private int ramCapacity;

    @Column(name = "ram_type")
    private String ramType;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "clock_speed")
    private double clockSpeed;

    @Column(name = "bluetooth")
    private boolean bluetooth;

    @Column(name = "network_support")
    private String networkSupport;

    @Column(name = "storage_capacity")
    private int storageCapacity;

    public Specs() {
    }

    public Specs(Integer id) {
        this.id = id;
    }

    public Specs(Integer id, int ramCapacity, String ramType, String cpu, double clockSpeed, boolean bluetooth, String networkSupport, int storageCapacity) {
        this.id = id;
        this.ramCapacity = ramCapacity;
        this.ramType = ramType;
        this.cpu = cpu;
        this.clockSpeed = clockSpeed;
        this.bluetooth = bluetooth;
        this.networkSupport = networkSupport;
        this.storageCapacity = storageCapacity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public boolean getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getNetworkSupport() {
        return networkSupport;
    }

    public void setNetworkSupport(String networkSupport) {
        this.networkSupport = networkSupport;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(int storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specs)) {
            return false;
        }
        Specs other = (Specs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wad.phone.entities.Specs[ id=" + id + " ]";
    }

}
