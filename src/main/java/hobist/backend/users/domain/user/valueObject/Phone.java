package hobist.backend.users.domain.user.valueObject;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

public record Phone(@NonNull String phone) {

    private static final String E_164_PATTERN = "^[1-9]\\d{1,14}$";

    //number of digits is not tested because it varies from country to country
    public Phone(String phone) {
        var phoneVal=stripAllWhiteSpaces(phone);
        if (phoneVal == null) {phoneVal = "";}
        if (!phoneVal.isEmpty() && !phoneVal.matches(E_164_PATTERN)) {
            throw new IllegalArgumentException("Phone number must follow the E.164 format!");
        }

        this.phone = phoneVal;
    }


    private String stripAllWhiteSpaces(String str){
        var x = str.replaceAll("\\s+","");

        return x;
    }
}
