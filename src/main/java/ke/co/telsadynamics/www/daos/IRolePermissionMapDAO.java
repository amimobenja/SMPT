/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import ke.co.telsadynamics.www.entities.SmsRolesPermissionMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Tracom
 */
public interface IRolePermissionMapDAO {
    List<SmsRolesPermissionMap> getAllRolePermissionMaps();    
    
    List<SmsRolesPermissionMap> getAllRolePermissionMapsByPermissionId(int permissionId);

    SmsRolesPermissionMap getRolePermissionMapById(int id);

    void addRolePermissionMap(SmsRolesPermissionMap rolePerMap);

    void updateRolePermissionMap(SmsRolesPermissionMap rolePerMap);
    
    void deleteRolePermissionMap(int id);

    boolean doesRolePermissionMapExists(int rolePerMapId);
    
    boolean doesRolePermissionMapByRoleIdExists(int roleId);
    
    Collection<Map<String, Object>> deleteRolePermissionMapByRoleIdExists(int roleId);
}
