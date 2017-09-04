/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.controllers;

import ke.co.telsadynamics.www.entities.SmsRoles;
import ke.co.telsadynamics.www.services.IRoleService;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ke.co.telsadynamics.www.daos.IRoleDAO;
import ke.co.telsadynamics.www.services.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Tracom
 */

@Controller
@RequestMapping("SMS")
public class RoleController {
    
    @Autowired
    private IRoleService roleService;
    
    @Autowired
    private IRoleDAO roleDAO;
    
    @GetMapping("role/{id}")
    public ResponseEntity<SmsRoles> getRoleById(@PathVariable("id") Integer id) {
        
        SmsRoles role = roleService.getRoleById(id);
        return new ResponseEntity<SmsRoles>(role, HttpStatus.OK);
    }

    @GetMapping("roles")
    public ResponseEntity<List<SmsRoles>> getAllRoles() {
        List<SmsRoles> list = roleService.getAllRoles();
        return new ResponseEntity<List<SmsRoles>>(list, HttpStatus.OK);
    }
    
    @GetMapping("get_role_permissions")
    public ResponseEntity<Map<String, Object>> getAllUsersTest() {
        Map<String, Object> map = roleService.getAllRolesPermissions();
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:8383")
    @GetMapping("get_role_permission/{id}")
    public ResponseEntity<Map<String, Object>> getUser$PermissionById(@PathVariable("id") String id) {
        if (Helpers.isStringInt(id)) {
            Map<String, Object> map = roleService.getRolesPermissionById(Integer.parseInt(id));

            boolean exist = roleDAO.doesRoleExists(Integer.parseInt(id));

            if (!exist == false) {
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } 
            else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    
    @CrossOrigin(origins = "http://localhost:8383")
    @PostMapping("hcreate_role")
    public ResponseEntity<HashMap> createTEntity(@RequestBody LinkedHashMap<String, Object> objMap) {
        HashMap<String, Object> role = roleService.createhRole(objMap);

        return new ResponseEntity<HashMap>(role, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:8383")    
    @PutMapping("updateh_role")
    public ResponseEntity<HashMap> updateRole(@RequestBody LinkedHashMap<String, Object> objMap) {
        HashMap<String, String> response = roleService.updatehRole(objMap);
        return new ResponseEntity<HashMap>(response, HttpStatus.OK);
    }

    @DeleteMapping("delete_role/{id}")
    public ResponseEntity<HashMap> deleteRole(@PathVariable("id") Integer id) {
        HashMap<String, String> response = roleService.deleteRole(id);
        return new ResponseEntity<HashMap>(response, HttpStatus.OK);
    }
        
    @PostMapping("create_role")
    public ResponseEntity<HashMap> createNewRole(@RequestBody String jsonString) {
        HashMap<String, String> resp = roleService.createRole(jsonString);
        return new ResponseEntity<HashMap>(resp, HttpStatus.OK);
    }
    
    
}
