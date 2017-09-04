/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import java.util.List;
import ke.co.telsadynamics.www.entities.SmsEntity;

/**
 *
 * @author Tracom
 */
public interface IEntityDAO {
    
    List<SmsEntity> getAllEntities();

    SmsEntity getEntityById(int entityId);

    void addEntity(SmsEntity entity);

    void updateEntity(SmsEntity entity);

    void deleteEntity(int entityId);

    boolean doesEntityExists(int entityId);

    boolean doesEntityExists(String entityName);

    boolean doesEntityExists(int entityId, String entityName);
    
}
