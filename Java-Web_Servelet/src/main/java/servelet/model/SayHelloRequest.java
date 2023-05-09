package servelet.model;

import lombok.Data;

@Data
public class SayHelloRequest {

    private String firstName;
    private String lastName;
}
