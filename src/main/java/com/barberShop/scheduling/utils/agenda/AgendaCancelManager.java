package com.barberShop.scheduling.utils.agenda;

import com.barberShop.scheduling.domain.Agenda;
import com.barberShop.scheduling.enums.CancelType;

public interface AgendaCancelManager {
    void cancelAgendas(String identifier, CancelType type);
    void cancelAgenda(Agenda agenda);
}