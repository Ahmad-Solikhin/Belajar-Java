package validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Person {

    private List<@NotBlank(message = "hobby can not blank") String> hobbies;
    @NotBlank(message = "First name can not blank")
    @Size(max = 20, message = "Can not larger than 20")
    private String firstName;
    @NotBlank(message = "Last name can not blank")
    @Size(max = 20, message = "Can not larger than 20")
    private String lastName;
    @NotNull(message = "Age can not be null")
    private Integer age;
    @NotNull(message = "Address can not null")
    @Valid //Ini digunakan untuk validasi nested object
    private Address address;

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Address getAddress() {
        return address;
    }

    public void sayHello(@NotBlank(message = "Name can not blank") String name){
        System.out.println("Hello " + name + ", my name is " + firstName);
    }

    @NotBlank(message = "Full name can not blank")
    public String fullName(){
        return firstName + " " + lastName;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    @Valid
    public Person() {
    }
    @Valid
    public Person(@NotBlank(message = "first name can not blank") String firstName,
                  @NotBlank(message = "last name can not blank") String lastName,
                  @NotNull(message = "age can not null") Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
