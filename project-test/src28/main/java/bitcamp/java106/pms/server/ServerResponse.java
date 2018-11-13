package bitcamp.java106.pms.server;

import java.io.PrintWriter;

public class ServerResponse {
    protected PrintWriter out;
    
    public ServerResponse(PrintWriter out) {
        this.out = out;
    }
    
    public PrintWriter getWriter() {
        return this.out;
    }
}
