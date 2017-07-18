package aavn.dating.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

/**
 * Created by dhvy on 6/7/2017.
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, scope = User.class)
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(unique = true)
    @Basic(optional = true)
    private String email;

    @Basic(optional = true)
    private String password;

    @Basic(optional = true)
    private String confirmpassword;

    @Basic(optional = true)
    @Column(columnDefinition="nvarchar(30)")
    private String  name;

    // must fix here, use String is silly.
    @Basic(optional = true)
    private String gender;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Basic(optional = true)
    private String address;

    @Basic(optional = true)
    private String profession;

    @Basic(optional = true)
    private int height;

    @Basic(optional = true)
    private int weight;

    @Basic(optional = true)
    private String avaImg;

    @Basic(optional = true)
    private String coverImg;

    @Basic(optional = true)
    @Column( columnDefinition = "BLOB")
    private byte[] freeTime;

    @Basic(optional = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActive;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "UserId_test")
    private Collection<User_Answer> list_ans;

    public User() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAvaImg() {
        return avaImg;
    }

    public void setAvaImg(String avaImg) {
        this.avaImg = avaImg;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public byte[] getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(byte[] freeTime) {
        this.freeTime = freeTime;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getConfirmPassword() {
        return confirmpassword;
    }

    public void setConfirmPassword(String confirm_password) {
        this.confirmpassword = confirm_password;
    }

    public Collection<User_Answer> getList_ans() {
        return list_ans;
    }

    public void setList_ans(Collection<User_Answer> list_ans) {
        this.list_ans = list_ans;
    }

    public void updateUser( User user ) {
        if ( user.getEmail() != null )
            this.email = user.getEmail();
        if ( user.getPassword() != null )
            this.password = user.getPassword();
        if ( user.getName() != null )
            this.name = user.getName();
        if ( user.getGender() != null )
            this.gender = user.getGender();
        if ( user.getBirthday() != null )
            this.birthday = user.getBirthday();
        if ( user.getAddress() != null )
            this.address = user.getAddress();
        if ( user.getProfession() != null )
            this.profession = user.getProfession();
        if ( user.getHeight() > 0 )
            this.height = user.getHeight();
        if ( user.getWeight() > 0 )
            this.weight = user.getWeight();
        if ( user.getAvaImg() != null )
            this.avaImg = user.getAvaImg();
        if ( user.getCoverImg() != null )
            this.coverImg = user.getCoverImg();
        if ( user.getFreeTime() != null )
            this.freeTime = user.getFreeTime();
        if ( user.getLastActive() != null )
            this.lastActive = lastActive;
    }

    public void setDefault() {
        if ( this.getName() == null )
            this.name = "My Account";
        if ( this.getAddress() == null )
            this.address = "My Address";
        if ( this.getAvaImg() == null )
            this.avaImg = "assets/images/avatar.png";
        if ( this.getCoverImg() != null )
            this.coverImg = "default";

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", profession='" + profession + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", avaImg='" + avaImg + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", freeTime=" + Arrays.toString(freeTime) +
                ", lastActive=" + lastActive +
                ", list_ans=" + list_ans +
                '}';
    }
}
