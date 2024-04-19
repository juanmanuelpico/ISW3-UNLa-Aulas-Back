package com.equipo6.aulasUnla.services.implementations;

import com.equipo6.aulasUnla.dtos.request.DocenteDTORequest;
import com.equipo6.aulasUnla.dtos.response.DocenteDTOResponse;
import com.equipo6.aulasUnla.entities.Docente;
import com.equipo6.aulasUnla.repositories.DocenteRepository;
import com.equipo6.aulasUnla.services.IDocenteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("docenteService")
public class DocenteService implements IDocenteService {

    @Autowired(required = true)
    private DocenteRepository docenteRepository;

    @Autowired(required = true)
    private ModelMapper modelMapper;

    @Override
    public boolean crearDocente(DocenteDTORequest dto) throws Exception {

        if(docenteRepository.findByDni(dto.getDni()) != null){
            throw new Exception("El dni para este docente ya existe");
        }
        if(docenteRepository.findByEmail(dto.getEmail()) != null) {
            throw new Exception("El mail ya se encuentra registrado, cambie de mail");
        }

        Docente docente = modelMapper.map(dto, Docente.class);
        docente.setFechaIngreso(LocalDate.now());
        docente.setLegajo("UNLa-"+dto.getId());
        docenteRepository.save(docente);
        return true;
    }

    @Override
    public boolean crearDocentes(List<DocenteDTORequest> dtosDocentes) throws Exception {
        for(DocenteDTORequest dto : dtosDocentes) {
            crearDocente(dto);
        }
        return true;
    }

    @Override
    public List<DocenteDTOResponse> obtenerDocentes() throws Exception {
        List<DocenteDTOResponse> listaDocentesDto = new ArrayList<>();
        List<Docente> listaDocentesEnt = docenteRepository.findAll();

        if (listaDocentesEnt.isEmpty()) {
            throw new Exception("La lista de docentes esta vacía.");
        }

        for (Docente d : listaDocentesEnt) {
            listaDocentesDto.add(modelMapper.map(d, DocenteDTOResponse.class));
        }

        return listaDocentesDto;
    }
}
