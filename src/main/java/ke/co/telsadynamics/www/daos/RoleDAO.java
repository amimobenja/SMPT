/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import ke.co.telsadynamics.www.entities.SmsEntityPermission;
import ke.co.telsadynamics.www.entities.SmsRoles;
import ke.co.telsadynamics.www.entities.SmsRolesPermissionMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tracom
 */

@Transactional
@Repository
public class RoleDAO implements IRoleDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private IEntityPermissionDAO entityPermissionDAO;
    
    @Override
    public List<SmsRoles> getAllRoles() {
        String hql = "FROM SmsRoles as rol ORDER BY rol.id";
        return (List<SmsRoles>) entityManager.createQuery(hql).getResultList();
    }
    
    
    @Override
    public List<SmsRoles> getAllRolesPermissions() {
        String hql = "FROM SmsRoles as rol ORDER BY rol.id";
        List<SmsRoles> roleList = entityManager.createQuery(hql).getResultList();        
        
        return roleList;
    }

    @Override
    public SmsRoles getRolesPermissionById(int id) {        
        SmsRoles role = getRoleById(id);        
        
        return role;
    }

    @Override
    public SmsRoles getRoleById(int id) {
        return entityManager.find(SmsRoles.class, id);
    }

    @Override
    public void addRole(SmsRoles role) {
        entityManager.persist(role);
    }

    @Override
    public void updateRole(SmsRoles role) {
        SmsRoles r = getRoleById(role.getRoleId());
        
        r.setRoleName(role.getRoleName());
        r.setDescription(role.getDescription());        
        r.setIntrash(role.getIntrash());
        
        entityManager.flush();
    }

    @Override
    public void deleteRole(int id) {
        entityManager.remove(getRoleById(id));
    }

    @Override
    public boolean doesRoleExists(String roleName) {
        String hql = "FROM SmsRoles WHERE role_name = ?";
        int count = entityManager.createQuery(hql).setParameter(1, roleName).getResultList().size();
        return count > 0;
    }
    
    @Override
    public boolean doesRoleExists(int roleId) {
        String hql = "FROM SmsRoles WHERE role_id = ?";
        int count = entityManager.createQuery(hql).setParameter(1, roleId).getResultList().size();
        return count > 0;
    }
    
    @Override
    public boolean doesRoleExists(int roleId, String roleName) {
        String hql = "FROM SmsRoles WHERE role_id != ? AND role_name = ?";
        int count = entityManager.createQuery(hql).setParameter(1, roleId).setParameter(2, roleName).getResultList().size();
        return count > 0;
    }

    
}
