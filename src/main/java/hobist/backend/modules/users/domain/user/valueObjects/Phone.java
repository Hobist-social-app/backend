package hobist.backend.modules.users.domain.user.valueObjects;

import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import jakarta.persistence.Embeddable;

@Embeddable
public record Phone(String phone) {

    private static final String E_164_PATTERN = "^[1-9]\\d{1,14}$";

    //number of digits is not tested because it varies from country to country
    public Phone(String phone) {
        var phoneVal=stripAllWhiteSpaces(phone);
        if (!phoneVal.isEmpty() && !phoneVal.matches(E_164_PATTERN)) {
            throw new InvalidArgumentException("Phone number must follow the E.164 format!");
        }

        this.phone = phoneVal;
    }


    private static String stripAllWhiteSpaces(String str){
        if (str== null) {str= "";}
        var x = str.replaceAll("\\s+","");

        return x;
    }
}
