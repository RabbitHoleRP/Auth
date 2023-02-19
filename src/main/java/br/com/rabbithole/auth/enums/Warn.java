package br.com.rabbithole.auth.enums;

public enum Warn {
    INSERT_CACHE_ERROR("<red>[Permissions] Erro ao adicionar jogador ao Cache!"),
    REMOVE_CACHE_ERROR("<red>[Permissions] Erro ao remover jogogador do Cache!"),
    INSERT_DATABASE_ERROR("<red>[Permissions] Erro ao inserir jogador no Banco de Dados!"),
    UPDATE_DATABASE_ERROR("<red>[Permissions] Erro ao atualizar jogador no Banco de Dados!"),
    DELETE_DATABASE_ERROR("<red>[Permissions] Erro ao deletar jogador do Banco de Dados!");

    public final String message;

    Warn(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
