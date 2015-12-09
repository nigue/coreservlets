package com.coreservlets.events5;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.*;

/** Gives a simple event handling example. This fifth example
 *  (of six) uses the main Activity for the event handler.
 *  To satisfy Java's strict type checking, the Activity
 *  implements the appropriate Listener interface.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class Events5Example extends Activity
                            implements OnClickListener {
    private View mColorRegion;
    private int[] mColorChoices =
            { Color.BLACK, Color.BLUE, Color.CYAN,
              Color.DKGRAY, Color.GRAY, Color.GREEN,
              Color.LTGRAY, Color.MAGENTA, Color.RED,
              Color.WHITE, Color.YELLOW };
    
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mColorRegion = findViewById(R.id.color_region);
        Button colorButton = 
                (Button)findViewById(R.id.color_button);
        colorButton.setOnClickListener(this);
    }
    
    /** Sets the color of the color region. */
    
    private void setRegionColor(int color) {
        mColorRegion.setBackgroundColor(color);
    }

    /** Calls to the same class to set the color of View at the bottom. */
    
    @Override
    public void onClick(View clickedButton) {
        Random generator = new Random();
        int index = generator.nextInt(mColorChoices.length);
        setRegionColor(mColorChoices[index]);
    }
}