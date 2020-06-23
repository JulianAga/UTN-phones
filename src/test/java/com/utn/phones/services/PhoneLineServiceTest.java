package com.utn.phones.services;

import static org.mockito.MockitoAnnotations.initMocks;

import com.utn.phones.repositories.PhoneLineRepository;
import org.junit.Before;
import org.mockito.Mock;

public class PhoneLineServiceTest {

  PhoneLineService phoneLineService;
  @Mock
  PhoneLineRepository phoneLineRepository;

  @Mock
  CityService cityService;

  @Mock
  ClientService clientService;

  @Before
  public void setUp() {
    initMocks(this);
    phoneLineService = new PhoneLineService(phoneLineRepository, cityService, clientService);
  }


}
