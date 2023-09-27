package com.onlinelearning.DAL;

import com.onlinelearning.Models.Role;

public interface RoleDAO {

    Role addRole(Role role);

    Integer getRoleIdByRoleName(Role role);

    Role getRoleById(int id);

}
