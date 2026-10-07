package hobist.backend.users.user.valueObjects;

import hobist.backend.users.domain.user.valueObject.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTests {

    @ParameterizedTest
    @ValueSource(strings = {"nameSurname@gmail.com","   AG@unist.hr  ","name.surnam@yahoo.com"
    , "surname_name@yahoo.ba"})
    void shouldNotThrowException(String str){

        Email email = new Email(str);

    }

    @ParameterizedTest
    @ValueSource(strings = {"namesurnamegmail.com","@gmail.com","namesurnamegmail.com@",
    "name surname@gmail.com","name@surname@yahoo.com",".namesurname@gmail.com","namesurname@gmai_l.hr"
    , "companyname@gmail","bignameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee@gmail.coooom"})
    void shouldThrowException(String str){
       IllegalArgumentException e =assertThrows(IllegalArgumentException.class
               ,() -> new Email(str));

       assertThat(e).isInstanceOf(IllegalArgumentException.class);
    }

}
