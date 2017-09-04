/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import ke.co.telsadynamics.www.daos.IEntityDAO;
import ke.co.telsadynamics.www.services.IEntityService;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 *
 * @author Tracom
 */
@Controller
@RequestMapping("SMS")
public class EntityController {

    @Autowired
    private IEntityService entityService;

    @Autowired
    private IEntityDAO entityDAO;
    

    @CrossOrigin(origins = "http://localhost:8383")
    @GetMapping("hget_entity/{id}")
    public ResponseEntity<HashMap> getEntityId(@PathVariable("id") String id) {
        if (isStringInt(id)) {
            HashMap entity = entityService.getEntityById(Integer.parseInt(id));

            boolean exist = entityDAO.doesEntityExists(Integer.parseInt(id));

            if (!exist == false) {
                return new ResponseEntity<HashMap>(entity, HttpStatus.OK);
            } 
            else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
    
    @CrossOrigin(origins = "http://localhost:8383")
    @GetMapping("hget_entities")
    public ResponseEntity<HashMap> getEntities() {
        HashMap<String, Object> entities = entityService.getEntities();

        return new ResponseEntity<HashMap>(entities, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:8383")
    @PutMapping("hupdate_entity")
    public ResponseEntity<HashMap> updateTEntity(@RequestBody LinkedHashMap<String, Object> objMap) {
        HashMap<String, Object> entities = entityService.updateTEntity(objMap);

        return new ResponseEntity<HashMap>(entities, HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "http://localhost:8383")
    @PostMapping("hcreate_entity")
    public ResponseEntity<HashMap> createTEntity(@RequestBody LinkedHashMap<String, Object> objMap) {
        HashMap<String, Object> entities = entityService.createTEntity(objMap);

        return new ResponseEntity<HashMap>(entities, HttpStatus.OK);
    }
    
    @DeleteMapping("delete_entity/{id}")
    public ResponseEntity<HashMap> deleteEntity(@PathVariable("id") Integer id) {
        HashMap<String, String> response = entityService.deleteEntity(id);
        return new ResponseEntity<HashMap>(response, HttpStatus.OK);
    }

    public boolean isStringInt(String s) {
        try {
            int a = Integer.parseInt(s);
            return a == (int) a;         
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
