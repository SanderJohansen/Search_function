package search_service.logic;

import org.json.JSONException;
import search_service.dao.ICannabisDao;
import search_service.model.Response;
import search_service.model.Cannabis;

import java.sql.SQLException;

public interface ISearchLogic {


    public Response getCannabis(String CannabisName) throws ICannabisDao.DALException, SQLException, JSONException;

    public Response findAll() throws ICannabisDao.DALException, SQLException, JSONException;

    public Response findByID(int idCannabis) throws ICannabisDao.DALException, SQLException, JSONException;

    public Response  findByName(String CannabisName) throws ICannabisDao.DALException, SQLException, JSONException;

    public Response findByDisease(String idDisease ) throws ICannabisDao.DALException, SQLException, JSONException;

    public Response findByEffect(String idEffect) throws ICannabisDao.DALException, SQLException, JSONException;
}
