package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private String account;
    private String address;
    private String password;
    @JsonProperty("phone_number")
    private String phoneNumber;
}
