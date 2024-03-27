package com.hexaware.controller;
import com.hexaware.dao.*;
import com.hexaware.model.Admin;
import com.hexaware.exception.AdminNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class AdminService implements IAdminService {
    private Map<Integer, Admin> adminMap; 
    private Map<String, Admin> adminByUsernameMap; 

    public AdminService() {
        adminMap = new HashMap<>();
        adminByUsernameMap = new HashMap<>();
    }

    @Override
    public Admin getAdminById(int adminId) throws AdminNotFoundException {
        Admin admin = adminMap.get(adminId);
        if (admin == null) {
            throw new AdminNotFoundException("Admin with ID " + adminId + " not found.");
        }
        return admin;
    }


    @Override
    public Admin getAdminByUsername(String username) throws AdminNotFoundException {
        Admin admin = adminByUsernameMap.get(username);
        if (admin == null) {
            throw new AdminNotFoundException("Admin with username '" + username + "' not found.");
        }
        return admin;
    }

    @Override
    public boolean registerAdmin(Admin adminData) {
        if (adminData != null && !adminByUsernameMap.containsKey(adminData.getUsername())) {
            adminMap.put(adminData.getAdminID(), adminData);
            adminByUsernameMap.put(adminData.getUsername(), adminData);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Admin adminData) {
        if (adminData != null && adminMap.containsKey(adminData.getAdminID())) {
            adminMap.put(adminData.getAdminID(), adminData);
            adminByUsernameMap.put(adminData.getUsername(), adminData);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(int adminId) {
        Admin admin = adminMap.remove(adminId);
        if (admin != null) {
            adminByUsernameMap.remove(admin.getUsername());
            return true;
        }
        return false;
    }
}





/*package com.hexaware.controller;
import com.hexaware.model.*;

import com.hexaware.dao.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.exception.*;
public class AdminService implements IAdminService {
    private Map<Integer, Admin> adminMap; // Map to store admins with their IDs
    private Map<String, Admin> adminByUsernameMap; // Map to store admins with their usernames

    public AdminService() {
        adminMap = new HashMap<>();
        adminByUsernameMap = new HashMap<>();
    }
   // @Override
  //  public Admin getAdminById(int adminId) throws AdminNotFoundException {
   //     Admin admin = adminMap.get(adminId);
   //     if (admin == null) {
   //         throw new AdminNotFoundException("Admin with ID " + adminId + " not found.");
   //     }
   //     return admin;
 //   }

    @Override
    public Admin getAdminById(int adminId) {
        return adminMap.get(adminId);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminByUsernameMap.get(username);
    }

    @Override
    public boolean registerAdmin(Admin adminData) {
        if (adminData != null && !adminByUsernameMap.containsKey(adminData.getUsername())) {
            adminMap.put(adminData.getAdminID(), adminData);
            adminByUsernameMap.put(adminData.getUsername(), adminData);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Admin adminData) {
        if (adminData != null && adminMap.containsKey(adminData.getAdminID())) {
            adminMap.put(adminData.getAdminID(), adminData);
            adminByUsernameMap.put(adminData.getUsername(), adminData);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAdmin(int adminId) {
        Admin admin = adminMap.remove(adminId);
        if (admin != null) {
            adminByUsernameMap.remove(admin.getUsername());
            return true;
        }
        return false;
    }
}*/
