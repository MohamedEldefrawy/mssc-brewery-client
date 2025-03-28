package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import java.util.UUID;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
public class BeerClient {
  private String apiHost;
  public final static String BEER_PATH_V1 = "/api/v1/beer/";
  private final RestTemplate restTemplate;

  public BeerClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public BeerDto getBeerById(UUID id) {
    return restTemplate.getForObject(apiHost + BEER_PATH_V1 + id.toString(), BeerDto.class);
  }

  public BeerDto createBeer(BeerDto beerDto) {
    return restTemplate.postForObject(apiHost + BEER_PATH_V1, beerDto, BeerDto.class);
  }

  public boolean updateBeer(UUID id, BeerDto beerDto) {
    try {
      restTemplate.put(apiHost + BEER_PATH_V1 + id.toString(), beerDto);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  public boolean deleteBeer(UUID id) {
    try {
      restTemplate.delete(apiHost + BEER_PATH_V1 + id.toString());
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }
}
