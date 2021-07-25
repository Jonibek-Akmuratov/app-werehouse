package uz.pdp.jokker.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.jokker.appwerehouse.entity.Measurement;
import uz.pdp.jokker.appwerehouse.payload.ApiResponce;
import uz.pdp.jokker.appwerehouse.payload.MeasurementDto;
import uz.pdp.jokker.appwerehouse.repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;


    public ApiResponce add(MeasurementDto measurementDto) {
        if (measurementRepository.existsByName(measurementDto.getName()))
            return new ApiResponce("This Measurment All Ready exists", false);
        Measurement measurement = new Measurement(
                measurementDto.getName()
        );
        measurementRepository.save(measurement);
        return new ApiResponce("Measurment saved ", true, measurement);
    }

    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }

    public Measurement getOne(Integer id) {
        if (measurementRepository.findById(id).isPresent()) {
            return measurementRepository.findById(id).get();
        }
        return null;
    }

    public ApiResponce deleted(Integer id) {
        if (measurementRepository.findById(id).isPresent()) {
            measurementRepository.deleteById(id);
            return new ApiResponce("Measurement Deleted", true);
        } else {
            return new ApiResponce("Measurement not found", false,id);
        }
    }
}
