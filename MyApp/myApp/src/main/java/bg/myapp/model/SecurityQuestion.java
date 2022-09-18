package bg.myapp.model;

import javax.persistence.*;

@Entity
@Table(name = "security_question")
public class SecurityQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false)
    private int orderQuestion;

    @OneToOne
    private Account user;


    public SecurityQuestion() {
    }

    public Long getId() {
        return id;
    }

    public SecurityQuestion setId(Long id) {
        this.id = id;
        return this;
    }


    public String getQuestion() {
        return question;
    }

    public SecurityQuestion setQuestion(String question) {
        this.question = question;
        return this;
    }

    public int getOrderQuestion() {
        return orderQuestion;
    }

    public SecurityQuestion setOrderQuestion(int orderQuestion) {
        this.orderQuestion = orderQuestion;
        return this;
    }

    public Account getUser() {
        return user;
    }

    public SecurityQuestion setUser(Account user) {
        this.user = user;
        return this;
    }
}
