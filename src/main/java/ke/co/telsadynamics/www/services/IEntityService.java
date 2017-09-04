/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.services;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author Tracom
 */
public interface IEntityService {
    
    HashMap getEntityById(int id);
    
    HashMap<String, Object> getEntities();
    
    HashMap updateTEntity(LinkedHashMap<String, Object> objMap);
    
    HashMap createTEntity(LinkedHashMap<String, Object> objMap);

    HashMap deleteEntity(int id);    
    
}
