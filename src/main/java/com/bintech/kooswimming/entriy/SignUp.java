package com.bintech.kooswimming.entriy;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

@Indices({
        @Index(value = "email", type = IndexType.Unique),
        @Index(value = "name", type = IndexType.Unique)
})

public class SignUp{

    private String name;
    private String address;
    private String postalCode;
    private String email;

    public SignUp(String name, String address, String postalCode, String email) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
    }

    public SignUp() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
