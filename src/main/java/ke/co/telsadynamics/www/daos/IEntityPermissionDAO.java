/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import ke.co.telsadynamics.www.entities.SmsEntityPermission;

/**
 *
 * @author Tracom
 */
public interface IEntityPermissionDAO {
    
    List<SmsEntityPermission> getAllEntityPermissions();

    SmsEntityPermission getEntityPermissionById(int enityPermissionId);

    void addEntityPermission(SmsEntityPermission permission);

    void updateEntityPermission(SmsEntityPermission permission);

    void deleteEntityPermission(int permissionId);

    boolean doesEntityPermissionExists(int permissionId);

    boolean doesEntityPermissionExists(String permissionName);

    boolean doesEntityPermissionExists(int permissionId, String permissionName);
    
    Collection<Map<String, Object>> deleteEntityPermissionMapByEntityIdExists(int entityId);
    
    boolean doesEntityPermissionMapByEntityIdExists(int entityId);
    
}
