package com.coreservlets.events6;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.*;

/** Gives a simple event handling example. This last example
 *  (of six) uses the main Activity for the event handler.
 *  However, instead of setting the listener programmatically,
 *  we set it in the layout file (res/layouts/main.xml). This
 *  means that you do not need to implement a Listener interface,
 *  but it also disguises from the Java programmer what method
 *  acts as event handler for the GUI control(s).
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class Events6Example extends Activity {
    // The Android coding style conventions say that non-public instance variables should start with "m".
    // "m" for "member" (as in "data member" or "member variable").
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
        // No need to look up the button or assign an event handler
    }
    
    /** Sets the color of the color region. */
    
    private void setRegionColor(int color) {
        mColorRegion.setBackgroundColor(color);
    }

    /** Calls to the same class to set the color of View at the bottom. */
    
    public void randomizeColor(View clickedButton) {
        Random generator = new Random();
        int index = generator.nextInt(mColorChoices.length);
        setRegionColor(mColorChoices[index]);
    }
}