package api.libertya.org.service;

import org.openXpertya.model.MBPartner;
import org.openXpertya.model.X_C_BPartner;
import org.openXpertya.util.Env;
import org.springframework.stereotype.Service;

@Service
public class BPartnerService extends GeneralService {

    @Override
    protected MBPartner instantiateModelValidationPO(int ID) {
        return (MBPartner)loadPO(ID, () -> new MBPartner(Env.getCtx(), ID, null));
    }

    @Override
    protected X_C_BPartner instantiateSerializablePO(int ID) {
        return (X_C_BPartner)loadPO(ID, () -> new X_C_BPartner(Env.getCtx(), ID, null));
    }
}
