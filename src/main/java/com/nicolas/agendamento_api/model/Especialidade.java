package com.nicolas.agendamento_api.model;

public enum Especialidade {
    CLINICA_GERAL("Clínica Geral"),
    PEDIATRIA("Pediatria"),
    GINECOLOGIA("Ginecologia"),
    CARDIOLOGIA("Cardiologia"),
    ORTOPEDIA("Ortopedia"),
    PSIQUIATRIA("Psiquiatria"),
    DERMATOLOGIA("Dermatologia");

    private String nomeDisplay;

    Especialidade(String nomeDisplay) {
        this.nomeDisplay = nomeDisplay;
    }

    public String getNomeDisplay() {
        return nomeDisplay;
    }
}