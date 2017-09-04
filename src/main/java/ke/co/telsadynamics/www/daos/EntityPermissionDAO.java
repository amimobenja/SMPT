/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import ke.co.telsadynamics.www.entities.SmsEntityPermission;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tracom
 */

@Transactional
@Repository
public class EntityPermissionDAO implements IEntityPermissionDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SmsEntityPermission> getAllEntityPermissions() {
        String hql = "FROM SmsEntityPermission ORDER BY permission_id";
        return (List<SmsEntityPermission>) entityManager.createQuery(hql).getResultList();        
    }

    @Override
    public SmsEntityPermission getEntityPermissionById(int enityPermissionId) {
        return entityManager.find(SmsEntityPermission.class, enityPermissionId);        
    }

    @Override
    public void addEntityPermission(SmsEntityPermission permission) {        
        entityManager.persist(permission);
    }

    @Override
    public void updateEntityPermission(SmsEntityPermission permission) {
        SmsEntityPermission entityperm = getEntityPermissionById(permission.getPermissionId());
        
        entityperm.setPermission(permission.getPermission());
        entityperm.setEntityId(permission.getEntityId());
        entityperm.setCaption(permission.getCaption());
        
        entityManager.flush();        
    }

    @Override
    
    @Modifying
    @Transactional
    public void deleteEntityPermission(int permissionId) {
        entityManager.remove(getEntityPermissionById(permissionId));
        
    }

    @Override
    public boolean doesEntityPermissionExists(int permissionId) {
        String hql = "FROM SmsEntityPermission WHERE permission_id = ?";
        int count = entityManager.createQuery(hql).setParameter(1, permissionId).getResultList().size();
        
        return count > 0;
    }

    @Override
    public boolean doesEntityPermissionExists(String permissionName) {
        String hql = "FROM SmsEntityPermission WHERE permission = ?";
        int count = entityManager.createQuery(hql).setParameter(1, permissionName).getResultList().size();
        
        return count > 0;        
    }

    @Override
    public boolean doesEntityPermissionExists(int permissionId, String permissionName) {
        String hql = "FROM SmsEntityPermission WHERE permission_id != ? AND permission = ?";
        int count = entityManager.createQuery(hql).setParameter(1, permissionId)
                .setParameter(2, permissionName).getResultList().size();
        
        return count > 0;        
    }
    
    @Override
    public boolean doesEntityPermissionMapByEntityIdExists(int entityId) {
        String hql = "FROM SmsEntityPermission WHERE entity_id = ?";
        int count = entityManager.createQuery(hql).setParameter(1, entityId).getResultList().size();
        
        return count > 0;
    }

    @Override
    public Collection<Map<String, Object>> deleteEntityPermissionMapByEntityIdExists(int entityId) {
        Collection<Map<String, Object>> mDEp = new LinkedHashSet<Map<String, Object>>();
        String hql = "FROM SmsEntityPermission WHERE entity_id = ?";
        List<SmsEntityPermission> list = entityManager.createQuery(hql).setParameter(1, entityId).getResultList();
        
        for (SmsEntityPermission epm : list) {   
            Map<String, Object> _rpm = new LinkedHashMap<String, Object>();
            _rpm.put("permissionId", epm.getPermissionId());
            _rpm.put("permission", epm.getPermission());
            _rpm.put("caption", epm.getCaption());
            mDEp.add(_rpm);
            
            
            System.out.println("ID - "+epm.getPermissionId());
            System.out.println("Permission - "+epm.getPermission());
            System.out.println("Caption - "+epm.getCaption());
            
            deleteEntityPermission(epm.getPermissionId());
            
            System.out.println("Deleted - "+epm.getPermissionId());
        }    
                
        return mDEp;
    }
    
}
