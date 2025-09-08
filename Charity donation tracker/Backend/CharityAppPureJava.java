import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;

public class CharityAppPureJava {

    // Hardcoded users
    static List<Map<String,String>> users = Arrays.asList(
        Map.of("username","donor1","password","donor123","role","donor"),
        Map.of("username","ngo1","password","ngo123","role","ngo"),
        Map.of("username","admin1","password","admin123","role","admin")
    );

    // Single NGO with causes
    static List<Map<String,Object>> causes = new ArrayList<>(Arrays.asList(
        new HashMap<>(Map.of("title","Books for Schools","goal",5000,"collected",2000,"active",true)),
        new HashMap<>(Map.of("title","Clean Water","goal",10000,"collected",3000,"active",true))
    ));

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(9090), 0);
        System.out.println("Server running at http://localhost:9090");

        // ===== LOGIN API =====
        server.createContext("/api/login", (HttpExchange exchange) -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.sendResponseHeaders(405, -1); // Method not allowed
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "utf-8"));
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) body.append(line);
            String request = body.toString();

            String username = request.split("\"username\":\"")[1].split("\"")[0];
            String password = request.split("\"password\":\"")[1].split("\"")[0];
            String role = request.split("\"role\":\"")[1].split("\"")[0];

            boolean found = users.stream().anyMatch(u -> u.get("username").equals(username)
                    && u.get("password").equals(password)
                    && u.get("role").equals(role));

            String response = found ? "{\"status\":\"success\",\"role\":\""+role+"\"}" : "{\"status\":\"fail\"}";

            // Add CORS headers
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            exchange.sendResponseHeaders(200,response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        // ===== GET CAUSES API =====
        server.createContext("/api/causes", (HttpExchange exchange) -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }

            StringBuilder json = new StringBuilder("[");
            for (int i=0;i<causes.size();i++){
                Map<String,Object> c = causes.get(i);
                json.append("{")
                        .append("\"title\":\"").append(c.get("title")).append("\",")
                        .append("\"goal\":").append(c.get("goal")).append(",")
                        .append("\"collected\":").append(c.get("collected")).append(",")
                        .append("\"active\":").append(c.get("active"))
                        .append("}");
                if(i<causes.size()-1) json.append(",");
            }
            json.append("]");

            String response = json.toString();
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200,response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        // ===== DONATE API =====
        server.createContext("/api/donate", (HttpExchange exchange) -> {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.sendResponseHeaders(405, -1);
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), "utf-8"));
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) body.append(line);
            String request = body.toString();

            int index = Integer.parseInt(request.split("\"causeIndex\":")[1].split(",")[0].trim());
            int amount = Integer.parseInt(request.split("\"amount\":")[1].split("}")[0].trim());

            String resp = "Failed";
            if(index>=0 && index<causes.size() && amount>0){
                int collected = (int) causes.get(index).get("collected");
                causes.get(index).put("collected", collected+amount);
                resp = "Donation successful!";
            }

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Content-Type", "text/plain");
            exchange.sendResponseHeaders(200, resp.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(resp.getBytes());
            os.close();     
        });

        server.setExecutor(null);
        server.start();
    }
}
