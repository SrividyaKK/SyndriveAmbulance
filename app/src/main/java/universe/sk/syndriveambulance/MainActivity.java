package universe.sk.syndriveambulance;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Before setContentView
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                                .setDefaultFontPath("fonts/Arkhip_font.ttf")
                                                .setFontAttrId(R.attr.fontPath)
                                                .build());
        setContentView(R.layout.activity_main);

        setupUIViews();

    } // end of onCreate

    private void setupUIViews() {

    }

} // end of MainActivity
