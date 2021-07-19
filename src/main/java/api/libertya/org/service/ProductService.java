package api.libertya.org.service;

import org.openXpertya.model.MProduct;
import org.openXpertya.model.X_M_Product;
import org.openXpertya.util.Env;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends GeneralService {

    @Override
    protected MProduct instantiateModelValidationPO(int ID) {
        return (MProduct)loadPO(ID, () -> new MProduct(Env.getCtx(), ID, null));
    }

    @Override
    protected X_M_Product instantiateSerializablePO(int ID) {
        return (X_M_Product)loadPO(ID, () -> new X_M_Product(Env.getCtx(), ID, null));
    }
}
