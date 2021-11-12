package cn.com.sheep.http;

import lombok.Data;

@Data
public class ResponseReferenceData<R> {
    private String code;
    private String msg;
    private R data;
}
