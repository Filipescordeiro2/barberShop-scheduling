package com.barberShop.scheduling.service.profissionalBarbearia;


import com.barberShop.scheduling.dto.request.ProfissionalBarbeariaRequest;
import com.barberShop.scheduling.dto.response.profissionalBarbearia.ProfissionalBarbeariaResponse;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.mapper.profissionalBarbearia.ProfissionalBarbeariaMapper;
import com.barberShop.scheduling.repository.profissionalBarbearia.ProfissionalBarbeariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalBarbeariaService {

    private final ProfissionalBarbeariaRepository profissionalBarbeariaRepository;

    public ProfissionalBarbeariaResponse createProfissionalBarbearia (ProfissionalBarbeariaRequest request){
        try {
            if(profissionalBarbeariaRepository.existsByProfissionalCpfAndBarbeariaCnpj
                    (request.getCpfProfissional(),request.getCnpjBarbearia())){
                throw new ServicosBarbeariaException("Profissional already linked in barbearia");
            }
            var profissionalBarbearia = ProfissionalBarbeariaMapper.INSTANCE.convertDtoToEntity(request);
            return ProfissionalBarbeariaMapper.INSTANCE.
                    convertEntityToProfissionalBarbeariaResponse(profissionalBarbeariaRepository.save(profissionalBarbearia));
        }catch (Exception e){
            throw new ServicosBarbeariaException("Error link profissional in  barbearia: " + e.getMessage());
        }
    }

}
