import java.util.Objects;

public class PortProtocol {
    private final int port;
    private final String protocol;

    public PortProtocol(int port, String protocol) {
        this.port = port;
        this.protocol = protocol.toLowerCase();  // protocol should not be case senstive
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PortProtocol that = (PortProtocol) obj;
        return port == that.port && protocol.equals(that.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, protocol);
    }

    @Override
    public String toString() {
        return port + "/" + protocol;
    }

    public int getPort(){
        return port;
    }

    public String getProtocol(){
        return protocol;
    }
}
