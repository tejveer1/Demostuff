/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.Stuff;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author sidhant
 */
@Remote
public interface StuffFacadeRemote {

    void create(Stuff stuff);

    void edit(Stuff stuff);

    void remove(Stuff stuff);

    Stuff find(Object id);

    List<Stuff> findAll();

    List<Stuff> findRange(int[] range);

    int count();
    
}
