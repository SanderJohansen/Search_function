package search_service.logic;

import org.json.JSONException;
import search_service.model.Response;
import search_service.model.Cannabis;
import search_service.dao.ICannabisDao;
import search_service.dao.CannabisDAO;
import java.sql.SQLException;

public class SearchLogic implements ISearchLogic {

    //TODO get responses here like

    ICannabisDao dao = new CannabisDAO();

    @Override
    //Er allerede lavet igennem findByName
    public Response getCannabis(String CannabisName) throws ICannabisDao.DALException, SQLException, JSONException {
        //make a call to the DAO, to gte the users from the database
        Cannabis responseCannabis = dao.findByName(CannabisName);
        //make a call to the (static) Response class, to create a response.
        return Response.setResult(200,"Cannabis gotten", responseCannabis.toJSONObject());

    }

    @Override
    public Response findAll() throws ICannabisDao.DALException, SQLException, JSONException {
        //make a call to the DAO, to gte the users from the database
        Cannabis responseCannabis = (Cannabis) dao.findAll();
        //make a call to the (static) Response class, to create a response.
        return Response.setResult(200,"Cannabis gotten", responseCannabis.toJSONObject());
    }

    @Override
    public Response findByID(int idCannabis) throws ICannabisDao.DALException, SQLException, JSONException {
        //make a call to the DAO, to gte the users from the database
        Cannabis responseCannabis = dao.findByID(idCannabis);
        //make a call to the (static) Response class, to create a response.
        //TODO there might be a problem here since it has been taken form "fidn by name"
        return Response.setResult(200,"Cannabis gotten", responseCannabis.toJSONObject());
    }

    public Response findByName (String CannabisName) throws ICannabisDao.DALException, SQLException, JSONException {
        //make a call to the DAO, to gte the users from the database
        Cannabis responseCannabis = dao.findByName(CannabisName);
        //make a call to the (static) Response class, to create a response.
        return Response.setResult(200,"Cannabis gotten", responseCannabis.toJSONObject());
    }

    @Override
    public Response findByDisease(String DiseaseName) throws ICannabisDao.DALException, SQLException, JSONException {
        //make a call to the DAO, to gte the users from the database
        Cannabis responseCannabis = (Cannabis) dao.findByDisease(DiseaseName);
        //make a call to the (static) Response class, to create a response.
        return Response.setResult(200,"Cannabis gotten", responseCannabis.toJSONObject());
    }

    @Override
    //works weirdly
    public Response findByEffect(String idEffect) throws ICannabisDao.DALException, SQLException, JSONException {
        //make a call to the DAO, to gte the users from the database
        Cannabis responseCannabis = (Cannabis) dao.findByDisease(idEffect);
        //make a call to the (static) Response class, to create a response.
        return Response.setResult(200,"Cannabis gotten", responseCannabis.toJSONObject());
    }
}
