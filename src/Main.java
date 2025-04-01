public class Main {
    private static final String PORT_PROTOCOL_TAG_FILE = "port_protocol_tag.txt";
    private static final String FLOW_LOG_FILE = "flow_log_records.txt";
    
    public static void main(String[] args)  {
        System.out.println("buidling port protocol tag mapping table");
        TagLookup tagLookup = new TagLookup();
        tagLookup.buildLookup(PORT_PROTOCOL_TAG_FILE);
        
        System.out.println("parsing flow log record ...");
        FlowLogParser flowLogParser  = new FlowLogParser();
        flowLogParser.parser(FLOW_LOG_FILE);
    }

}
