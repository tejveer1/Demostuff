/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.Stuff;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sidhant
 */
@Stateless
public class StuffFacade extends AbstractFacade<Stuff> implements StuffFacadeRemote {

    @PersistenceContext(unitName = "DemoStuff-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StuffFacade() {
        super(Stuff.class);
    }
    
}
