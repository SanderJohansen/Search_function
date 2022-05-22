package search_service.Controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import search_service.dao.ICannabisDao;
import search_service.logic.ISearchLogic;
import search_service.logic.SearchLogic;
import search_service.model.Cannabis;
import search_service.model.Response;

import search_service.rabbitMQ.RabbitLog;
import search_service.rabbitMQ.ConfigureRabbitMQ;


import javax.xml.bind.SchemaOutputResolver;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;


@RestController
@RequestMapping("/search-service")
public class SearchServiceRestController {

    ISearchLogic searchLogic = new SearchLogic();
    Response response = new Response();
    private final RabbitTemplate rabbitTemplate;
    private RabbitLog rabbitLog;


    public SearchServiceRestController(RabbitTemplate rabbitTemplate) {this.rabbitTemplate = rabbitTemplate; }

    //not GetMapping?
    @GetMapping("/find-Cannabis/")
    public ResponseEntity<String> findByName(String jsonString) throws ICannabisDao.DALException, SQLException, JSONException {
        //TODO need some rabbitMQ stuff here
        rabbitLog = new RabbitLog("get-Cannabis","GET", LocalDate.now().toString(), LocalTime.now().toString(), jsonString);
        //Parse jsonObject
        JSONObject jsonObject = new JSONObject(jsonString);
        int idCannabis = jsonObject.getInt("idCannabis");
        String cannabisname = jsonObject.getString("CannabisName");

        String DiseaseName = jsonObject.getString("Diseasename");
        int idEffect = jsonObject.getInt("idEffect");
        int idSideEffect = jsonObject.getInt("idSideEffect");
        int idCategory = jsonObject.getInt("idCategory");
        double THCRatio = jsonObject.getDouble("THCRatio");
        double CBGRatio = jsonObject.getDouble("CBGRatio");
        double CBDRatio = jsonObject.getDouble("CBDRatio");
        String Description = jsonObject.getString("Description");

        response = searchLogic.findByName(cannabisname);
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
        //compelte Rabbitlog (using helper function)
        rabbitLog.setOutput(response.toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(false);

        sendRabbitLog();

        return responseEntity;

    }

    @GetMapping("/find-disease/{diseaseName}")
    public ResponseEntity<String> findByDisease (String jsonString) throws ICannabisDao.DALException, SQLException, JSONException {

        rabbitLog = new RabbitLog("get-Disease", "GET", LocalDate.now().toString(), LocalTime.now().toString(), jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);

        int idCannabis = jsonObject.getInt("idCannabis");
        String diseaseName = jsonObject.getString("CannabisName");

        String DiseaseName = jsonObject.getString("Diseasename");
        int idEffect = jsonObject.getInt("idEffect");
        int idSideEffect = jsonObject.getInt("idSideEffect");
        int idCategory = jsonObject.getInt("idCategory");
        double THCRatio = jsonObject.getDouble("THCRatio");
        double CBGRatio = jsonObject.getDouble("CBGRatio");
        double CBDRatio = jsonObject.getDouble("CBDRatio");
        String Description = jsonObject.getString("Description");

        response = searchLogic.findByDisease(diseaseName);
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(response.toJSONString());
        //complete Rabbitlog (using helper function
        rabbitLog.setOutput(response.toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(false);

        sendRabbitLog();

        return responseEntity;
    }



    @ExceptionHandler(ICannabisDao.DALException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDALException(ICannabisDao.DALException dalException) throws JSONException {
        rabbitLog.setOutput(dalException.getResponse().toJSONString());
        rabbitLog.setOutputTime(LocalTime.now().toString());
        rabbitLog.setError(true);
        sendRabbitLog();
        return ResponseEntity.status(HttpStatus.OK).body(dalException.getResponse().toJSONString());
            }


    public void sendRabbitLog(){
        try {
            rabbitTemplate.convertAndSend(ConfigureRabbitMQ.EXCHANGE_NAME,
                    "logger.message", rabbitLog.toJson().toString());
        } catch (Exception e){
            System.out.println("Failed to send RabbitMQ activity-log properly.");
        }
    }




}
