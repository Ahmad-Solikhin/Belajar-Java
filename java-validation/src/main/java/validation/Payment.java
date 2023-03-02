package validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;
import validation.constraint.CheckCase;
import validation.constraint.CheckOrderId;
import validation.enums.CaseMode;
import validation.group.CreditCardPaymentGroup;
import validation.group.MobileBankingPaymentGroup;
import validation.payload.EmailErrorPayload;

public class Payment {
    @NotBlank(message = "Email can not be blank",
            groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class})
    @Email(message = "Email format is wrong",
            groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class})
    private String email;
    @CheckOrderId(groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class})
    private String orderId;
    @Range(min = 10_000L, max = 1_000_000L, message = "Amount must between {min} and {max}",
            groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class})
    @NotNull(message = "amount can not null",
            groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class})
    private Long amount;
    @NotBlank(message = "Credit card can not blank",
            groups = {CreditCardPaymentGroup.class})
    @LuhnCheck(message = "Credit card format is wrong",
            groups = {CreditCardPaymentGroup.class},
            payload = {EmailErrorPayload.class})//Ini juga gabakal dieksekusi sebenernya sama ben validation, jadi harus di cek manual
    private String creditCard;
    @NotBlank(message = "Virtual account can not blank",
            groups = {MobileBankingPaymentGroup.class})
    private String virtualAccount;
    @Valid
    @NotNull(groups = {CreditCardPaymentGroup.class, MobileBankingPaymentGroup.class},
    message = "Customer can not blank")
    //Ini cara convert nya
    @ConvertGroup(from = CreditCardPaymentGroup.class, //Dari group mana
            to = Default.class) //Ke group asalnya
    @ConvertGroup(from = MobileBankingPaymentGroup.class, //Dari group mana
            to = Default.class) //Ke group asalnya
    private Customer customer;

    @Override
    public String toString() {
        return "Payment{" +
                "email='" + email + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", creditCard='" + creditCard + '\'' +
                ", virtualAccount='" + virtualAccount + '\'' +
                ", customer=" + customer +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getVirtualAccount() {
        return virtualAccount;
    }

    public void setVirtualAccount(String virtualAccount) {
        this.virtualAccount = virtualAccount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
}
