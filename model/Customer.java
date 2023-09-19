package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String email;



    public Customer(String firstName, String lastName, String email) {
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()){
            throw new IllegalArgumentException();
        }else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!Objects.equals(firstName, customer.firstName)) return false;
        if (!Objects.equals(lastName, customer.lastName)) return false;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


}
