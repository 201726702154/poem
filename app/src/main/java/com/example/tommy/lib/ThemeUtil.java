package com.example.tommy.lib;
        import android.app.Activity;
        import android.content.Intent;

        import com.example.tommy.R;
public class ThemeUtil {
    private static int theme = 0;
    private static final int DAY_THEME = 0;
    private static final int NIGHT_THEME = 1;
    public static void onActivityCreatedSetTheme(Activity activity) {
        switch (theme) {
            case DAY_THEME:
                activity.setTheme(R.style.AppThemeBase);
                break;
            case NIGHT_THEME:
                activity.setTheme(R.style.NightMode);
                break;
        }
    }
    public static void ChangeCurrentTheme(Activity activity) {
        switch (theme) {
            case DAY_THEME:
                theme = NIGHT_THEME;
                break;
            case NIGHT_THEME:
                theme = DAY_THEME;
                break;
        }
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
}