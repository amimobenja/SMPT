/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.services;

import ke.co.telsadynamics.www.entities.SmsRoles;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tracom
 */
public interface IRoleService {
    
    List<SmsRoles> getAllRoles();
    
    Map<String, Object> getAllRolesPermissions();
    
    Map<String, Object> getRolesPermissionById(int id);

    SmsRoles getRoleById(int id);
    
    HashMap createRole(String jsonString);  
    
    HashMap createhRole(LinkedHashMap<String, Object> objMap);  

    HashMap updateRole(SmsRoles role);

    HashMap updatehRole(LinkedHashMap<String, Object> objMap);

    HashMap deleteRole(int id);
}
