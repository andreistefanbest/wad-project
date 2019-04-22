
package wad.phone.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

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
    private Integer ramCapacity;

    @Column(name = "ram_type")
    private String ramType;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "clock_speed")
    private Double clockSpeed;

    @Column(name = "bluetooth")
    private Boolean bluetooth;

    @Column(name = "network_support")
    private String networkSupport;

    @Column(name = "storage_capacity")
    private Integer storageCapacity;

    public Specs() {
    }

    public Specs(Integer id) {
        this.id = id;
    }

    public Specs(Integer id, Integer ramCapacity, String ramType, String cpu, Double clockSpeed, Boolean bluetooth, String networkSupport, Integer storageCapacity) {
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

    public Integer getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(Integer ramCapacity) {
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

    public Double getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(Double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(Boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getNetworkSupport() {
        return networkSupport;
    }

    public void setNetworkSupport(String networkSupport) {
        this.networkSupport = networkSupport;
    }

    public Integer getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(Integer storageCapacity) {
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
