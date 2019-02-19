package de.hhu.propra.sharingplatform.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString(exclude = "contract")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    private long amountProPayId;
    private String proPayIdSender;
    private String proPayIdRecipient;
    private double bail;
    private long bailProPayId;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
        mappedBy = "payment")
    private Contract contract;

    @SuppressWarnings("unused")
    private Payment() {
        // used for jpa
    }

    public Payment(User sender, User recipient, double amount, double bail) {
        this.proPayIdSender = sender.getPropayId();
        this.proPayIdRecipient = recipient.getPropayId();
        this.amount = amount;
        this.bail = bail;
    }

}
