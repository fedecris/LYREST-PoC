package api.libertya.org.service;

import api.libertya.org.common.POLoaderInterface;
import org.openXpertya.model.PO;
import org.openXpertya.util.CLogger;

import java.util.Map;

public class GeneralService {

    public PO load(int ID, POLoaderInterface loader) throws IllegalArgumentException {
        PO po = loader.perform();
        if (po.getID() <= 0) {
            throw new IllegalArgumentException(String.format("ID %d does not exist", ID));
        }
        return po;
    }

    protected String save(PO po) throws IllegalStateException {
        if (!po.save()) {
            throw new IllegalStateException(String.format("Error updating: %s", CLogger.retrieveErrorAsString()));
        }
        return "Updated OK";
    }

    public String delete(PO po) throws IllegalArgumentException, IllegalStateException {
        if (!po.delete(true)) {
            throw new IllegalStateException(String.format("Error deleting: %s", CLogger.retrieveErrorAsString()));
        }
        return "Deleted OK";
    }

    public PO setValues(PO po, Map<String, String> values) {
        values.keySet().forEach(
                (k) -> po.set_Value(k, values.get(k))
        );
        return po;
    }
}
