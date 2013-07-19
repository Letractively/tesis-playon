package tesis.playon.mobile.preferences;

import tesis.playon.mobile.Const;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHelper {

    private final SharedPreferences mPreferences;

    public PreferenceHelper(Context context) {
	mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void updateUsuario(String usuario, String password) {
	SharedPreferences.Editor editor = mPreferences.edit();
	editor.putString(Const.USUARIO, usuario);
	editor.putString(Const.PASSWORD, password);
	editor.commit();
    }

    public void updateNroUsuario(int nroUsuario) {
	SharedPreferences.Editor editor = mPreferences.edit();
	editor.putInt(Const.ID_USUARIO, nroUsuario);
	editor.commit();
    }

    public void updateIdPlaya(int idPlaya) {
	SharedPreferences.Editor editor = mPreferences.edit();
	editor.putInt(Const.ID_PLAYA, idPlaya);
	editor.commit();
    }

    public void updateNomPlaya(String nomPlaya) {
	SharedPreferences.Editor editor = mPreferences.edit();
	editor.putString(Const.NOMBRE_PLAYA, nomPlaya);
	editor.commit();
    }

    public void updateQuery(String query) {
	SharedPreferences.Editor editor = mPreferences.edit();
	editor.putString(Const.QUERY, query);
	editor.commit();
    }

    public void updateComentario(String comentario) {
	SharedPreferences.Editor editor = mPreferences.edit();
	editor.putString(Const.COMENTARIO, comentario);
	editor.commit();
    }

    public void updateLatLng(String lat, String lng) {
	SharedPreferences.Editor editor = mPreferences.edit();
	editor.putString(Const.LAT, lat);
	editor.putString(Const.LNG, lng);
	editor.commit();
    }

    public int getIdUsuario() {
	return mPreferences.getInt(Const.ID_USUARIO, 0);
    }

    public int getIdPlaya() {
	return mPreferences.getInt(Const.ID_PLAYA, 0);
    }

    public String getUsuario() {
	return mPreferences.getString(Const.USUARIO, null);
    }

    public String getPassword() {
	return mPreferences.getString(Const.PASSWORD, null);
    }

    public String getComentario() {
	return mPreferences.getString(Const.COMENTARIO, null);
    }

    public String getQuery() {
	return mPreferences.getString(Const.QUERY, null);
    }

    public String getLat() {
	return mPreferences.getString(Const.LAT, null);
    }

    public String getLng() {
	return mPreferences.getString(Const.LNG, null);
    }

    public String getNomPlaya() {
	return mPreferences.getString(Const.NOMBRE_PLAYA, null);
    }

}