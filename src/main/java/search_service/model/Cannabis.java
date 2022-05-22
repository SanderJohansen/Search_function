package search_service.model;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;



public class Cannabis {



    private int idCannabis;
    private String CannabisName;
    private int idEffect;
    private String DiseaseName;
    private int idSideEffect;
    private int idCategory;
    private double THCRatio;
    private double CBGRatio;
    private double CBDRatio;
    private String Description;


    // Default constructor

    public Cannabis(){

    }

    public Cannabis(String CannabisName, int idCannabis, int idEffect, String DiseaseName, int idSideEffect, int idCategory, double THCRatio, double CBGRatio, double CBDRatio, String Description ){
        this.CannabisName = CannabisName;
        this.idCannabis = idCannabis;
        this.idEffect = idEffect;
        this.DiseaseName = DiseaseName;
        this.idSideEffect = idSideEffect;
        this.idCategory = idCategory;
        this.THCRatio = THCRatio;
        this.CBGRatio = CBGRatio;
        this.CBDRatio = CBDRatio;
        this.Description = Description;


    }


    public int getIdCannabis() {
        return idCannabis;
    }

    public void setIdCannabis(int idCannabis) {
        this.idCannabis = idCannabis;
    }

    public String getCannabisName() {
        return CannabisName;
    }

    public void setCannabisName(String cannabisName) {
        CannabisName = cannabisName;
    }

    public int getIdEffect() {
        return idEffect;
    }

    public void setIdEffect(int idEffect) {
        this.idEffect = idEffect;
    }

    public String getDiseaseName() {
        return DiseaseName;
    }

    public void setDiseaseName(int idDisease) {
        this.DiseaseName = DiseaseName;
    }

    public int getIdSideEffect() {
        return idSideEffect;
    }

    public void setIdSideEffect(int idSideEffect) {
        this.idSideEffect = idSideEffect;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public double getTHCRatio() {
        return THCRatio;
    }

    public void setTHCRatio(double THCRatio) {
        this.THCRatio = THCRatio;
    }

    public double getCBGRatio() {
        return CBGRatio;
    }

    public void setCBGRatio(double CBGRatio) {
        this.CBGRatio = CBGRatio;
    }

    public double getCBDRatio() {
        return CBDRatio;
    }

    public void setCBDRatio(double CBDRatio) {
        this.CBDRatio = CBDRatio;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    //TODO Seems to be some problems here
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("idCannabis", idCannabis);
        json.put("CannabisName", CannabisName);
        json.put("DiseaseName", DiseaseName);
        json.put("idEffect", idEffect);
        json.put("idSideEffect", idSideEffect);
        json.put("idCategory", idCategory);
        json.put("THCRation", THCRatio);
        json.put("CBGRatio", CBGRatio);
        json.put("CBDRation", CBDRatio);
        json.put("Description", Description);

        return json; }

    public Cannabis fromJSONObject(JSONObject json) throws JSONException {
        Cannabis cannabis = new Cannabis();
        cannabis.CannabisName = json.getString("CannabisName");
        cannabis.idCannabis = json.getInt("idCannabis");
        cannabis.DiseaseName = json.getString("DiseaseName");
        cannabis.idEffect = json.getInt("idEffect");
        cannabis.idSideEffect = json.getInt("idSideEffect");
        cannabis.idCategory = json.getInt("idCategory");
        cannabis.THCRatio = json.getDouble("THCRatio");
        cannabis.CBGRatio = json.getDouble("CBGRatio");
        cannabis.CBDRatio = json.getDouble("CBDRatio");
        cannabis.Description = json.getString("Description");

        return cannabis;

    }



    //TODO fuck me man
    @Override
    public String toString(){
        return "idCannabis: " + this.idCannabis + "CannabisName " + this.CannabisName + "DiseaseName " + this.DiseaseName + "idEffect " + this.idEffect + "idSideEffect" + this.idSideEffect + "idCategory " + this.idCategory
                + "THCRatio " + this.THCRatio + "CBGRatio " + this.CBGRatio + "CBDRatio " + this.CBDRatio + "Description " + this.Description;
    }

}



