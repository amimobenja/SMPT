/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.controllers;

import java.util.HashMap;
import java.util.List;
import ke.co.telsadynamics.www.entities.SmsEntityPermission;
import ke.co.telsadynamics.www.services.IEntityPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tracom
 */

@Controller
@RequestMapping("SMS")
public class EntityPermissionController {
    
    @Autowired
    private IEntityPermissionService entityPermissionService;
    
    @GetMapping("permission/{id}")
    public ResponseEntity<SmsEntityPermission> getEntityPermissionById(@PathVariable("id") int id) {
        SmsEntityPermission entityPermission = entityPermissionService.getEntityPermissionById(id);
        return new ResponseEntity<SmsEntityPermission>(entityPermission, HttpStatus.OK);
    }
    
    @GetMapping("permissions")
    public ResponseEntity<List<SmsEntityPermission>> getAllEntityPermissions() {
        List<SmsEntityPermission> list = entityPermissionService.getAllEntityPermissions();
        return new ResponseEntity<List<SmsEntityPermission>>(list, HttpStatus.OK);
    }
    
    @PutMapping("permission")
    public ResponseEntity<HashMap> updateEntityPermission(@RequestBody SmsEntityPermission entityPermission) {
        HashMap<String, String> response = entityPermissionService.updateEntityPermission(entityPermission);
        return new ResponseEntity<HashMap>(response, HttpStatus.OK);
    }
    
    @DeleteMapping("permission/{id}")
    public ResponseEntity<HashMap> deleteEntityPermission(@PathVariable("id") int id) {         
        HashMap<String, String> response = entityPermissionService.deleteEntityPermission(id);
        return new ResponseEntity<HashMap>(response, HttpStatus.OK);
    }
    
    @PostMapping("create_permission")
    public ResponseEntity<HashMap> createEntityPermission(@RequestBody String jsonString) {        
        HashMap<String, String> response = entityPermissionService.createEntityPermission(jsonString);        
        return new ResponseEntity<HashMap>(response, HttpStatus.OK);        
    }
    
}
