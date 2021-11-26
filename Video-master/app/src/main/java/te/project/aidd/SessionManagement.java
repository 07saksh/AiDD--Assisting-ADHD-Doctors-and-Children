package te.project.aidd;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SessionManagement extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME="session";
    String SESSION_KEY="session_user";
    String SESSION_KEY1="key_name";
    String SESSION_TABLEID="tableid";

    int id,tableID;

    public SessionManagement(Context context){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

    }

    public void saveSession(User user){
        //save session of user whenever user is logged in
        id=user.getId();
        tableID=user.getTableId();
        String naaam=user.getName();
        editor.putInt(SESSION_KEY,id).commit();
        editor.putInt(SESSION_TABLEID,tableID).commit();
        editor.putString(SESSION_KEY1,naaam).commit();
    }

    public int getSession(){
        //return user id whose session is saved
        return sharedPreferences.getInt(SESSION_KEY,-1);
    }
    public String getnaaam(){
        //return name of the user
        return sharedPreferences.getString(SESSION_KEY1,null);
    }
    public int getTableID(){
        return sharedPreferences.getInt(SESSION_TABLEID,-1);
    }

    public void removeSession(){
        editor.putInt(SESSION_KEY,-1).commit();
        editor.putString(SESSION_KEY1,null).commit();
    }
}
