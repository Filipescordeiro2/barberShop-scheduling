package com.barberShop.scheduling.utils.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.domain.Cliente;
import com.barberShop.scheduling.dto.request.agenda.AgendaRequest;
import com.barberShop.scheduling.dto.request.agenda.AgendaRequestWithoutJornada;
import com.barberShop.scheduling.dto.response.agenda.AgendaResponse;
import com.barberShop.scheduling.enums.JornadaEnum;
import com.barberShop.scheduling.enums.StatusAgenda;
import com.barberShop.scheduling.exception.ServicosBarbeariaException;
import com.barberShop.scheduling.mapper.agenda.AgendaMapper;
import com.barberShop.scheduling.repository.agenda.AgendaRepository;
import com.barberShop.scheduling.repository.agendamento.AgendamentoRepository;
import com.barberShop.scheduling.repository.barbearia.BarbeariaRepository;
import com.barberShop.scheduling.repository.profissional.ProfissionalRepository;
import com.barberShop.scheduling.repository.servicosBarbearia.ServicosBarbeariaRepository;
import com.barberShop.scheduling.validation.agenda.AgendaScheduleConflictValidator;
import com.barberShop.scheduling.validation.agenda.AgendaValidation;
import com.barberShop.scheduling.validation.agenda.ActiveStatusValidator;
import com.barberShop.scheduling.validation.agenda.ProfessionalBarberShopLinkValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgendaCreatedManagerImpl implements AgendaCreatedManager {

    private final ProfissionalRepository profissionalRepository;
    private final BarbeariaRepository barbeariaRepository;
    private final ServicosBarbeariaRepository servicosBarbeariaRepository;
    private final AgendaRepository agendaRepository;
    private final AgendamentoRepository agendamentoRepository;
    private final AgendaValidation agendaValidation;
    private final ScheduleGenerator scheduleGenerator;

    @Override
    public void abrirAgendaDoProfissional(Cliente cliente) {
        log.info("Inicializado o metodo de [abrirAgendaDoProfissional]");
        var agendamentos = agendamentoRepository.findByCliente(cliente);

        agendamentos.forEach(agendamento -> {
            var agenda = agendaRepository.findById(agendamento.getAgenda().getId())
                    .orElseThrow(() -> new RuntimeException("Agenda not found"));

            agenda.setStatusAgenda(StatusAgenda.ABERTO);
            agendaRepository.save(agenda);
            log.info("Agenda reaberta para o profissional: {}, Dia: {}, Hora: {}, ID: {}",
                    agenda.getProfissional().getNameProfissional(),
                    agenda.getDate(),
                    agenda.getTime(), agenda.getId());
        });
        log.info("Finalizado o metodo de [abrirAgendaDoProfissional]");
    }

    @Override
    public List<AgendaResponse> generateScheduleForPeriod(AgendaRequestWithoutJornada request,
                                                          LocalTime startTime, LocalTime endTime,
                                                          JornadaEnum jornada) {

        log.info("Inicializado o metodo de [generateScheduleForPeriod] -" +
                        "Request: {}," +
                        " StartTime: {}," +
                        " EndTime: {}," +
                        " Jornada: {}",
                                request,
                                startTime,
                                endTime,
                                jornada);

        List<Agenda> agendas = new ArrayList<>();

        List<LocalTime> times = scheduleGenerator.generateTimes(startTime, endTime);

        times.forEach(time -> {
            Agenda agenda = createAgendaEntity(request, time, jornada);

            agendaValidation.validateWithSpecificValidators(agenda, List.of(
                    ProfessionalBarberShopLinkValidator.class,
                    ActiveStatusValidator.class,
                    AgendaScheduleConflictValidator.class
            ));

            agendas.add(agenda);
            log.info("Agenda criada - ID: {}", agenda.getId());
        });

        if (agendas.isEmpty()) {
            throw new ServicosBarbeariaException("No available slots for the requested period.");
        }

        agendaRepository.saveAll(agendas);
        log.info("Finalizado o metodo de [generateScheduleForPeriod] - Agendas Criadas: {}", agendas);
        return AgendaMapper.INSTANCE.convertEntityListToResponseList(agendas);
    }

    @Override
    public AgendaResponse createSingleSchedule(AgendaRequest request) {
        log.info("Inicializado o metodo de [createSingleSchedule] - Request: {}", request);

        var profissional = profissionalRepository.findById(request.getCpfProfissional())
                .orElseThrow(() -> new RuntimeException("Profissional not found with CPF: " + request.getCpfProfissional()));

        var barbearia = barbeariaRepository.findById(request.getCnpjBarbearia())
                .orElseThrow(() -> new RuntimeException("Barbearia not found with CNPJ: " + request.getCnpjBarbearia()));

        var servicosBarbearia = servicosBarbeariaRepository.findById(request.getServicosBarbeariaId())
                .orElseThrow(() -> new RuntimeException("Serviço de Barbearia not found with ID: " + request.getServicosBarbeariaId()));

        Agenda agenda = Agenda.builder()
                .profissional(profissional)
                .barbearia(barbearia)
                .servicosBarbearia(servicosBarbearia)
                .date(request.getDate())
                .time(request.getTime())
                .jornada(determineShift(request.getTime()))
                .statusAgenda(StatusAgenda.ABERTO)
                .build();

        agendaValidation.validateWithSpecificValidators(agenda, List.of(
                ProfessionalBarberShopLinkValidator.class,
                ActiveStatusValidator.class,
                AgendaScheduleConflictValidator.class
        ));

        agendaRepository.save(agenda);
        log.info("Finalizado o metodo de [createSingleSchedule] - Agenda Criada: {}", agenda);
        return AgendaMapper.INSTANCE.convertEntityToResponse(agenda);
    }

    private Agenda createAgendaEntity(AgendaRequestWithoutJornada request, LocalTime time, JornadaEnum jornada) {
        var profissional = profissionalRepository.findById(request.getCpfProfissional())
                .orElseThrow(() -> new RuntimeException("Profissional not found with CPF: " + request.getCpfProfissional()));

        var barbearia = barbeariaRepository.findById(request.getCnpjBarbearia())
                .orElseThrow(() -> new RuntimeException("Barbearia not found with CNPJ: " + request.getCnpjBarbearia()));

        var servicosBarbearia = servicosBarbeariaRepository.findById(request.getServicosBarbeariaId())
                .orElseThrow(() -> new RuntimeException("Serviço de Barbearia not found with ID: " + request.getServicosBarbeariaId()));

        return Agenda.builder()
                .profissional(profissional)
                .barbearia(barbearia)
                .servicosBarbearia(servicosBarbearia)
                .date(request.getDate())
                .time(time)
                .jornada(jornada)
                .statusAgenda(StatusAgenda.ABERTO)
                .build();
    }

    private JornadaEnum determineShift(LocalTime time) {
        if (time.isBefore(LocalTime.of(12, 0))) {
            return JornadaEnum.MANHA;
        } else if (time.isBefore(LocalTime.of(19, 0))) {
            return JornadaEnum.TARDE;
        } else {
            return JornadaEnum.NOITE;
        }
    }
}