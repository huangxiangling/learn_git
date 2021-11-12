package cn.com.sheep.http;

import lombok.Data;
import sun.security.provider.MD5;

/**
 * 请求协议头
 */
@Data
public class ProtocolParameter {

    private String sign;
    private String signType = "MD5";
    private String inputCharset = "UTF-8";
    private String serviceVersion = "1.0";
    private String signMask;
}
