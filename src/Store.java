import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {
    
    static final Map<Integer, String> protocol_map = new HashMap<>();
    //put protocol code mapping here. here are the sample.
    static {
        protocol_map.put(0, "icmp");
        protocol_map.put(1, "ICMP");
        protocol_map.put(2, "IGMP");
        protocol_map.put(3, "GGP");
        protocol_map.put(6, "TCP");
        protocol_map.put(17, "UDP");
        protocol_map.put(22, "tcp");
        protocol_map.put(23, "tcp");
        protocol_map.put(25, "tcp");
        protocol_map.put(31, "udp");
        protocol_map.put(47, "GRE");
        protocol_map.put(50, "ESP");
        protocol_map.put(51, "AH");
        protocol_map.put(58, "ICMPv6");
        protocol_map.put(68, "udp");
        protocol_map.put(89, "OSPF");
        protocol_map.put(110, "EMAIL");
        protocol_map.put(132, "SCTP");
        protocol_map.put(143, "tcp");
        protocol_map.put(443, "tcp");
        protocol_map.put(993, "email");
        protocol_map.put(3389, "TCP");
    }

    //shared data, using thread safe data structure
    static final Map<String, Integer> tag_count_map = new ConcurrentHashMap<>();
    static final Map<PortProtocol, Integer> port_protocol_count_map = new ConcurrentHashMap<>();
}
