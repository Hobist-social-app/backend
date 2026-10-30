package hobist.backend.profiles.userProfiles.valueObjects;

import hobist.backend.modules.profiles.domain.userProfiles.valueObjects.Name;
import hobist.backend.shared.domain.exceptions.InvalidArgumentException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NameTests {

    @ParameterizedTest
    @ValueSource(strings = {"tony","Ivan","aNa","aNasTazIja","KIZO","Čabo",""})
    void shouldNotThrowException(String str){

       Name name = new Name(str);

    }

    @ParameterizedTest
    @ValueSource(strings = {"ivan2","#ana","anasta:zija", "ana marija","AasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasAasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasAasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasAasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasAasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasAasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdas"})
    void shouldThrowException(String str){

        InvalidArgumentException e = assertThrows(InvalidArgumentException.class
        ,() -> new Name(str));

        assertThat(e).isInstanceOf(InvalidArgumentException.class);


    }

}
