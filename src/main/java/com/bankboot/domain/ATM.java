package com.bankboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ATM {
    String machine;
    String password;
    Integer atmBalance;
    Integer maxBalance;
    String address;
}
