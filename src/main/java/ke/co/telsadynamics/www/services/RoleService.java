/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import ke.co.telsadynamics.www.daos.IEntityPermissionDAO;
import ke.co.telsadynamics.www.daos.IRoleDAO;
import ke.co.telsadynamics.www.daos.IRolePermissionMapDAO;
import ke.co.telsadynamics.www.entities.SmsRoles;
import ke.co.telsadynamics.www.entities.SmsRolesPermissionMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import ke.co.telsadynamics.www.entities.SmsEntityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tracom
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDAO roleDAO;

    @Autowired
    private IRolePermissionMapDAO rolePermissionMapDAO;

    @Autowired
    private IEntityPermissionDAO entityPermissionDAO;

    @Override
    public List<SmsRoles> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public SmsRoles getRoleById(int id) {
        SmsRoles obj = roleDAO.getRoleById(id);
        return obj;
    }

    @Override
    public HashMap createRole(String jsonString) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            SmsRoles role = mapper.readValue(jsonString, SmsRoles.class);

            if (!role.getRoleName().equalsIgnoreCase("") || !role.getDescription().equalsIgnoreCase("")) {
                if (!roleDAO.doesRoleExists(role.getRoleName())) {
                    SmsRoles r = new SmsRoles();

                    r.setRoleName(role.getRoleName());
                    r.setDescription(role.getDescription());
//                    r.setIntrash(role.getIntrash());

                    roleDAO.addRole(r);

                    Map<String, Object> cr = new LinkedHashMap<String, Object>();
                    cr.put("roleName", role.getRoleName());
                    cr.put("description", role.getDescription());
//                    cr.put("intrash", role.getIntrash());

                    System.out.println("Role Permission Map Collection - " + role.getSmsRolesPermissionMapCollection().size());

                    Collection<SmsRolesPermissionMap> rolePermMap = new LinkedHashSet<SmsRolesPermissionMap>();
                    for (SmsRolesPermissionMap rpm : role.getSmsRolesPermissionMapCollection()) {

                        SmsRolesPermissionMap _rpm = new SmsRolesPermissionMap();

                        _rpm.setRoleId(r);
                        _rpm.setPermissionId(rpm.getPermissionId());

                        rolePermMap.add(_rpm);

                        rolePermissionMapDAO.addRolePermissionMap(_rpm);
                    }

                    r.setSmsRolesPermissionMapCollection(rolePermMap);

                    cr.put("ssRolePermissionMapCollection", rolePermMap);

                    hashMap.put("statusCode", "00");
                    hashMap.put("statusName", "Role created successfully");

                } else {

                    hashMap.put("statusCode", "03");
                    hashMap.put("statusName", "Role Name Exist.");
                }
            } else {
                if (role.getRoleName().equalsIgnoreCase("")) {

                    hashMap.put("statusCode", "01");
                    hashMap.put("statusName", "Role Name is Empty.");
                } else if (role.getDescription().equalsIgnoreCase("")) {

                    hashMap.put("statusCode", "01");
                    hashMap.put("statusName", "Role Description is Empty.");
                }

            }

        } catch (IOException ex) {

            hashMap.put("statusCode", "02");
            hashMap.put("statusName", ex.toString());
            System.out.println("IOException - " + ex);
        }

        return hashMap;
    }

    @Override
    public HashMap createhRole(LinkedHashMap<String, Object> objMap) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        String rName = objMap.get("roleName").toString();
        String rDesc = objMap.get("description").toString();
        Collection<LinkedHashMap<String, Object>> rolePermCol
                = (Collection<LinkedHashMap<String, Object>>) objMap.get("rolePermissionMapCollection");

        if (!rName.equalsIgnoreCase("") || !rDesc.equalsIgnoreCase("")) {
            if (!roleDAO.doesRoleExists(rName)) {
                SmsRoles r = new SmsRoles();

                r.setRoleName(rName);
                r.setDescription(rDesc);

                roleDAO.addRole(r);

                System.out.println("Role Permission Map Collection - " + rolePermCol.size());
                
                if (!rolePermCol.isEmpty()) { 
                    Collection<SmsRolesPermissionMap> rolePermMap = new LinkedHashSet<SmsRolesPermissionMap>();
                    for (LinkedHashMap<String, Object> rpm : rolePermCol) {
                        
                        SmsEntityPermission epId = entityPermissionDAO.getEntityPermissionById(
                                Integer.parseInt(rpm.get("permissionId").toString()));
                        SmsRolesPermissionMap _rpm = new SmsRolesPermissionMap();

                        _rpm.setRoleId(r);
                        _rpm.setPermissionId(epId);

                        rolePermMap.add(_rpm);

                        rolePermissionMapDAO.addRolePermissionMap(_rpm);
                    }
                    r.setSmsRolesPermissionMapCollection(rolePermMap);                
                }
                hashMap.put("statusCode", "00");
                hashMap.put("statusName", "Role created successfully");

            } else {
                hashMap.put("statusCode", "03");
                hashMap.put("statusName", "Role Name Exist.");
            }
        } else {
            if (rName.equalsIgnoreCase("")) {
                hashMap.put("statusCode", "01");
                hashMap.put("statusName", "Role Name is Empty.");
            } else if (rDesc.equalsIgnoreCase("")) {
                hashMap.put("statusCode", "01");
                hashMap.put("statusName", "Role Description is Empty.");
            }
        }

        return hashMap;
    }
    
    @Override
    public HashMap updatehRole(LinkedHashMap<String, Object> objMap) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        boolean update = false;
        
        String rId = objMap.get("roleId").toString();
        String rName = objMap.get("roleName").toString();
        String rDesc = objMap.get("description").toString();
        Collection<LinkedHashMap<String, Object>> rolePermCol
            = (Collection<LinkedHashMap<String, Object>>) objMap.get("rolePermissionMapCollection");
        
        int roleId = Integer.parseInt(rId);
        SmsRoles r = roleDAO.getRoleById(roleId);
        
        if (roleDAO.doesRoleExists(roleId)) {
            if (!roleDAO.doesRoleExists(roleId, rName)) {
                /**
                 * Check if the Role exist among the Role Map Permission
                 * Mapping, if so remove ALL associated Role permission mappings
                 */
                Collection<Map<String, Object>> mDRpm = new LinkedHashSet<Map<String, Object>>();
                if (rolePermissionMapDAO.doesRolePermissionMapByRoleIdExists(roleId)) {
                    System.out.println(" - RolePermissionMap to be Removed - " + roleId);
                    mDRpm = rolePermissionMapDAO.deleteRolePermissionMapByRoleIdExists(roleId);
                }

                /**
                 * Check if the Role Permission Mapping Collection is empty. If
                 * Yes, just update the role as it is and esp after removing the
                 * earlier existing mappings
                 */
                if (!rolePermCol.isEmpty()) {
                    Map<String, Object> mNRpm = new LinkedHashMap<String, Object>();
                    Collection<Map<String, Object>> ssRolesPerm = new LinkedHashSet<Map<String, Object>>();
                    for (LinkedHashMap<String, Object> rpm : rolePermCol) {
                        /**
                         * Create a new Role Permission Map and save it to the
                         * DB using the Role roleId
                         */
                        System.out.println("PID - "+rpm.get("permissionId").toString());
                        if (entityPermissionDAO.doesEntityPermissionExists(Integer.parseInt(rpm.get("permissionId").toString()))) {
                            SmsEntityPermission epId = entityPermissionDAO.getEntityPermissionById(Integer.parseInt(rpm.get("permissionId").toString()));
                            SmsRolesPermissionMap _rpm = new SmsRolesPermissionMap();
                            _rpm.setRoleId(r);
                            _rpm.setPermissionId(epId);

                            rolePermissionMapDAO.addRolePermissionMap(_rpm);
                            update = true;

                            Map<String, Object> _Mrpm = new LinkedHashMap<String, Object>();
                            _Mrpm.put("permissionId", _rpm.getPermissionId());
                            _Mrpm.put("roleId", r.getRoleId());

                            ssRolesPerm.add(_Mrpm);

                            mNRpm.put("newRolePermissionMapCollection", ssRolesPerm);

                        } else {
                            hashMap.put("statusCode", "01");
                            hashMap.put("statusName", " Permission ID - " + rpm.get("permissionId").toString()
                                    + " Does does not exist");
                            update = false;
                            break;
                        }
                    }

                    if (update) {
                        SmsRoles pRole = getRoleById(roleId);

                        Map<String, Object> upd = new LinkedHashMap<String, Object>();

                        upd.put("previousRoleName", pRole.getRoleName());
                        upd.put("newRoleName", r.getRoleName());
                        upd.put("previousRoleDescription", pRole.getDescription());
                        upd.put("newRoleDescription", r.getDescription());
                        upd.put("previousIntrash", pRole.getIntrash());
                        upd.put("newIntrash", r.getIntrash());
                        upd.put("previousSmsRolesPermissionMapCollections", mDRpm);
                        upd.put("newSmsRolesPermissionMapCollections", mNRpm);
                        
                        SmsRoles updateRole = new SmsRoles();
                        updateRole.setRoleId(roleId);
                        updateRole.setRoleName(rName);
                        updateRole.setDescription(rDesc);

                        roleDAO.updateRole(updateRole);

                        hashMap.put("statusCode", "00");
                        hashMap.put("statusName", "Role Updated Successfully.");
                    }
                } else {
                    SmsRoles pRole = getRoleById(roleId);

                    Map<String, Object> upd = new LinkedHashMap<String, Object>();

                    upd.put("previousRoleName", pRole.getRoleName());
                    upd.put("newRoleName", r.getRoleName());
                    upd.put("previousRoleDescription", pRole.getDescription());
                    upd.put("newRoleDescription", r.getDescription());
                    upd.put("previousIntrash", pRole.getIntrash());
                    upd.put("newIntrash", r.getIntrash());
                    upd.put("previousSmsRolesPermissionMapCollections", mDRpm);

                    SmsRoles updateRole = new SmsRoles();
                    updateRole.setRoleId(roleId);
                    updateRole.setRoleName(rName);
                    updateRole.setDescription(rDesc);

                    roleDAO.updateRole(updateRole);

                    hashMap.put("statusCode", "00");
                    hashMap.put("statusName", "Role Updated Successfully.");
                }

            } else {
                hashMap.put("statusCode", "01");
                hashMap.put("statusName", "Role Name Exist.");
            }
        } else {
            hashMap.put("statusCode", "01");
            hashMap.put("statusName", "Role ID Does not Exist.");
        }
        
        return hashMap;

    }

    @Override
    public HashMap updateRole(SmsRoles role) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        boolean update = false;
        if (roleDAO.doesRoleExists(role.getRoleId())) {
            if (!roleDAO.doesRoleExists(role.getRoleId(), role.getRoleName())) {
                /**
                 * Check if the Role exist among the Role Map Permission
                 * Mapping, if so remove ALL associated Role permission mappings
                 */
                Collection<Map<String, Object>> mDRpm = new LinkedHashSet<Map<String, Object>>();
                if (rolePermissionMapDAO.doesRolePermissionMapByRoleIdExists(role.getRoleId())) {
                    System.out.println(" - RolePermissionMap to be Removed - " + role.getRoleId());
                    mDRpm = rolePermissionMapDAO.deleteRolePermissionMapByRoleIdExists(role.getRoleId());
                }

                /**
                 * Check if the Role Permission Mapping Collection is empty. If
                 * Yes, just update the role as it is and esp after removing the
                 * earlier existing mappings
                 */
                if (!role.getSmsRolesPermissionMapCollection().isEmpty()) {

                    Map<String, Object> mNRpm = new LinkedHashMap<String, Object>();
                    Collection<Map<String, Object>> ssRolesPerm = new LinkedHashSet<Map<String, Object>>();
                    for (SmsRolesPermissionMap rpm : role.getSmsRolesPermissionMapCollection()) {
                        /**
                         * Create a new Role Permission Map and save it to the
                         * DB using the Role roleId
                         */

                        if (entityPermissionDAO.doesEntityPermissionExists(rpm.getPermissionId().getPermissionId())) {

                            SmsRolesPermissionMap _rpm = new SmsRolesPermissionMap();
                            _rpm.setRoleId(role);
                            _rpm.setPermissionId(rpm.getPermissionId());

                            rolePermissionMapDAO.addRolePermissionMap(_rpm);
                            update = true;

                            Map<String, Object> _Mrpm = new LinkedHashMap<String, Object>();
                            _Mrpm.put("permissionId", _rpm.getPermissionId());
                            _Mrpm.put("roleId", role.getRoleId());

                            ssRolesPerm.add(_Mrpm);

                            mNRpm.put("newRolePermissionMapCollection", ssRolesPerm);

                        } else {
                            hashMap.put("statusCode", "01");
                            hashMap.put("statusName", " Permission ID - " + rpm.getPermissionId().getPermissionId()
                                    + " Does does not exist");
                            update = false;
                            break;
                        }

                    }

                    if (update) {

                        SmsRoles pRole = getRoleById(role.getRoleId());

                        Map<String, Object> upd = new LinkedHashMap<String, Object>();

                        upd.put("previousRoleName", pRole.getRoleName());
                        upd.put("newRoleName", role.getRoleName());
                        upd.put("previousRoleDescription", pRole.getDescription());
                        upd.put("newRoleDescription", role.getDescription());
                        upd.put("previousIntrash", pRole.getIntrash());
                        upd.put("newIntrash", role.getIntrash());
                        upd.put("previousSmsRolesPermissionMapCollections", mDRpm);
                        upd.put("newSmsRolesPermissionMapCollections", mNRpm);

                        roleDAO.updateRole(role);

                        hashMap.put("statusCode", "00");
                        hashMap.put("statusName", "Role Updated Successfully.");
                    }
                } else {

                    SmsRoles pRole = getRoleById(role.getRoleId());

                    Map<String, Object> upd = new LinkedHashMap<String, Object>();

                    upd.put("previousRoleName", pRole.getRoleName());
                    upd.put("newRoleName", role.getRoleName());
                    upd.put("previousRoleDescription", pRole.getDescription());
                    upd.put("newRoleDescription", role.getDescription());
                    upd.put("previousIntrash", pRole.getIntrash());
                    upd.put("newIntrash", role.getIntrash());
                    upd.put("previousSmsRolesPermissionMapCollections", mDRpm);
                    upd.put("newSmsRolesPermissionMapCollections", "");

                    roleDAO.updateRole(role);

                    hashMap.put("statusCode", "00");
                    hashMap.put("statusName", "Role Updated Successfully.");
                }

            } else {

                hashMap.put("statusCode", "01");
                hashMap.put("statusName", "Role Name Exist.");
            }
        } else {

            hashMap.put("statusCode", "01");
            hashMap.put("statusName", "Role ID Does not Exist.");
        }

        return hashMap;

    }

    @Override
    public HashMap deleteRole(int id) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        if (roleDAO.doesRoleExists(id)) {

            Map<String, Object> del = new LinkedHashMap<String, Object>();
            SmsRoles dRole = getRoleById(id);

            del.put("roleId", id);
            del.put("roleName", dRole.getRoleName());
            del.put("roleDescription", dRole.getDescription());
            del.put("roleIntrash", dRole.getIntrash());
            del.put("roleSmsRolesPermissionMapCollection", dRole.getSmsRolesPermissionMapCollection());

            roleDAO.deleteRole(id);

            hashMap.put("statusCode", "SUCCESS");
            hashMap.put("Success", "Role deleted successfully");

        } else {

            hashMap.put("statusCode", "FAIL");
            hashMap.put("roleId", "Does not Exist.");

        }
        return hashMap;
    }

    @Override
    public Map<String, Object> getAllRolesPermissions() {
        Map<String, Object> uLmap = new LinkedHashMap<String, Object>();

        List<Object> linkedList = new ArrayList<Object>();
        List<SmsRoles> roleList = roleDAO.getAllRolesPermissions();

        for (SmsRoles role : roleList) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();

            map.put("roleId", role.getRoleId());
            map.put("roleName", role.getRoleName());
            map.put("description", role.getDescription());
            map.put("creationDate", role.getCreationDate());
            map.put("intrash", role.getIntrash());
            
            Collection<SmsEntityPermission> ssEntityPermissionCollection = new LinkedHashSet<SmsEntityPermission>();
            for (SmsRolesPermissionMap rpm : role.getSmsRolesPermissionMapCollection()) {
                SmsEntityPermission ep = entityPermissionDAO.getEntityPermissionById(rpm.getPermissionId().getPermissionId());

                ssEntityPermissionCollection.add(ep);
                map.put("permissions", ssEntityPermissionCollection);
            }
            linkedList.add(map);
        }

        uLmap.put("roles", linkedList);
        return uLmap;
    }

    @Override
    public Map<String, Object> getRolesPermissionById(int id) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        SmsRoles role = roleDAO.getRoleById(id);
        
        if (role != null) {
            Collection<SmsEntityPermission> ssEntityPermissionCollection = new HashSet<SmsEntityPermission>();
            for (SmsRolesPermissionMap rpm : role.getSmsRolesPermissionMapCollection()) {
                SmsEntityPermission ep = entityPermissionDAO.getEntityPermissionById(rpm.getPermissionId().getPermissionId());

                ssEntityPermissionCollection.add(ep);
            }
            map.put("roleId", role.getRoleId());
            map.put("roleName", role.getRoleName());
            map.put("description", role.getDescription());
            map.put("creationDate", role.getCreationDate());
            map.put("intrash", role.getIntrash());
            map.put("permissions", ssEntityPermissionCollection);        
        }
        
        return map;
    }

}
