package aavn.dating.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by dhvy on 6/7/2017.
 */
@Entity
@Table( uniqueConstraints={
            @UniqueConstraint(columnNames = {"user1", "user2"})
})

public class Appointment {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "user1")
    private User user1;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "user2")
    private User user2;

    @Basic(optional = false)
    private String  status;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;

    public Appointment(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
