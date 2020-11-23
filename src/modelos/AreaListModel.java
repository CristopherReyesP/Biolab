/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author francisco
 */
public class AreaListModel extends AbstractListModel {

    private final ArrayList<Area> lista = new ArrayList<>();

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        Area a = (Area) lista.get(index);
        return a.getId()+" "+a.getNombre();
    }

    public void addArea(Area a) {
        lista.add(a);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }

    public void eliminarArea(int index0) {
        lista.remove(index0);
        this.fireIntervalRemoved(index0, getSize(), getSize()+1);
    }

    public Area getArea(int index) {
        return (Area) lista.get(index);
    }

    public void limpiarLista() {
        lista.clear();
    }
}
