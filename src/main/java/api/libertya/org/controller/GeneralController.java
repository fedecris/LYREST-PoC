package api.libertya.org.controller;

import api.libertya.org.common.LoginInfo;
import api.libertya.org.exception.AuthorizationException;
import api.libertya.org.common.ActivityInterface;
import api.libertya.org.util.UserManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.http.HttpStatus;
import org.openXpertya.OpenXpertya;
import org.openXpertya.model.PO;
import org.openXpertya.util.Env;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Properties;

public abstract class GeneralController {

    public static final String ENV_OXP_HOME 			= "OXP_HOME";
    public static final String ENV_OXP_API_LOG 			= "OXP_API_LOG";

    /** Info de acceso */
    LoginInfo loginInfo = null;



    /** Invocacion inicial en comun a toda operacion */
    protected void init(LoginInfo loginInfo) throws AuthorizationException, IllegalArgumentException {
        setClientOrg(loginInfo.getClientID(), loginInfo.getOrgID());
        startupEnvironment();
        UserManager.login(loginInfo, Env.getCtx());
    }

    /** Estructura en comun a toda operacion de la API */
    protected ResponseEntity<String> command(ActivityInterface method)  {
        try {
            init(loginInfo);
            return ResponseEntity.status(HttpStatus.SC_OK).body(method.perform());
        } catch (AuthorizationException ae) {
            return ResponseEntity.status(HttpStatus.SC_FORBIDDEN).body(ae.getMessage());
        } catch (IllegalArgumentException | IllegalStateException ie) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(ie.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /** Setea en el contexto las variables de compañía y organizacion */
    protected void setClientOrg(Integer clientID, Integer orgID) throws IllegalArgumentException
    {
        // Setear clientID y orgID en el contexto
        if (clientID == null || clientID < 0)
            throw new IllegalArgumentException("Valor clientID (" + clientID + ") incorrecto. ");
        if (orgID == null || orgID < 0)
            throw new IllegalArgumentException("Valor orgID (" + orgID + ") incorrecto. ");
        Env.setContext(Env.getCtx(), "#AD_Client_ID", clientID);
        Env.setContext(Env.getCtx(), "#AD_Org_ID", orgID);
    }

    /** Configura el entorno inical */
    protected void startupEnvironment() throws IllegalStateException
    {
        // Validar parametros recibidos
        if (Env.getCtx() == null)
            throw new IllegalStateException ("Error al iniciar el entorno. Contexto es null.");
        if (Env.getContext(Env.getCtx(), "#AD_Client_ID") == null)
            throw new IllegalStateException ("Error al iniciar el entorno. AD_Client_ID no esta configurada.");
        if (Env.getContext(Env.getCtx(), "#AD_Org_ID") == null)
            throw new IllegalStateException ("Error al iniciar el entorno. AD_Org_ID no esta configurada.");

        // Iniciar ambiente
        setOXPHome();
        if (!OpenXpertya.startup( false ))
            throw new RuntimeException ("Error al iniciar entorno (Hay conexión a Base de Datos?) ");
    }

    /** Directorio OXPHome */
    protected void setOXPHome() throws IllegalStateException {
        // OXP_HOME seteada?
        String oxpHomeDir = System.getenv(ENV_OXP_HOME);
        if (oxpHomeDir == null)
            throw new IllegalStateException ("La variable de entorno OXP_HOME no está seteada. ");
        // Cargar el entorno basico
        System.setProperty(ENV_OXP_HOME, oxpHomeDir);

        // OXP_WS_LOG seteada? Si no está seteada, utilizar la conf. de oxpHomeDir
        String oxpAPILog = System.getenv(ENV_OXP_API_LOG);
        System.setProperty(ENV_OXP_API_LOG, oxpAPILog == null ? oxpHomeDir : oxpAPILog);
    }

    /** PO -> JSon */
    protected String jsonSerialize(PO po) throws Exception {
        return new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .writerWithDefaultPrettyPrinter().writeValueAsString(po);
    }

    protected Map<String, String> jsonToMap(String data) throws Exception {
        return new ObjectMapper().readValue(data, Map.class);
    }

}
