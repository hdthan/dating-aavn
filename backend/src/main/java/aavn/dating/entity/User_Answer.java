package aavn.dating.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

/**
 * Created by dhvy on 6/7/2017.
 */
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, scope = User_Answer.class)
public class User_Answer {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_answerId;

    @Basic( optional = true)
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Basic( optional = false)
    @ManyToOne
    @JoinColumn(name = "quesId")
    private Question ques;

    @Basic( optional = false )
    private int ans;

    public User_Answer() {

    }

    public long getUser_answerId() {
        return user_answerId;
    }

    public void setUser_answerId(long user_answerId) {
        this.user_answerId = user_answerId;
    }


    public int getAns() {
        return ans;
    }

    public void setAns(int ans) {
        this.ans = ans;
    }

    public User_Answer(int user_answerId) {

        this.user_answerId = user_answerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQues() {
        return ques;
    }

    public void setQues(Question ques) {
        this.ques = ques;
    }
}
