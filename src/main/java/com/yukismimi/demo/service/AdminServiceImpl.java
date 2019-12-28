package com.yukismimi.demo.service;

import com.yukismimi.demo.entity.Admin;
import com.yukismimi.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl {

    private AdminRepository adminRepository;

    public boolean login(Admin admin){
        Optional<Admin> adm = adminRepository.findAdminByNickname(admin.getNickname());
        return adm.orElseGet(Admin::new).getPassword().equals(admin.getPassword());
    }

    public void register(Admin admin){
        adminRepository.save(admin);
    }

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
}
