package aavn.dating.entity;

/**
 * Created by dhvy on 6/27/2017.
 */

import javax.persistence.*;

@Entity
@Table( uniqueConstraints={
        @UniqueConstraint(columnNames = {"userSend", "userReceive"})
})
public class Message {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "userSend")
    private User userSend;

    @Basic(optional = false)
    @ManyToOne
    @JoinColumn(name = "userReceive")
    private User userReceive;

    @Basic(optional = false)
    private String content;

    public Message(int messageId) {
        this.messageId = messageId;
    }

    public Message() {

    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public User getUserSend() {
        return userSend;
    }

    public void setUserSend(User userSend) {
        this.userSend = userSend;
    }

    public User getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(User userReceive) {
        this.userReceive = userReceive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userSend=" + userSend +
                ", userReceive=" + userReceive +
                ", content='" + content + '\'' +
                '}';
    }
}
