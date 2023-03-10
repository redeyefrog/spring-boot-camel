package com.redeyefrog.processor;

import com.redeyefrog.dto.RestRq;
import com.redeyefrog.utils.JsonUtils;
import com.redeyefrog.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Component("Json2Xml")
public class Json2XmlProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonStr = exchange.getIn().getBody(String.class);

        log.debug("json str: {}", jsonStr);

        RestRq rq = JsonUtils.toBean(jsonStr, RestRq.class);

        String xmlStr = XmlUtils.toXml(rq);

        log.debug("xml str: {}", xmlStr);

        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_XML);

        exchange.getIn().setHeader(Exchange.HTTP_METHOD, RequestMethod.POST);

        exchange.getIn().setBody(xmlStr);
    }

}
