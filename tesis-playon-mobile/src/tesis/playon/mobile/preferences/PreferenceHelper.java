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

    public int getIdUsuario() {
	return mPreferences.getInt(Const.ID_USUARIO, 0);
    }

    public String getUsuario() {
	return mPreferences.getString(Const.USUARIO, null);
    }

    public String getPassword() {
	return mPreferences.getString(Const.PASSWORD, null);
    }

}