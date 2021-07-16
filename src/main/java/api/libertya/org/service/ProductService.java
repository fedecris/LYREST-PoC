package api.libertya.org.service;

import org.openXpertya.model.MProduct;
import org.openXpertya.util.Env;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends GeneralService {

    @Override
    protected MProduct loadPOSpecific(int ID) {
        return (MProduct)loadPO(ID, () -> new MProduct(Env.getCtx(), ID, null));
    }
}
