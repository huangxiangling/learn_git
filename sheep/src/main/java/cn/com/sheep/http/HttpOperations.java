package cn.com.sheep.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class HttpOperations {

    private static final String SUCCESS = "0000";
    private String host;
    private int port;

    public <T> T postForObject(String path, String request) {
        return postForObject(path, request, Object.class);
    }

    public <T> T postForObject(String path, String request, Class clazz) {

        T result = null;
        try {
            String requestBody = JSON.toJSONString(request);
            // 使用RequestUtils工具类调用
            String responseBody = RequestUtils.fetchData(host, port, path, requestBody);
            ResponseReferenceData<T> response = JSON.parseObject(responseBody, new TypeReference<ResponseReferenceData<T>>(clazz) {
            });
            if (StringUtils.equals(SUCCESS, response.getCode())) {
                result = response.getData();
            } else {
                log.warn("response error [code: {}, msg : {}], [request: {}]", response.getCode(), response.getMsg(), request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
