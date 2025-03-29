package com.barberShop.scheduling.repository.agendamento;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.domain.Agendamento;
import com.barberShop.scheduling.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
    Optional<Agendamento> findByAgenda(Agenda agenda);
    List<Agendamento> findByCliente(Cliente cliente);
    List<Agendamento> findByAgenda_Profissional_Cpf(String cpfProfissional);
    List<Agendamento> findByAgenda_Barbearia_cnpj(String cnpjBarbearia);

}
