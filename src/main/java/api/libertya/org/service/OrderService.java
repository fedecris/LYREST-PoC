package api.libertya.org.service;

import org.openXpertya.model.MOrder;
import org.openXpertya.model.X_C_Order;
import org.openXpertya.util.Env;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends GeneralService {

    @Override
    protected MOrder instantiateModelValidationPO(int ID) {
        return (MOrder)loadPO(ID, () -> new MOrder(Env.getCtx(), ID, null) );
    }

    @Override
    protected X_C_Order instantiateSerializablePO(int ID) {
        return (X_C_Order)loadPO(ID, () -> new X_C_Order(Env.getCtx(), ID, null) );
    }
}
