package com.bankboot.controller;

import com.bankboot.dao.ATMDao;
import com.bankboot.domain.*;
import com.bankboot.server.SalesmanServer;
import com.bankboot.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/salesman")
public class SalesmanController {
    SalesmanServer server;
    JWTUtils jwtUtils;
    ATMDao atmDao;

    public SalesmanController(SalesmanServer server, JWTUtils jwtUtils, ATMDao atmDao) {
        this.server = server;
        this.jwtUtils = jwtUtils;
        this.atmDao = atmDao;
    }

    @GetMapping
    R login(Salesman salesman) {
        log.info(salesman.toString());
        log.info("Salesman login ...");
        try {
            String token = server.login(salesman);
            if(token == null) {
                return R.error("账号或密码错误");
            }
            else {
                R success = R.success(token);
                success.setMsg("登陆成功");
                return success;
            }
        } catch (SQLException e) {
            return R.error("操作异常，请重试");
        }
    }

    @GetMapping("/verify")
    public R verify(@RequestHeader String token) {
        log.info("身份核验");
        if(jwtUtils.verifySalesman(token)) {
            R success = R.success(null);
            success.setMsg("核验通过");
            return success;
        }
        return R.error("error");
    }

    @PostMapping("/transact/{account}")
    R selectOneTransact(@PathVariable String account, @RequestHeader String token) {
        log.info("获取个人交易记录");
        boolean successful = jwtUtils.verifySalesman(token);
        if(successful) {
            try {
                List<Transact> list = server.getOneTransact(account);
                R success = R.success(list);
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @PostMapping("/transact")
    R getAllTransact(@RequestHeader String token) {
        log.info("获取全部交易");
        boolean successful = jwtUtils.verifySalesman(token);
        if(successful) {
            try {
                List<Transact> list = server.getAllTransact();
                R success = R.success(list);
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            R error = R.error("身份已过期，请重新登录");
            error.setCode(2);
            return error;
        }
    }

    @GetMapping("/transfer")
    R getAllTransfer(@RequestHeader String token) {
        log.info("获取所有转账记录");
        boolean successful = jwtUtils.verifySalesman(token);
        if(successful) {
            try {
                List<Transfer> list = server.getAllTransfer();
                R success = R.success(list);
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @GetMapping("/transfer/{account}")
    R getOneTransfer(@PathVariable String account, @RequestHeader String token) {
        log.info("获取个人转账记录");
        boolean successful = jwtUtils.verifySalesman(token);
        if(successful) {
            try {
                List<Transfer> list = server.getOneTransfer(account);
                R success = R.success(list);
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @PostMapping("/in")
    R inMoney(@RequestBody Operation operation, @RequestHeader String token) {
        log.info("加钞");
        if(operation.getBalance() == null || operation.getBalance() % 100 != 0 || operation.getBalance() == 0) {
            return R.error("操作失败，金额异常");
        }
        boolean successful = jwtUtils.verifySalesman(token);
        if(successful) {
            try {
                String jobNo = jwtUtils.getId(token);
                operation.setJobNo(jobNo);
                ATM atm = atmDao.selectOne(operation.getMachine());
                Integer balance = atm.getAtmBalance();
                Integer maxBalance = atm.getMaxBalance();
                if(balance + operation.getBalance() <= maxBalance) {
                    server.inMoney(operation);
                    R success = R.success(null);
                    success.setMsg("操作成功");
                    return success;
                }
                else {
                    return R.error("当前机器钞票过多，无法加钞");
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

    @PostMapping("/out")
    R outMoney(@RequestBody Operation operation, @RequestHeader String token) {
        log.info("减钞");
        if(operation.getBalance() == null || operation.getBalance() % 100 != 0 || operation.getBalance() == 0) {
            return R.error("操作失败，金额异常");
        }
        boolean successful = jwtUtils.verifySalesman(token);
        if(successful) {
            try {
                String jobNo = jwtUtils.getId(token);
                operation.setJobNo(jobNo);
                Integer balance = atmDao.getBalance(operation.getMachine());
                if(operation.getBalance() <= balance) {
                    server.outMoney(operation);
                    R success = R.success(null);
                    success.setMsg("操作成功");
                    return success;
                }
                else {
                    return R.error("操作失败，当前机器钞票不足");
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

    @GetMapping("/account")
    R getAllAccount(@RequestHeader String token) {
        log.info("获取所有账户信息");
        boolean successful = jwtUtils.verifySalesman(token);
        if(successful) {
            try {
                List<Account> list = server.getAllAccount();
                R success = R.success(list);
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @GetMapping("/account/{account}")
    R getAccount(@PathVariable String account, @RequestHeader String token) {
        log.info("获取单个账户信息");
        boolean successful = jwtUtils.verify(token);
        if(successful) {
            try {
                Account acc = server.getAccount(account);
                R success = R.success(acc);
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @PostMapping("/account/{userId}")
    R getUserAccount(@PathVariable String userId, @RequestHeader String token) {
        log.info("获取单用户账户信息");
        boolean successful = jwtUtils.verify(token);
        if(successful) {
            try {
                List<Account> acc = server.getUserAccount(userId);
                R success = R.success(acc);
                success.setMsg("获取成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }

    @PostMapping("/account")
    R freezeAccount(String account ,@RequestHeader String token) {
        log.info("冻结或解冻账户" + account);
        boolean successful = jwtUtils.verify(token);
        if(successful) {
            try {
                server.freezeAccount(account);
                R success = R.success(null);
                success.setMsg("操作成功");
                return success;
            } catch (SQLException e) {
                return R.error("操作异常，请重试");
            }
        }
        else {
            // 重定向
            return R.error("身份已过期，请重新登录");
        }
    }
}
