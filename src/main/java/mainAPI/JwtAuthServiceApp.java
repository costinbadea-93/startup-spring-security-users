package mainAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mainAPI.dto.ReviewDto;
import org.hibernate.validator.internal.util.TypeHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mainAPI.model.Role;
import mainAPI.model.User;
import mainAPI.service.UserService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
public class JwtAuthServiceApp implements CommandLineRunner {

  @Autowired
  UserService userService;

  public static void main(String[] args) {
    SpringApplication.run(JwtAuthServiceApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Override
  public void run(String... params) throws Exception {

//    User admin = new User();
//    admin.setUsername("admin");
//    admin.setPassword("admin");
//    admin.setEmail("admin@email.com");
//    admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
//
//    userService.signup(admin);
//
//    User client = new User();
//    client.setUsername("client");
//    client.setPassword("client");
//    client.setEmail("client@email.com");
//    client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//
//    userService.signup(client);
  }
}