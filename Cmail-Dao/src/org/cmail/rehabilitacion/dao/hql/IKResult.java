/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cmail.rehabilitacion.dao.hql;

import java.util.List;
import org.cmail.rehabilitacion.modelo.DomainEntity;

/**
 *
 * @author Usuario
 */
public interface IKResult<T extends DomainEntity> { 
    public List<T> list();    
    public T unique();
    public T first();
    public Long count();
}


