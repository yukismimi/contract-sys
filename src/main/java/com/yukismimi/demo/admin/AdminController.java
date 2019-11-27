package com.yukismimi.demo.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        Admin admin = new Admin();
        admin.setNickname("admin");
        admin.setPassword("123456");
        adminService.register(admin);
    }
}
