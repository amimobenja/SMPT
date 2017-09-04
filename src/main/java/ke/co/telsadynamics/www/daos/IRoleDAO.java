/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import ke.co.telsadynamics.www.entities.SmsRoles;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tracom
 */
public interface IRoleDAO {
    
    List<SmsRoles> getAllRoles();
    
    List<SmsRoles> getAllRolesPermissions();
    
    SmsRoles getRolesPermissionById(int id);
    
    SmsRoles getRoleById(int id);

    void addRole(SmsRoles role);

    void updateRole(SmsRoles role);

    void deleteRole(int id);

    boolean doesRoleExists(String roleName);
    
    boolean doesRoleExists(int roleId);
    
    boolean doesRoleExists(int roleId, String roleName);
    
}
