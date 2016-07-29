package by.serzh.beatsub.api.domain;

import com.querydsl.core.annotations.QueryEntity;

import java.util.Objects;

@QueryEntity
public class Server extends AbstractEntity {

    private String host;
    private int port;
    private String username;
    private String password;
    private License license = new License();

    public Server() {
    }

    public Server(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public Server(String host, String username, String password) {
        this(host, 4040, username, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return port == server.port &&
                Objects.equals(host, server.host) &&
                Objects.equals(username, server.username) &&
                Objects.equals(password, server.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, username, password);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = Objects.requireNonNull(license);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Server{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", license=").append(license);
        sb.append('}');
        return sb.toString();
    }
}
