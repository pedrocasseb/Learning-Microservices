package br.com.pedrocasseb.service.impl;

import br.com.pedrocasseb.mapper.CityMapper;
import br.com.pedrocasseb.model.City;
import br.com.pedrocasseb.payload.request.CityRequest;
import br.com.pedrocasseb.payload.response.CityResponse;
import br.com.pedrocasseb.repository.CityRepository;
import br.com.pedrocasseb.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest request) throws Exception {
        if (cityRepository.existsByCityCode(request.getCityCode())) {
            throw new Exception("city with given code already exists");
        }

        City city = CityMapper.toEntity(request);
        City result = cityRepository.save(city);

        return CityMapper.toResponse(result);
    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("city not exists with given id")
        );
        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("city not exists with given id")
        );

        if (cityRepository.existsByCityCodeAndIdNot(request.getCityCode(), id)) {
            throw new Exception("City code already exists");
        }

        City updatedCity = cityRepository.save(CityMapper.updateEntity(city, request));
        return CityMapper.toResponse(updatedCity);
    }

    @Override
    public void deleteCity(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("city not exists with given id")
        );
        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword, pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable).map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
