package hobist.backend.IntegrationTests;

import com.jayway.jsonpath.JsonPath;
import hobist.backend.modules.profiles.infrastructure.userPreferences.UserPreferencesRepository;
import hobist.backend.modules.users.infrastructure.user.UserRepository;
import hobist.backend.shared.domain.valueObjects.EntityId;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.docker.compose.skip.in-tests=false")//M.G: SpringBootTest skips docker compose file so it cant connect to db without adding this in brackets.
@AutoConfigureMockMvc
@Transactional
public class SignUpIntegrationTest {

    @Autowired
   MockMvc mockMvc;
    @Autowired
   UserRepository userRepository;
    @Autowired
   UserPreferencesRepository userPreferencesRepository;

   @Test
    void ShouldCreateUserAndUserPreferences() throws Exception {

       var response = mockMvc.perform(post("/users")
               .contentType(APPLICATION_JSON)
               .content("""
                       {
                            "nickname": "testmicki",
                            "phone": "385955571151",
                            "email": "testmicki@gmail.com",
                            "rawPassword": "password123"
                        }
                       """))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").exists())
               .andReturn();

       var id=new EntityId(
               UUID.fromString(
                       JsonPath.read(response.getResponse().getContentAsString(), "$.id")
               ));

       var user = userRepository.findById(id).orElseThrow();

       assertEquals("testmicki",user.getNickname().nickname());
       assertEquals("testmicki@gmail.com",user.getEmail().email());

       var userPreferences = userPreferencesRepository.findById(id).orElseThrow();
   }

}
