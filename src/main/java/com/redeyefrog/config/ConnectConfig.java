package com.redeyefrog.config;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

@Configuration
public class ConnectConfig {

//    @Bean("clientConnectionManager")
//    public HttpClientConnectionManager clientConnectionManager() throws Exception {
//        BasicHttpClientConnectionManager clientConnectionManager = new BasicHttpClientConnectionManager(socketFactoryRegistry());
//
//        return clientConnectionManager;
//    }

    @Bean("clientConnectionManager")
    public HttpClientConnectionManager clientConnectionManager() throws Exception {
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry());

        clientConnectionManager.setMaxTotal(1000); // The maximum number of connections allowed across all routes.
        clientConnectionManager.setDefaultMaxPerRoute(1000);// The maximum number of connections allowed for a route that has not been specified otherwise by a call to setMaxPerRoute. Use setMaxPerRoute when you know the route ahead of time and setDefaultMaxPerRoute when you do not.

        return clientConnectionManager;
    }

    /**
     * Register Socket and SSL Socket
     * @return
     * @throws Exception
     */
    private Registry<ConnectionSocketFactory> socketFactoryRegistry() throws Exception {
        return RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", getSSLConnectionSocketFactory())
                .build();
    }

    /**
     * Ignore SSL Certificate
     * @return
     * @throws Exception
     */
    private SSLConnectionSocketFactory getSSLConnectionSocketFactory() throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
                .setProtocol("TLS")// default
                .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                .build();

        return new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
    }

}
