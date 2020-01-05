package com.terahorse.fobit.listeners;

import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FobitAgendaEventListener extends DefaultAgendaEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(FobitAgendaEventListener.class);

    @Override
    public void afterMatchFired(AfterMatchFiredEvent event) {
        String ruleName = event.getMatch().getRule().getName();
        LOG.info("┃ ⬥ RHS fired: {} ", ruleName);
    }

    @Override
    public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
        String agendaName = event.getAgendaGroup().getName();
        LOG.info("┃ ⬒ AGENDA FINISHED: {}", agendaName);
    }

}
