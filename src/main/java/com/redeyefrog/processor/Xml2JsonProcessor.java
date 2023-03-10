package com.redeyefrog.processor;

import com.redeyefrog.dto.RestRs;
import com.redeyefrog.utils.JsonUtils;
import com.redeyefrog.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Component("Xml2Json")
public class Xml2JsonProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String xmlStr = exchange.getIn().getBody(String.class);

        log.debug("xml str: {}", xmlStr);

        RestRs rs = XmlUtils.toBean(xmlStr, RestRs.class);

        String jsonStr = JsonUtils.toJson(rs);

        log.debug("json str: {}", jsonStr);

        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        exchange.getIn().setBody(jsonStr);
    }

}
