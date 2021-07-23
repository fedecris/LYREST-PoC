package api.libertya.org.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.openXpertya.model.PO;

import java.util.Map;

public class JSON {

    /** PO -> JSon */
    public static String serialize(Object object) throws Exception {
        return new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }

    /** JSon -> Map */
    public static Map<String, String> toMap(String data) throws Exception {
        return new ObjectMapper().readValue(data, Map.class);
    }

}
