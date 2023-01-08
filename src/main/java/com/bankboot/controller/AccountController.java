package com.bankboot.controller;

import com.bankboot.dao.ATMDao;
import com.bankboot.dao.AccountDao;
import com.bankboot.domain.*;
import com.bankboot.server.AccountServer;
import com.bankboot.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {
    AccountServer server;
    JWTUtils jwtUtils;
    ATMDao atmDao;
    AccountDao accountDao;
    @Autowired
    public AccountController(AccountServer server, JWTUtils jwtUtils, ATMDao atmDao, AccountDao accountDao) {
        this.server = server;
        this.jwtUtils = jwtUtils;
        this.atmDao = atmDao;
        this.accountDao = accountDao;
    }

    @GetMapping
    public R login(Account user) {
        log.info(user.getAccount() + "登录");
        try {
            if(accountDao.selectByAccount(user.getAccount()) == null) {
                return R.error("账号不存在");
            }
            Boolean status = accountDao.getStatus(user.getAccount());
            if(status == true) {
                return R.error("账户已冻结，请联系业务员进行解冻");
            }
            String token = server.login(user);
            if(token == null) {
                boolean freezing = server.procError(user.getAccount());
                if(freezing) {
                    return R.error("账号已被冻结");
                }
                else {
                    return R.error("密码错误");
                }
            }
            else {
                R success = R.success(token);
                success.setMsg("登陆成功");
                return success;
            }
        } catch (Exception e) {
            return R.error("发生异常，请重试 ……");
        }
    }

    @GetMapping("/verify")
    public R verify(@RequestHeader String token) {
        log.info("身份核验");
        if(jwtUtils.verifyAccount(token)) {
            R success = R.success(null);
            success.setMsg("核验通过");
            return success;
        }
        return R.error("error");
    }



    @PutMapping
    public R transfer(@RequestBody Transfer transfer, @RequestHeader String token) {
        log.info("转账");
        boolean successful = jwtUtils.verifyAccount(token);
        if(successful) {
            try {
                String target = transfer.getTargetAccount();
                Account acc = accountDao.selectByAccount(target);
                if(acc == null) {
                    return R.error("目标账户不存在，请检查账号是否正确");
                }
                String account = jwtUtils.getId(token);
                transfer.setAccount(account);
                double balance = server.getBalance(account);
                if(transfer.getBalance() <= balance) {
                    server.transfer(transfer);
                    R success = R.success(null);
                    success.setMsg("转账成功");
                    return success;
                }
                else {
                    return R.error("转账失败，余额不足");
                }
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @PostMapping
    R getBalance(@RequestHeader String token) {
        log.info("查询余额");
        boolean successful = jwtUtils.verifyAccount(token);
        if(successful) {
            String account = jwtUtils.getId(token);
            try {
                double balance = server.getBalance(account);
                R success = R.success(String.format("%.2f", balance));
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("发生异常");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @PostMapping("/in")
    R saveMoney(@RequestBody Transact transact, @RequestHeader String token) {
        log.info("saveMoney");
        boolean successful = jwtUtils.verifyAccount(token);
        if(successful) {
            try {
                String account = jwtUtils.getId(token);
                transact.setAccount(account);
                ATM atm = atmDao.selectOne(transact.getMachine());
                Integer balance = atm.getAtmBalance();
                Integer maxBalance = atm.getMaxBalance();
                if(balance + transact.getBalance() <= maxBalance) {
                    server.saveMoney(transact);
                    R success = R.success(null);
                    success.setMsg("存款成功");
                    return success;
                }
                else {
                    return R.error("当前机器钞票上限，无法存款");
                }
            } catch (SQLException e) {
                return R.error("发生异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @PostMapping("/out")
    R catchMoney(@RequestBody Transact transact, @RequestHeader String token) {
        log.info("取现金 {}",transact.getBalance() );
        boolean successful = jwtUtils.verifyAccount(token);
        if(successful) {
            try {
                String account = jwtUtils.getId(token);
                transact.setAccount(account);
                double curBalance = server.getBalance(account);
                if(curBalance < transact.getBalance()) return R.error("账户余额不足");
                Integer balance = atmDao.getBalance(transact.getMachine());
                if(transact.getBalance() <= balance) {
                    server.catchMoney(transact);
                    R success = R.success(null);
                    success.setMsg("取款成功，请取走钞票");
                    return success;
                }
                else {
                    return R.error("当前机器余额不足，无法完成取款");
                }
            } catch (SQLException e) {
                return R.error("发生异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

}
