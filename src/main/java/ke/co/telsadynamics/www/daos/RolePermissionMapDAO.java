/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import ke.co.telsadynamics.www.entities.SmsRolesPermissionMap;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tracom
 */
@Transactional
@Repository
public class RolePermissionMapDAO implements IRolePermissionMapDAO{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<SmsRolesPermissionMap> getAllRolePermissionMaps() {
        
        String hql = "FROM SmsRolesPermissionMap ORDER BY role_permission_map_id";
        return (List<SmsRolesPermissionMap>) entityManager.createQuery(hql).getResultList();       
        
//        Session session = entityManager.unwrap(Session.class); // Unwrapping a EntityManager to a Session

    }
    
    @Override
    public List<SmsRolesPermissionMap> getAllRolePermissionMapsByPermissionId(int permissionId) {
        
        String hql = "FROM SmsRolesPermissionMap WHERE permission_id = '"+permissionId+"' ORDER BY role_permission_map_id";
        return (List<SmsRolesPermissionMap>) entityManager.createQuery(hql).getResultList();  

    }

    @Override
    public SmsRolesPermissionMap getRolePermissionMapById(int id) {
        return entityManager.find(SmsRolesPermissionMap.class, id);
    }

    @Override
    public void addRolePermissionMap(SmsRolesPermissionMap rolePerMap) {
        entityManager.persist(rolePerMap); 
    }

    @Override
    public void updateRolePermissionMap(SmsRolesPermissionMap rolePerMap) {
        SmsRolesPermissionMap rolePermissionMap = getRolePermissionMapById(rolePerMap.getRolePermissionMapId());
        
        rolePermissionMap.setRoleId(rolePerMap.getRoleId());
        rolePermissionMap.setPermissionId(rolePerMap.getPermissionId());
        
        entityManager.flush();
    }

    @Override    
    @Modifying
    @Transactional
    public void deleteRolePermissionMap(int id) {
        System.out.println("Reached Here -- ");
        entityManager.remove(getRolePermissionMapById(id));
        System.out.println("Finished... ");
    }

    @Override
    public boolean doesRolePermissionMapExists(int rolePerMapId) {
        String hql = "FROM SmsRolesPermissionMap WHERE role_permission_map_id = ?";
        int count = entityManager.createQuery(hql).setParameter(1, rolePerMapId).getResultList().size();
        
        return count > 0;
    }
    
    @Override
    public boolean doesRolePermissionMapByRoleIdExists(int roleId) {
        String hql = "FROM SmsRolesPermissionMap WHERE role_id = ?";
        int count = entityManager.createQuery(hql).setParameter(1, roleId).getResultList().size();
        
        return count > 0;
    }
    
    @Override
    public Collection<Map<String, Object>> deleteRolePermissionMapByRoleIdExists(int roleId) {
        Collection<Map<String, Object>> mDRpm = new LinkedHashSet<Map<String, Object>>();
        String hql = "FROM SmsRolesPermissionMap WHERE role_id = ?";
        List<SmsRolesPermissionMap> list = entityManager.createQuery(hql).setParameter(1, roleId).getResultList();
        
        for (SmsRolesPermissionMap rpm : list) {   
            Map<String, Object> _rpm = new LinkedHashMap<String, Object>();
            _rpm.put("permissionId", rpm.getPermissionId());
            _rpm.put("roleId", rpm.getRoleId());
            mDRpm.add(_rpm);
            deleteRolePermissionMap(rpm.getRolePermissionMapId());
        }    
                
        return mDRpm;
    }
    
    
    
}
