package hobist.backend.users.user.valueObjects;

import hobist.backend.users.domain.user.valueObject.Phone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhoneTests {

    @ParameterizedTest
    @ValueSource(strings = {"385954174887", "385 95 417 4887",
            "    3   85     95 417 4887", "224 68223455","","     ",})
    void ShouldNotThrowException(String str) {
        Phone phone = new Phone(str);
    }


    @ParameterizedTest
    @ValueSource(strings = {"385w54174887","afwefew ", "--385954174887"})
    void ShouldThrowException(String str){

        IllegalArgumentException e = assertThrows( IllegalArgumentException.class, () -> new Phone(str));

       assertThat(e).isInstanceOf(IllegalArgumentException.class);
       assertThat(e).hasMessage("Phone number must follow the E.164 format!");

    }
}
