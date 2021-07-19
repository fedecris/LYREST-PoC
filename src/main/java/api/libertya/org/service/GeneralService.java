package api.libertya.org.service;

import api.libertya.org.common.POLoaderInterface;
import org.openXpertya.model.PO;
import org.openXpertya.util.CLogger;

import java.util.Map;

public abstract class GeneralService {

    /**
     * Metodo a implementar en las subclases para entidades a insertar/modificar/eliminar
     * @param ID identificador del objeto a modificar/eliminar o 0 en caso de insercion
     * @return un PO especifico, segun subclase por ejemplo MBPartner, MProduct, etc.
     *          apoyandose en loadPO a fin de validar la existencia del objeto
     */
    protected abstract PO instantiateModelValidationPO(int ID);

    /**
     * Metodo a implementar en las subclases para recuperacion de entidades a serializar
     * @param ID identificador del objeto a recuperar
     * @return un PO especifico, segun subclase por ejemplo X_C_BPartner, X_M_Product, etc.
     *          apoyandose en loadPO a fin de validar la existencia del objeto
     */
    protected abstract PO instantiateSerializablePO(int ID);

    /** Recupera una entidad por su ID */
    public PO retrieveByID(int ID) throws IllegalArgumentException {
        return instantiateSerializablePO(ID);
    }

    /** Elimina una entidad por su ID */
    public String deleteByID(int ID) throws IllegalArgumentException, IllegalStateException {
        return deletePO(instantiateModelValidationPO(ID));
    }

    /** Elimina una entidad por su ID */
    public String updateByID(int ID, Map<String, String> values) {
        return savePO(setValues(instantiateModelValidationPO(ID), values));
    }

    /** Inserta una nueva entidad */
    public String insert(Map<String, String> values) {
        return savePO(setValues(instantiateModelValidationPO(0), values));
    }

    /** Creacion / Recuperacion de un objeto segun su ID */
    protected PO loadPO(int ID, POLoaderInterface loader) throws IllegalArgumentException {
        PO po = loader.getPOInstance();
        /* ID es mayor a cero solo para updates */
        if (ID > 0 && po.getID() <= 0) {
            throw new IllegalArgumentException(String.format("ID %d does not exist", ID));
        }
        return po;
    }

    /** Persistir objeto
     * @return ID del objeto insertado / actualizado si todo OK
     */
    private String savePO(PO po) throws IllegalStateException {
        if (!po.save()) {
            throw new IllegalStateException(String.format("Error updating: %s", CLogger.retrieveErrorAsString()));
        }
        return String.valueOf(po.getID());
    }

    /** Eliminar objeto
     * @return ID del objeto eliminado si todo OK
     */
    private String deletePO(PO po) throws IllegalArgumentException, IllegalStateException {
        if (!po.delete(true)) {
            throw new IllegalStateException(String.format("Error deleting: %s", CLogger.retrieveErrorAsString()));
        }
        return String.valueOf(po.getID());
    }

    /** Asignacion de valores en un PO a partir del map recibido */
    private PO setValues(PO po, Map<String, String> values) {
        values.keySet().forEach(
                (k) -> po.set_Value(k, values.get(k))
        );
        return po;
    }
}
