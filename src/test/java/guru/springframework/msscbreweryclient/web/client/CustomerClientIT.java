package guru.springframework.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerClientIT {
  @Autowired
  private CustomerClient customerClient;

  @Test
  void getCustomerById_takeUUID_returnCustomerDto() {
    CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());
    assertNotNull(customerDto);
  }

  @Test
  void createCustomer_takeCustomerDto_returnCustomerDto() {
    CustomerDto newCustomer = CustomerDto.builder().id(UUID.randomUUID()).name("new customer").build();
    CustomerDto createdCustomer = customerClient.createCustomer(newCustomer);
    assertNotNull(createdCustomer);
  }

  @Test
  void updateCustomer_takeCustomerDtoAndUUID_returnNoContentResponse() {
    CustomerDto newBeer = CustomerDto.builder().name("updated customer").build();
    boolean result = this.customerClient.updateCustomer(UUID.randomUUID(), newBeer);
    assertTrue(result);
  }

  @Test
  void deleteCustomer_takeUUID_returnNoContentResponse() {
    boolean result = this.customerClient.deleteCustomer(UUID.randomUUID());
    assertTrue(result);
  }
}
