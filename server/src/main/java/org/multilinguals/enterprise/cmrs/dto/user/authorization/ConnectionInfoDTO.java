package org.multilinguals.enterprise.cmrs.dto.user.authorization;

public class ConnectionInfoDTO {
    private String connSessionId;

    public ConnectionInfoDTO(String connSessionId) {
        this.connSessionId = connSessionId;
    }

    public String getConnSessionId() {
        return connSessionId;
    }
}