package api.libertya.org.service;


import org.openXpertya.model.MBPartner;
import org.openXpertya.util.Env;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BPartnerService extends GeneralService {


    public MBPartner retrieveByID(int ID) throws IllegalArgumentException {
        return loadBPartner(ID);
    }

    public String deleteByID(int ID) throws IllegalArgumentException, IllegalStateException {
        return delete(loadBPartner(ID));
    }

    public String updateByID(int ID, Map<String, String> values) {
        return save(setValues(loadBPartner(ID), values));
    }

    protected MBPartner loadBPartner(int ID) {
        return (MBPartner)load(ID, () -> new MBPartner(Env.getCtx(), ID, null));
    }



}
