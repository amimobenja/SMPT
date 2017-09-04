/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.services;

import java.util.HashMap;
import java.util.List;
import ke.co.telsadynamics.www.entities.SmsEntityPermission;

/**
 *
 * @author Tracom
 */
public interface IEntityPermissionService {    
    
    List<SmsEntityPermission> getAllEntityPermissions();

    SmsEntityPermission getEntityPermissionById(int entityPermissionId);
    
    HashMap updateEntityPermission(SmsEntityPermission entityPermission);

    HashMap deleteEntityPermission(int entityPermissionId); 
    
    HashMap createEntityPermission(String jsonString);
    
}
