package aavn.dating.entity;

import javax.persistence.*;

/**
 * Created by dhvy on 6/7/2017.
 */
@Entity
public class Question {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Basic(optional = true)
    private String content;

    public Question() {

    }


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question(int questionId) {

        this.questionId = questionId;
    }

}
