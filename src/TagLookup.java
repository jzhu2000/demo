import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TagLookup {
    private final Map<PortProtocol, String> portProtocolLookup = new HashMap<>();

    public void buildLookup(String filePath) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
                String[] parts = line.split(","); //split by ','
                if (parts.length == 3) {
                    int port = Integer.parseInt(parts[0].trim());
                    String protocol = parts[1].trim().toLowerCase();
                    String tag = parts[2].trim(); //case sensitive
                    portProtocolLookup.put(new PortProtocol(port, protocol), tag);
                }
            }
        } catch (IOException e) {
            System.out.println(" Error on parsing Port Protocol Tag file: " + filePath );
            e.printStackTrace();
        }

        System.out.println(count + " lines of Port Protocol Tag lookup parsed" );
    }

    public void printMap() {
        for (Map.Entry<PortProtocol, String> entry : portProtocolLookup.entrySet()) {
                System.out.println("Look up " + entry.getKey() + " : " + entry.getValue());
        }
    }

    public String getTag(int port, String protocol) {
        //System.out.println("Look up " + port + " : " + protocol);
        return portProtocolLookup.getOrDefault(new PortProtocol(port, protocol.toLowerCase()), "UNKNOWN");
    }

    public String getTag(int port, int protocol) {
        //System.out.println("Look up " + port + " : " + protocol);
        if (!Store.protocol_map.containsKey(protocol)) return "UNKNOWN";
        return portProtocolLookup.getOrDefault(new PortProtocol(port, Store.protocol_map.get(protocol).toLowerCase()), "UNKNOWN");
    }
}
