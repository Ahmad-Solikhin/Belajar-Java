package validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Address {

    @NotBlank(message = "city can not blank")
    private String city;
    @NotBlank(message = "country can not blank")
    private String country;
    @NotNull(message = "zip code can not null")
    private Integer zipCode;

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
