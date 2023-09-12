package guru.springframework.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BreweryClientIT {
  @Autowired
  private BreweryClient breweryClient;

  @Test
  void getBeerById_takeUUIDClientId_returnClientDto() {
    BeerDto result = this.breweryClient.getBeerById(UUID.randomUUID());
    assertNotNull(result);
  }

  @Test
  void createBeerById_takeBeerDto_returnClientDto() {
    BeerDto newBeer = BeerDto.builder().beerName("new beer").build();
    BeerDto result = this.breweryClient.createBeer(newBeer);
    assertNotNull(result);
  }
}
