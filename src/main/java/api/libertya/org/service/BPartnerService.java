package api.libertya.org.service;


import org.openXpertya.model.MBPartner;
import org.openXpertya.util.Env;
import org.springframework.stereotype.Service;

@Service
public class BPartnerService extends GeneralService {

    protected MBPartner loadPOSpecific(int ID) {
        return (MBPartner)loadPO(ID, () -> new MBPartner(Env.getCtx(), ID, null));
    }

}
