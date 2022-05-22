package search_service.dao;

import search_service.model.Cannabis;
import user_service.SQLDatabase.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CannabisDAO implements ICannabisDao {

    DBConnection connector = new DBConnection();

    //SQL commands for below
    private static final String FIND_ALL = "SELECT * from GreenCare.Cannabis ORDER by idCannabis";
    private static final String FIND_BY_ID = "SELECT * FROM GreenCare.Cannabis WHERE idCannabis=?";
    private static final String FIND_BY_NAME = "SELECT * FROM GreenCare.Cannabis WHERE CannabisName=?";
    private static final String FIND_BY_DISEASE = "SELECT * FROM GreenCare.Cannabis WHERE DiseaseName=?";
    //I dont work
    private static final String FIND_BY_EFFECT = "Select CannabisName, Effect, Disease From GreenCare.Cannabis INNER JOIN GreenCare.Effects ON EFFECTS.idEffect = Cannabis.idEffect Where EffectName =?";


    @Override
    public List<Cannabis> findAll() throws DALException, SQLException {
        Cannabis cannabis = new Cannabis();
        List<Cannabis> List = new ArrayList<Cannabis>();

        try {
            Connection connection = connector.connectToLocalDB();
            PreparedStatement findAll = connection.prepareStatement(FIND_ALL);
            ResultSet rs = findAll.executeQuery();

            while (rs.next()) {
                cannabis.setIdCannabis(rs.getInt("idCannabis"));
                cannabis.setCannabisName(rs.getString("CannabisName"));
                cannabis.setIdEffect(rs.getInt("idEffect"));
                cannabis.setIdSideEffect(rs.getInt("idSideEffect"));
                cannabis.setIdCategory(rs.getInt("idCategory"));
                cannabis.setTHCRatio(rs.getFloat("THCRatio"));
                cannabis.setCBGRatio(rs.getFloat("CBGRatio"));
                cannabis.setCBDRatio(rs.getFloat("CBDRatio"));
                cannabis.setDescription(rs.getString("Description"));
                cannabis.setDiseaseName(rs.getInt("DiseaseName"));

                List.add(cannabis);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List;
    }

    @Override
    public Cannabis findByID(int idCannabis) throws DALException, SQLException {
        Cannabis cannabis = new Cannabis();
        Connection connection = connector.connectToLocalDB();
        PreparedStatement findByID = connection.prepareStatement(FIND_BY_ID);
        ResultSet rs = findByID.executeQuery();
        findByID.setInt(1, idCannabis);

        if (rs.next()) {
            cannabis.setIdCannabis(rs.getInt("idCannabis"));
            cannabis.setCannabisName(rs.getString("CannabisName"));
            cannabis.setIdEffect(rs.getInt("idEffect"));
            cannabis.setIdSideEffect(rs.getInt("idSideEffect"));
            cannabis.setIdCategory(rs.getInt("idCategory"));
            cannabis.setTHCRatio(rs.getFloat("THCRatio"));
            cannabis.setCBGRatio(rs.getFloat("CBGRatio"));
            cannabis.setCBDRatio(rs.getFloat("CBDRatio"));
            cannabis.setDescription(rs.getString("Description"));
            cannabis.setDiseaseName(rs.getInt("DiseaseName"));


        } else {
            throw new UnknownCannabisIDExpection(idCannabis);
        }
        return cannabis;
    }


    @Override
    public Cannabis findByName(String CannabisName) throws DALException, SQLException {

        Cannabis cannabis = new Cannabis();
        Connection connection = connector.connectToLocalDB();
        PreparedStatement findByName = connection.prepareStatement(FIND_BY_NAME);
        findByName.setString(1, CannabisName);
        ResultSet rs = findByName.executeQuery();


        if (rs.next()) {
            cannabis.setIdCannabis(rs.getInt("idCannabis"));
            cannabis.setCannabisName(rs.getString("CannabisName"));
            cannabis.setIdEffect(rs.getInt("idEffect"));
            cannabis.setIdSideEffect(rs.getInt("idSideEffect"));
            cannabis.setIdCategory(rs.getInt("idCategory"));
            cannabis.setTHCRatio(rs.getFloat("THCRatio"));
            cannabis.setCBGRatio(rs.getFloat("CBGRatio"));
            cannabis.setCBDRatio(rs.getFloat("CBDRatio"));
            cannabis.setDescription(rs.getString("Description"));
            cannabis.setDiseaseName(rs.getInt("DiseaseName"));
        } else {
            throw new UnknownCannabisExpection(CannabisName);

        }
        return cannabis;
    }

    @Override
    public List<Cannabis> findByDisease(String DiseaseName) throws DALException, SQLException {

        Cannabis cannabis = new Cannabis();
        List<Cannabis> List = new ArrayList<Cannabis>();
        Connection connection = connector.connectToLocalDB();
        PreparedStatement findByDisease = connection.prepareStatement(FIND_BY_DISEASE);
        ResultSet rs = findByDisease.executeQuery();

        if (rs.next()) {
            while (rs.next()) {
                cannabis.setIdCannabis(rs.getInt("idCananbis"));
                cannabis.setCannabisName(rs.getString("CannabisName"));
                cannabis.setIdEffect(rs.getInt("idEffect"));
                cannabis.setIdSideEffect(rs.getInt("idSideEffect"));
                cannabis.setIdCategory(rs.getInt("idCategory"));
                cannabis.setTHCRatio(rs.getFloat("THCRatio"));
                cannabis.setCBGRatio(rs.getFloat("CBGRatio"));
                cannabis.setCBDRatio(rs.getFloat("CBDRatio"));
                cannabis.setDescription(rs.getString("Description"));
                cannabis.setDiseaseName(rs.getInt("idDisease"));

                List.add(cannabis);
            }
        } else
            throw new UnknownDiseaseExpection(DiseaseName);

        return List;
    }

//TODO this does not work, joins in sql are weird AF.
    @Override
    public List<Cannabis> findByEffect(String EffectName) throws DALException, SQLException {
        Cannabis cannabis = new Cannabis();
        List<Cannabis> List = new ArrayList<Cannabis>();
        Connection connection = connector.connectToRemoteDB();
        PreparedStatement findByEffect = connection.prepareStatement(FIND_BY_EFFECT);
        ResultSet rs = findByEffect.executeQuery();

        if (rs.next()) {
            while (rs.next()) {
                cannabis.setIdCannabis(rs.getInt("id"));
                cannabis.setCannabisName(rs.getString("CannabisName"));
                cannabis.setIdEffect(rs.getInt("idEffect"));
                cannabis.setIdSideEffect(rs.getInt("idSideEffect"));
                cannabis.setIdCategory(rs.getInt("idCategory"));
                cannabis.setTHCRatio(rs.getFloat("THCRatio"));
                cannabis.setCBGRatio(rs.getFloat("CBGRatio"));
                cannabis.setCBDRatio(rs.getFloat("CBDRatio"));
                cannabis.setDescription(rs.getString("Description"));
                cannabis.setDiseaseName(rs.getInt("idDisease"));

                List.add(cannabis);
            }
        } else
            throw new UnknownEffectExpection(EffectName);

        return List;
    }

        }


