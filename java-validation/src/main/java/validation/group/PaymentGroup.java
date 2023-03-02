package validation.group;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

//Ini cara membuat sequence nya, urutan dari kiri ke kanan
@GroupSequence(value = {
        Default.class, //Ini pertama
        CreditCardPaymentGroup.class, //Ini kedua
        MobileBankingPaymentGroup.class //Ini ketiga
})
public interface PaymentGroup {
}
