package pe.gob.minjus.sisarb.rest.temp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pe.gob.minjus.sisarb.exception.EntityWebServiceRestException;
import pe.gob.minjus.sisarb.rest.model.CommonUbigeo;
import pe.gob.minjus.sisarb.rest.model.CommonWsResponse;

import java.util.List;

@Component
@Slf4j
public class RestCommonWs {

    @Value("${servicio.common.url}")
    String urlWsCommon;

    public CommonWsResponse<List<CommonUbigeo>> getUbigeo(String codigoDepartamento,String codigoProvincia) {
        CommonWsResponse<List<CommonUbigeo>> commonWsResponse = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = urlWsCommon + "/maestro/ubigeo";
            if (codigoDepartamento!=null) {
                url += "/" + codigoDepartamento;
                if (codigoProvincia!=null) {
                    url += "/" + codigoProvincia;
                }
            }
            commonWsResponse = restTemplate.getForObject(url, CommonWsResponse.class);
        }
        catch (ResourceAccessException e) {
            log.info("Causa : " + e.getMostSpecificCause());
            log.info("Se perdio conexion con COMMON_wS : " + e.getMessage());
            throw  new EntityWebServiceRestException(e.getMostSpecificCause().toString());
        }
        catch (Exception e) {
            log.info("Error desconocido : " + e.getMessage());
            throw  new EntityWebServiceRestException(e.getMessage());
        }
        return commonWsResponse;
    }

}
