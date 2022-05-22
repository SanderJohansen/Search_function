package search_service.dao;

import java.sql.SQLException;
import java.util.List;
import java.sql.SQLException;

import com.sun.xml.bind.v2.TODO;
import search_service.model.Cannabis;
import search_service.model.Response;

public interface ICannabisDao {

     List<Cannabis> findAll() throws DALException, SQLException;

     Cannabis findByID(int idCannabis) throws DALException, SQLException;

     Cannabis  findByName(String CannabisName) throws DALException, SQLException;

     List<Cannabis> findByDisease(String DiseaseName) throws DALException, SQLException;

     List<Cannabis> findByEffect(String idEffect) throws DALException, SQLException;

//EXCEPTiONS


    class DALException extends Exception {
        private Response response;

        public DALException(String msg, Response response) {
            super(msg);
            this.response = response;

        }

        public Response getResponse() {
            return response;
        }
    }
    class UnknownCannabisExpection extends DALException {

        public UnknownCannabisExpection(String CannabisName) {
            super("Unknown Cannabis '" + CannabisName + "'", new Response(404, true, "Unknown Cananbis '" + CannabisName + "'"));
        }
    }

    class UnknownCannabisIDExpection extends DALException {

        public UnknownCannabisIDExpection(int idCannabis) {
            super("Unknown CannabisID '" + idCannabis + "'", new Response(404, true, "Unknown id '" + idCannabis + "'"));
        }
    }

    class UnknownDiseaseExpection extends DALException {
        public UnknownDiseaseExpection(String DieaseName){
            super("Unknown Disease '" + DieaseName + "'", new Response(404, true, "Unknown Disease '" + DieaseName + "'"));

        }
    }

    class UnknownEffectExpection extends DALException {
        public UnknownEffectExpection(String EffectName){
            super("Unknown EffectName '" + EffectName + "'", new Response(404, true, "Unknown EffectName '" + EffectName + "'"));
        }
    }


//TODO: måske implementer excetion regler her





}
