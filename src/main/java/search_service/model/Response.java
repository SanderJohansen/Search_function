package search_service.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Response {
    public int status = 0;
    public boolean error = false;
    public String message = "";
    public JSONObject result = new JSONObject();


    public Response(int status, boolean error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public Response() {

    }

    public static Response fromJSON(JSONObject json) throws JSONException {
        Response response = new Response();
        response.status = json.getInt("status");
        response.message = json.getString("message");
        response.error = json.getBoolean("error");
        response.result = json.getJSONObject("result");
        return response;
    }

    public static Response fromJSONString(String jsonString) throws JSONException {
        return fromJSON(new JSONObject(jsonString));
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("error", error);
        json.put("message", message);
        json.put("result", result);
        return json;
    }

    public String toJSONString() throws JSONException { return toJSON().toString(); }




    //Create the (json) body for an error response.
    public static void setError(int status, String errorMessage) {
        Response response = new Response();
        response.error = true;
        response.status = status;
        response.message = errorMessage;
    }

    //Create the (json) body for a success response (with a result json)
    public static Response setResult(int status, String message, JSONObject result) {
        Response response = new Response();
        response.message = message;
        response.status = status;
        if (result != null) {
            response.result = result;
        }
        return response;
    }

    public static Response setResult(int status, String message) {
        Response response = new Response();
        response.message = message;
        response.status = status;
        return response;
    }
}
