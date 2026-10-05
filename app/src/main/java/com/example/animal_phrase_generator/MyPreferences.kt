import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class MyPreferences(context: Context) {

    private val sp: SharedPreferences =
        context.getSharedPreferences("NAME", Context.MODE_PRIVATE)

    fun setString(key: String, str: String) {
        sp.edit { putString(key, str) }
    }

    fun getString(key: String): String {
        return sp.getString(key, "") ?: ""
    }
}
