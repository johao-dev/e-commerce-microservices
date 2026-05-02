package zuzz.projects.e_commerce.microservices.customer_microservice.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Customer {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
}
