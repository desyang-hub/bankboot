package com.bankboot.controller;

import com.bankboot.domain.ATM;
import com.bankboot.domain.R;
import com.bankboot.server.ATMServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/atm")
public class ATMController {
    ATMServer server;

    @Autowired
    public ATMController(ATMServer server) {
        this.server = server;
    }

    @GetMapping
    R<String> login(ATM atm) {
        log.info("atm server starting ...");
        try {
            String machine = server.login(atm);
            if(machine == null) {
                return R.error("请确保信息正确");
            }
            else {
                R<String> success = R.success(machine);
                success.setMsg("验证成功");
                return success;
            }
        } catch (SQLException e) {
            return R.error("验证异常");
        }
    }

    @PostMapping
    public R<String> logout(HttpServletRequest request, HttpServletResponse response) {
        ServletContext application = request.getServletContext();
        application.setAttribute("", "");

        return null;
    }
}
