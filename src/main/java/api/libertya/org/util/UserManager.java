package api.libertya.org.util;

import api.libertya.org.common.LoginInfo;
import api.libertya.org.exception.AuthorizationException;
import org.openXpertya.model.MUser;

import java.util.Properties;

public class UserManager {

    /** Usuario autorizado? */
    public static void login(LoginInfo loginInfo, Properties ctx) throws AuthorizationException {
        MUser user = MUser.get(ctx, loginInfo.getUserName(), loginInfo.getPassword());
        if (user==null) {
            throw new AuthorizationException();
        }
    }
}
