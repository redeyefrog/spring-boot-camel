package com.redeyefrog.processor;

import com.redeyefrog.dto.RestRs;
import com.redeyefrog.enums.StatusCode;
import com.redeyefrog.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExceptionProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

        log.error(e.getMessage(), e);

        RestRs rs = new RestRs();
        rs.setCode(StatusCode.UNKNOWN.getCode());
        rs.setDesc(StatusCode.UNKNOWN.getDesc());
        String body = JsonUtils.toJson(rs);

//        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.BAD_REQUEST.value());
//        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        exchange.getIn().setBody(body);
    }

}
