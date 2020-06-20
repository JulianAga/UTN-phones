package com.utn.phones.services;

import com.utn.phones.dto.BetweenDatesDto;
import com.utn.phones.dto.CallRequestDto;
import com.utn.phones.dto.MostCalledDto;
import com.utn.phones.exceptions.callExceptions.CallNotFoundException;
import com.utn.phones.exceptions.dateExceptions.InvalidDateException;
import com.utn.phones.model.Call;
import com.utn.phones.projections.MostCalled;
import com.utn.phones.repositories.CallRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {

  private CallRepository callRepository;

  @Autowired
  public CallService(CallRepository callRepository) {
    this.callRepository = callRepository;
  }

  public List<Call> findAll() {
    return callRepository.findAll();
  }

  public List<MostCalledDto> findMostCalledCities(Integer id) throws CallNotFoundException {
    return Optional.ofNullable(callRepository.findMostCalledCities(id))
        .orElseThrow(CallNotFoundException::new);
  }

  public List<Call> findBetweenDates(Integer userId, BetweenDatesDto callBetweenDatesDto)
      throws InvalidDateException {
    if (callBetweenDatesDto.getEnd().isBefore(callBetweenDatesDto.getStart())) {
      throw new InvalidDateException();
    } else {
      return callRepository
          .findAllByOriginLineClientIdAndDateBetween(userId, callBetweenDatesDto.getStart(),
              callBetweenDatesDto.getEnd());
    }
  }

  public List<Call> findCallsFromClient(Integer id) throws CallNotFoundException {
    return Optional.ofNullable(this.callRepository.findAllByOriginLineClientId(id))
        .orElseThrow(CallNotFoundException::new);
  }

  /***
   * Agregado de llamadas
   * @param callRequestDto Dto with four parameters destiny number, origin number, duration, and date
   */
  public Call saveDto(CallRequestDto callRequestDto) {
    Call call = Call.builder().originNumber(callRequestDto.getOriginNumber())
        .destinyNumber(callRequestDto.getDestinyNumber()).duration(callRequestDto.getDuration())
        .date(callRequestDto.getDate())
        .build();
    return callRepository.save(call);
  }

}