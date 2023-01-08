package com.bankboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BankUser {
    String userId;
    String username;
    String sex;
    String idCard; // 身份证号
    String openDate; // 开户日期
    String address;  // 住址
}
