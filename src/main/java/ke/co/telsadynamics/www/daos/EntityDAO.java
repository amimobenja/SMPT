/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.telsadynamics.www.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import ke.co.telsadynamics.www.entities.SmsEntity;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Tracom
 */

@Transactional
@Repository
public class EntityDAO implements IEntityDAO {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SmsEntity> getAllEntities() {
        String hql = "FROM SmsEntity ORDER BY entity_id";
        return (List<SmsEntity>) entityManager.createQuery(hql).getResultList();        
    }

    @Override
    public SmsEntity getEntityById(int entityId) {
        return entityManager.find(SmsEntity.class, entityId);        
    }

    @Override
    public void addEntity(SmsEntity entity) {
        entityManager.persist(entity);        
    }

    @Override
    public void updateEntity(SmsEntity entity) {
        SmsEntity e = getEntityById(entity.getEntityId());
        
        e.setEntityName(entity.getEntityName());
        e.setModule(entity.getModule());
        
        entityManager.flush();        
    }

    @Override
    public void deleteEntity(int entityId) {
        entityManager.remove(getEntityById(entityId));        
    }

    @Override
    public boolean doesEntityExists(int entityId) {
        String hql = "FROM SmsEntity WHERE entity_id = ?";
        int count = entityManager.createQuery(hql).setParameter(1, entityId).getResultList().size();
        
        return count > 0;        
    }

    @Override
    public boolean doesEntityExists(String entityName) {
        String hql = "FROM SmsEntity WHERE entity_name = ?";
        int count = entityManager.createQuery(hql).setParameter(1, entityName).getResultList().size();
        
        return count > 0;        
    }

    @Override
    public boolean doesEntityExists(int entityId, String entityName) {
        String hql = "FROM SmsEntity WHERE entity_id != ? AND entity_name = ?";
        int count = entityManager.createQuery(hql).setParameter(1, entityId).setParameter(2, entityName).getResultList().size();
        
        return count > 0;          
    }
    
}
