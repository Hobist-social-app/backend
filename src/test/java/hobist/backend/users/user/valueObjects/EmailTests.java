package hobist.backend.users.user.valueObjects;

import hobist.backend.users.domain.user.valueObjects.Email;
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
    @ValueSource(strings ={"ivan.gmail.com", "@gmail.com", "ivan@", "ivan@gmail",
            "ivan..horvat@gmail.com", ".ivan@gmail.com", "ivan.@gmail.com",
            "ivan@gmail..com", "ivan@-gmail.com", "ivan@gmail-.com"})
    void shouldThrowException(String str){
       IllegalArgumentException e =assertThrows(IllegalArgumentException.class
               ,() -> new Email(str));

       assertThat(e).isInstanceOf(IllegalArgumentException.class);
    }

}
