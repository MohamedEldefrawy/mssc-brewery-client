package guru.springframework.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeerClientIT {
  @Autowired
  private BeerClient beerClient;

  @Test
  void getBeerById_takeUUIDClientId_returnClientDto() {
    BeerDto result = this.beerClient.getBeerById(UUID.randomUUID());
    assertNotNull(result);
  }

  @Test
  void createBeerById_takeBeerDto_returnClientDto() {
    BeerDto newBeer = BeerDto.builder().beerName("new beer").build();
    BeerDto result = this.beerClient.createBeer(newBeer);
    assertNotNull(result);
  }

  @Test
  void updateBeerById_takeBeerDtoAndUUID_returnNoContentResponse() {
    BeerDto newBeer = BeerDto.builder().beerName("updated beer").build();
    boolean result = this.beerClient.updateBeer(UUID.randomUUID(), newBeer);
    assertTrue(result);
  }

  @Test
  void deleteBeerById_takeUUID_returnNoContentResponse() {
    boolean result = this.beerClient.deleteBeer(UUID.randomUUID());
    assertTrue(result);
  }
}
