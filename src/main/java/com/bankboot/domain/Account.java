package com.bankboot.domain;

import com.bankboot.component.DoubleSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Account {
    String account;
    String userId;
    String password;
    @JsonSerialize(using = DoubleSerialize.class)
    double balance;
    String phoneNumber;
    Integer freeze;
}
