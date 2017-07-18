package aavn.dating.entity;

import javax.persistence.*;

/**
 * Created by dhvy on 6/7/2017.
 */
@Entity
public class Location {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int locationId;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String address;

    @Basic(optional = true)
    private String description;

    public Location(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
