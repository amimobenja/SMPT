/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import ke.co.telsadynamics.www.daos.IEntityPermissionDAO;
import ke.co.telsadynamics.www.entities.SmsEntityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tracom
 */
@Service
public class EntityPermissionService implements IEntityPermissionService {

    @Autowired
    private IEntityPermissionDAO entityPermissionDAO;

    @Override
    public List<SmsEntityPermission> getAllEntityPermissions() {
        return entityPermissionDAO.getAllEntityPermissions();
    }

    @Override
    public SmsEntityPermission getEntityPermissionById(int entityPermissionId) {
        SmsEntityPermission obj = entityPermissionDAO.getEntityPermissionById(entityPermissionId);
        return obj;
    }

    @Override
    public HashMap updateEntityPermission(SmsEntityPermission entityPermission) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        if (entityPermissionDAO.doesEntityPermissionExists(entityPermission.getPermissionId())) {
            System.out.println("Entity Permission ID - " + entityPermission.getPermissionId());
            if (!entityPermissionDAO.doesEntityPermissionExists(entityPermission.getPermissionId(), entityPermission.getPermission())) {

                entityPermissionDAO.updateEntityPermission(entityPermission);

            } else {
                hashMap.put("STATUS", "FAIL");
                hashMap.put("permission", " Entity Permission Name currently Exist.");
            }

        } else {
            hashMap.put("STATUS", "FAIL");
            hashMap.put("permissionId", " Entity Permission ID Does Not Exist.");
        }

        return hashMap;
    }

    @Override
    public HashMap deleteEntityPermission(int entityPermissionId) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        if (entityPermissionDAO.doesEntityPermissionExists(entityPermissionId)) {

            entityPermissionDAO.deleteEntityPermission(entityPermissionId);

            hashMap.put("STATUS", "SUCCESS");
            hashMap.put("permissionId", " Entity Permission ID Deleted Successfully.");
        } else {

            hashMap.put("STATUS", "FAIL");
            hashMap.put("permissionId", " Permission ID Does Not Exist.");
        }
        return hashMap;
    }

    @Override
    public HashMap createEntityPermission(String jsonString) {
        HashMap<String, String> hashMap = new HashMap<String, String>();

        try {
            
            ObjectMapper mapper = new ObjectMapper();
            SmsEntityPermission entityPermission = mapper.readValue(jsonString, SmsEntityPermission.class);
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            if (!entityPermissionDAO.doesEntityPermissionExists(entityPermission.getPermission())) {

                SmsEntityPermission ep = new SmsEntityPermission();

                ep.setPermission(entityPermission.getPermission());
                ep.setCaption(entityPermission.getCaption());
                ep.setEntityId(entityPermission.getEntityId());

                entityPermissionDAO.addEntityPermission(ep);

            } else {
                hashMap.put("STATUS", "FAIL");
                hashMap.put("permission", " Entity Permission Name currently Exist.");
            }

        } catch (IOException ex) {
            hashMap.put("STATUS", "FAIL");
            hashMap.put("IOException", ex.toString());
            System.out.println("IOException - " + ex);
        }

        return hashMap;
    }

}
