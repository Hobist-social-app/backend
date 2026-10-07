package hobist.backend.users.domain.user.valueObject;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Phone {

    private static final String E_164_PATTERN = "^[1-9]\\d{1,14}$";

    private final @NonNull String phone;

    //number of digits is not tested because it varies from country to country
    public Phone(String phone) {
        var phoneVal=stripAllWhiteSpaces(phone);
        if (phoneVal == null) {phoneVal = "";}
        if (!phoneVal.isEmpty() && !phoneVal.matches(E_164_PATTERN)) {
            throw new IllegalArgumentException("Phone number must follow the E.164 format!");
        }

        this.phone = phoneVal;
    }

    public String value() {return phone;}

    private String stripAllWhiteSpaces(String str){
        var x = str.replaceAll("\\s+","");

        return x;
    }
}
