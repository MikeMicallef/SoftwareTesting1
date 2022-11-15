package API;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiManager {

    API.objects.api api;
    String apiUrl = "https://api.marketalertum.com/Alert";
//post Alert
    public int postAlert(String jsonBody) throws Exception {
        if (api != null) {
            if (api.getApi() == API.objects.api.AVAILABLE) {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(jsonBody)).build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.statusCode();
            } else{ return -1;
            }
        } return -1;
    }


    //Removing Alerts
    public int DeleteAlert() throws Exception {
        if (api != null) {
            if (api.getApi() == API.objects.api.AVAILABLE) { //Sending a delete request
                HttpRequest SendDeleteRequest = HttpRequest.newBuilder().uri(URI.create("https://api.marketalertum.com/Alert?userId=548ac34f-7c6d-491f-99ec-5387bf312e84")).header("Content-Type", "application/json").DELETE().build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> httpResponse = client.send(SendDeleteRequest, HttpResponse.BodyHandlers.ofString());
                return httpResponse.statusCode();
            }
        }else return -1;
        return -1;
    }

    public void setapistatus(API.objects.api api){
        this.api = api;
    }

}
