package com.yukismimi.demo.controller;

import com.yukismimi.demo.entity.Admin;
import com.yukismimi.demo.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AdminController {

    private AdminServiceImpl adminService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Admin admin){
        if (adminService.login(admin)) {
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("failed");
    }

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
        initData();
    }

    private void initData() {
        Admin admin = new Admin();
        admin.setNickname("admin");
        admin.setPassword("123456");
        adminService.register(admin);
    }
}
