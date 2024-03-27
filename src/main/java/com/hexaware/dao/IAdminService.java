package com.hexaware.dao;
import com.hexaware.model.*;
import com.hexaware.exception.*;
public interface IAdminService {
	Admin getAdminById(int adminId) throws AdminNotFoundException;
//	 Admin getAdminById(int adminId);
	   // Admin getAdminByUsername(String username);
	    Admin getAdminByUsername(String username) throws AdminNotFoundException;

	    boolean registerAdmin(Admin adminData);
	    boolean updateAdmin(Admin adminData);
	    boolean deleteAdmin(int adminId);
}
