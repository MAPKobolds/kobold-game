package org.uniba.kobold.api.record;

import com.google.gson.Gson;
import org.uniba.kobold.rest.models.Record;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The type Record service.
 */
public class RecordService {
    private String basePath = "http://localhost:8000/records";
    private Client client = ClientBuilder.newClient();
    private Gson gson = new Gson();

    /**
     * Save game record int.
     *
     * @param playerName the player name
     * @param time       the time
     * @return the int
     */
    public int saveGameRecord(String playerName, Long time) {
        Record record = new Record(playerName, time, 0);
        WebTarget target = client.target(basePath);

        try {
            Invocation.Builder invocationBuilder = target
                    .request(MediaType.APPLICATION_JSON)
                    .header("Content-Type", "application/json");

            Entity<?> entity = Entity.entity(gson.toJson(record), MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(entity, Response.class);

            return response.getStatus();
        } catch (Exception e){
            throw new RuntimeException("Failed to make request to save record", e);
        }
    }

    /**
     * Gets best record.
     *
     * @return the best record
     */
    public List<Record> getBestRecord() {
        WebTarget target = client.target(basePath + "/best");
        String responseBody;

        try {
            Response resp = target.request(MediaType.APPLICATION_JSON).get();
            responseBody = resp.readEntity(String.class);

            return List.of(gson.fromJson(responseBody, Record[].class));
        }catch (Exception e){
            throw new RuntimeException("Failed to make request to " , e);
        }
    }

}
