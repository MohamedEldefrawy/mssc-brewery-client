package guru.springframework.msscbreweryclient.web.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {

  @Value("${restTemplate.config.timeout}")
  private String timeout;
  @Value("${restTemplate.config.maxConnections}")
  private String maxConnections;
  @Value("${restTemplate.config.defaultMaxConnections}")
  private String defaultMaxConnections;

  public ClientHttpRequestFactory clientHttpRequestFactory() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(Integer.parseInt(maxConnections));
    connectionManager.setDefaultMaxPerRoute(Integer.parseInt(defaultMaxConnections));

    RequestConfig requestConfig = RequestConfig
        .custom()
        .setConnectionRequestTimeout(Integer.parseInt(timeout))
        .setSocketTimeout(Integer.parseInt(timeout))
        .build();

    CloseableHttpClient httpClient = HttpClients
        .custom()
        .setConnectionManager(connectionManager)
        .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
        .setDefaultRequestConfig(requestConfig)
        .build();

    return new HttpComponentsClientHttpRequestFactory(httpClient);
  }

  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.setRequestFactory(this.clientHttpRequestFactory());
  }
}