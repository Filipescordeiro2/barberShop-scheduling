package com.barberShop.scheduling.utils.agendamento;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.domain.Cliente;

public interface AgendamentoCancelador {
    void cancelarAgendamentosCliente(Cliente cliente);
    void cancelarAgendamentosProfissional(String cpfProfissional);
    void cancelarAgendamentosBarbearia(String cnpjBarbearia);
    void cancelarAgendamentoForAgenda(Agenda agenda);
}
