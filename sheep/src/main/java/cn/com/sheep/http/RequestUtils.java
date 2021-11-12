package cn.com.sheep.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * http请求处理
 */
@Slf4j
public class RequestUtils {

    public static String fetchData(String host, int port, String path, String requestBody) throws IOException {

        log.info("request body:{} ", requestBody);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            URI uri = new URI("http", null, host, port, path, null, null);
            HttpPost httpPost = new HttpPost(uri);
            if (StringUtils.isNotEmpty(requestBody)) {
                StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity());

        } catch (URISyntaxException e) {
            log.error("uri error :{}", e);
        } finally {

            if (httpClient != null) {
                httpClient.close();
            }
            if (response != null) {
                response.close();
            }
        }
        return null;
    }
}
