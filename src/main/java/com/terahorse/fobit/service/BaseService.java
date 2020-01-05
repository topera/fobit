package com.terahorse.fobit.service;

import com.terahorse.fobit.Constants;
import com.terahorse.fobit.listeners.FobitAgendaEventListener;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Agenda;

class BaseService {

    // drools agendas
    private static final String AGENDA_SETUP = "SETUP";
    private static final String AGENDA_CARDS = "CARDS";
    private static final String AGENDA_BATTLE = "BATTLE";

    private static KieContainer kieContainer;

    BaseService(){
    }

    KieSession createKieSession() {
        createKieContainerIfNecessary();
        KieSession kieSession = kieContainer.newKieSession();
        configAgendas(kieSession);
        addListeners(kieSession);
        return kieSession;
    }

    private void addListeners(KieSession kieSession) {
        if (Constants.DEBUG_RULES) {
            kieSession.addEventListener(new FobitAgendaEventListener());
        }
    }

    private void configAgendas(KieSession kieSession) {
        Agenda agenda = kieSession.getAgenda();
        // they are in reverse order because this is a stack
        agenda.getAgendaGroup(AGENDA_BATTLE).setFocus();
        agenda.getAgendaGroup(AGENDA_CARDS).setFocus();
        agenda.getAgendaGroup(AGENDA_SETUP).setFocus();
    }

    private void createKieContainerIfNecessary() {
        if (kieContainer == null) {
            kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        }
    }

}
