package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CustomerClient {
  @Value("${sfg.brewery.apiHost}")
  private String apiHost;
  private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
  private final RestTemplate restTemplate;

  public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public CustomerDto getCustomerById(UUID uuid) {
    return this.restTemplate.getForObject(apiHost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
  }

  public CustomerDto createCustomer(CustomerDto customerDto) {
    return this.restTemplate.postForObject(apiHost + CUSTOMER_PATH_V1, customerDto, CustomerDto.class);
  }

  public boolean updateCustomer(UUID id, CustomerDto customerDto) {
    try {
      restTemplate.put(apiHost + CUSTOMER_PATH_V1 + id.toString(), customerDto);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }
  public boolean deleteCustomer(UUID id) {
    try {
      restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + id.toString());
      return true;
    } catch (Exception exception) {
      return false;
    }
  }
}
