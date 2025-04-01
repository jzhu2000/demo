import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FlowLogParser {
    private final TagLookup tagLookup;
    
    public FlowLogParser(){
        this.tagLookup = new TagLookup();
    }

    //parse log line by line
    public void parser(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {  //skip empty lines
                    String[] log = line.split("\\s+"); //whitespace
                    //do stats
                    countTag(log);
                }
            }
        } catch (IOException e) {
            System.out.println("Error on reading cvs file log: " + filePath);
            e.printStackTrace();
        }
        
        // print stats;
        for (Map.Entry<String, Integer> entry : Store.tag_count_map.entrySet()) {
            System.out.println("tag count: " + entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
        for (Map.Entry<PortProtocol, Integer> entry : Store.port_protocol_count_map.entrySet()) {
            System.out.println("protocol count: " + entry.getKey().getPort() +","+entry.getKey().getProtocol() + " : " + entry.getValue());
        }
    }
    
    public void countTag(String[] log) {
        int port = Integer.parseInt(log[6]);
        int protocol = Integer.parseInt(log[7]); 
    
        String tag = tagLookup.getTag(port, protocol);
        Store.tag_count_map.computeIfAbsent(tag, k -> 1);
        Store.tag_count_map.compute(tag, (k, v) -> v + 1);

        String proto = (Store.protocol_map.containsKey(protocol)) ? Store.protocol_map.get(protocol) :"UNKNOWN";
        Store.port_protocol_count_map.computeIfAbsent(new PortProtocol(port, proto +""), k -> 1);
        Store.port_protocol_count_map.compute(new PortProtocol(port, proto +""), (k, v) -> v + 1);
     }
}
