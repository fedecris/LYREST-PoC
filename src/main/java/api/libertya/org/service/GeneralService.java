package api.libertya.org.service;

import api.libertya.org.common.POLoaderInterface;
import org.openXpertya.model.PO;
import org.openXpertya.util.CLogger;

import java.util.Map;

public class GeneralService {

    protected PO loadPO(int ID, POLoaderInterface loader) throws IllegalArgumentException {
        PO po = loader.getPOInstance();
        /* ID es mayor a cero solo para updates */
        if (ID > 0 && po.getID() <= 0) {
            throw new IllegalArgumentException(String.format("ID %d does not exist", ID));
        }
        return po;
    }

    protected String savePO(PO po) throws IllegalStateException {
        if (!po.save()) {
            throw new IllegalStateException(String.format("Error updating: %s", CLogger.retrieveErrorAsString()));
        }
        return String.valueOf(po.getID());
    }

    protected String deletePO(PO po) throws IllegalArgumentException, IllegalStateException {
        if (!po.delete(true)) {
            throw new IllegalStateException(String.format("Error deleting: %s", CLogger.retrieveErrorAsString()));
        }
        return String.valueOf(po.getID());
    }

    protected PO setValues(PO po, Map<String, String> values) {
        values.keySet().forEach(
                (k) -> po.set_Value(k, values.get(k))
        );
        return po;
    }
}
