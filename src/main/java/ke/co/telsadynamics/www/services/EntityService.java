/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import ke.co.telsadynamics.www.daos.IEntityDAO;
import ke.co.telsadynamics.www.daos.IEntityPermissionDAO;
import ke.co.telsadynamics.www.entities.SmsEntity;
import ke.co.telsadynamics.www.entities.SmsEntityPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tracom
 */
@Service
public class EntityService implements IEntityService {    
    
    @Autowired
    private IEntityDAO entityDAO;
    
    @Autowired
    private IEntityPermissionDAO entityPermissionDAO;
        
    @Override
    public HashMap getEntityById(int id) {
        
        HashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        SmsEntity obj = entityDAO.getEntityById(id);
        
        if (obj != null) {            
            hashMap.put("entityId", obj.getEntityId());
            hashMap.put("entityName", obj.getEntityName());
            hashMap.put("module", obj.getModule());                    
        
            Collection<HashMap<String, Object>> epCol = new LinkedHashSet<HashMap<String, Object> >();
            for (SmsEntityPermission epObj : obj.getSmsEntityPermissionCollection()) {
                HashMap<String, Object> epMap = new LinkedHashMap<String, Object>();
                epMap.put("permissionId", epObj.getPermissionId());
                epMap.put("permission", epObj.getPermission());
                epMap.put("caption", epObj.getCaption());
                epCol.add(epMap);
            }            
                        
            hashMap.put("entityPermissions", epCol);   
        }    
        
        return hashMap;
    }
    
    @Override
    public HashMap<String, Object> getEntities() {
        HashMap<String, Object> entities = new LinkedHashMap<String, Object>();
        List<SmsEntity> entityList = entityDAO.getAllEntities();        
        
        Collection<HashMap<String, Object>> eCol = new LinkedHashSet<HashMap<String, Object> >(); 
        
        for (SmsEntity e : entityList) {            
            HashMap<String, Object> entity = new LinkedHashMap<String, Object>();            
            
            entity.put("entityId", e.getEntityId());
            entity.put("entityName", e.getEntityName());
            entity.put("module", e.getModule());
            
            Collection<HashMap<String, Object>> epCol = new LinkedHashSet<HashMap<String, Object> >();
            for (SmsEntityPermission epObj : e.getSmsEntityPermissionCollection()) {
                HashMap<String, Object> epMap = new LinkedHashMap<String, Object>();
                epMap.put("permissionId", epObj.getPermissionId());
                epMap.put("permission", epObj.getPermission());
                epMap.put("caption", epObj.getCaption());
                epCol.add(epMap);
            }            
            
            entity.put("entityPermissions", epCol);
            eCol.add(entity);
            
        }
        
        entities.put("entities", eCol);
        
        return entities;
    }

    
    @Override
    public HashMap updateTEntity(LinkedHashMap<String, Object> objMap) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        int eId = Integer.parseInt(objMap.get("entityId").toString());
        String eName = objMap.get("entityName").toString();
        String eM = objMap.get("module").toString();
        Collection<LinkedHashMap<String, Object>> entityPermissions = (Collection<LinkedHashMap<String, Object>>) objMap.get("entityPermissions");
        
        SmsEntity e = entityDAO.getEntityById(eId);        
        System.out.println(" Entity ID - "+e.getEntityId()+" SUCCESS!!!"+entityPermissions.size());
        
        if (!entityDAO.doesEntityExists(eId, eName)) {
            Collection<Map<String, Object>> mDEp = new LinkedHashSet<Map<String, Object>>();
            if (entityPermissionDAO.doesEntityPermissionMapByEntityIdExists(eId)) {
                mDEp = entityPermissionDAO.deleteEntityPermissionMapByEntityIdExists(eId);
            }
            
            if (!entityPermissions.isEmpty()) {
                Map<String, Object> mNEp = new LinkedHashMap<String, Object>();                    
                Collection<Map<String, Object>> ePerm = new LinkedHashSet<Map<String, Object>>();                
                
                for (LinkedHashMap<String, Object> epm : entityPermissions) {
                    SmsEntityPermission _epm = new SmsEntityPermission();
                    _epm.setCaption(epm.get("caption").toString());
                    _epm.setEntityId(e);
                    _epm.setPermission(epm.get("permission").toString());
                    entityPermissionDAO.addEntityPermission(_epm);                    
                    
                    Map<String, Object> _Mepm = new LinkedHashMap<String, Object>();
                    _Mepm.put("permissionId", _epm.getPermissionId());
                    _Mepm.put("permission", _epm.getPermission());
                    _Mepm.put("entityId", e);
                    _Mepm.put("caption", _epm.getCaption());

                    ePerm.add(_Mepm);
                    
                    mNEp.put("newPermissions", ePerm);
                }
                
                
                
                SmsEntity _e = new SmsEntity();
                _e.setEntityId(eId);
                _e.setEntityName(eName);
                _e.setModule(eM);
                
                entityDAO.updateEntity(_e);                
                
                hashMap.put("statusCode", "00");
                hashMap.put("statusName", "Entity Updated Successfully"); 
            
            } else {
                SmsEntity _e = new SmsEntity();
                _e.setEntityId(eId);
                _e.setEntityName(eName);
                _e.setModule(eM);
                
                entityDAO.updateEntity(_e);                
                
                hashMap.put("statusCode", "00");
                hashMap.put("statusName", "Entity Updated Successfully");             
            }            
            
        } else {
            hashMap.put("statusCode", "01");
            hashMap.put("statusName", "Entity Name Exists."); 
        }
        return hashMap;
    }
        
    @Override
    public HashMap createTEntity(LinkedHashMap<String, Object> objMap) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        String eName = objMap.get("entityName").toString();
        String eM = objMap.get("module").toString();
        Collection<LinkedHashMap<String, Object>> entityPermissions = (Collection<LinkedHashMap<String, Object>>) objMap.get("entityPermissions");
         
        System.out.println(" Entity Name - "+eName+" SUCCESS!!!"+entityPermissions.size());
        
        if (!entityDAO.doesEntityExists(eName)) {            
            
            SmsEntity _e = new SmsEntity();
            _e.setEntityName(eName);
            _e.setModule(eM);

            entityDAO.addEntity(_e); 
                        
            if (!entityPermissions.isEmpty()) {
                Map<String, Object> mNEp = new LinkedHashMap<String, Object>();                    
                Collection<SmsEntityPermission> ePerm = new LinkedHashSet<SmsEntityPermission>();                
                
                for (LinkedHashMap<String, Object> epm : entityPermissions) {
                    SmsEntityPermission _epm = new SmsEntityPermission();
                    _epm.setCaption(epm.get("caption").toString());
                    _epm.setEntityId(_e);
                    _epm.setPermission(epm.get("permission").toString());
                    entityPermissionDAO.addEntityPermission(_epm);  
                    
                    /** A map that contains all the new entity permissionss
                    Map<String, Object> _Mepm = new LinkedHashMap<String, Object>();
                    _Mepm.put("permissionId", _epm.getPermissionId());
                    _Mepm.put("permission", _epm.getPermission());
                    _Mepm.put("entityId", _e);
                    _Mepm.put("caption", _epm.getCaption());**/

                    ePerm.add(_epm);
                    
                    mNEp.put("newPermissions", ePerm);
                }
                _e.setSmsEntityPermissionCollection(ePerm);
            
            }  
            
            hashMap.put("statusCode", "00");
            hashMap.put("statusName", "Entity Created Successfully"); 
            
        } else {
            hashMap.put("statusCode", "01");
            hashMap.put("statusName", "Entity Name Exists."); 
        }
        return hashMap;
    }

    @Override
    public HashMap deleteEntity(int id) {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        
        if (entityDAO.doesEntityExists(id)) {
                        
            entityDAO.deleteEntity(id);
            
            hashMap.put("statusCode", "00");
            hashMap.put("statusName", " Entity Deleted Successfully.");
        
        } else {
            
            hashMap.put("statusCode", "01");
            hashMap.put("statusName", " Entity Does Not Exist.");
        }
        return hashMap;
    }
    
    
}
