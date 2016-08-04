package com.gimnasio.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emimaster16
 */
public class GimnasioModel {

    private List<Object> listPersist;

    public GimnasioModel() {
        this.listPersist = new ArrayList();
    }
    
    

    public List<Object> getListPersist() {
        return listPersist;
    }

    public void setListPersist(List<Object> listPersist) {
        this.listPersist = listPersist;
    }

}
