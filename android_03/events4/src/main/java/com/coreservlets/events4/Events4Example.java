package com.coreservlets.events4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.*;

/** Gives a simple event handling example. This fourth example
 *  (of six) uses an anonymous inner class for the event handler.
 *  <p>
 *  From <a href="http://www.coreservlets.com/android-tutorial/">
 *  the coreservlets.com Android programming tutorial series</a>.
 */

public class Events4Example extends Activity {
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
        colorButton.setOnClickListener(new OnClickListener() {
            /** Calls back to the outer class to set the color of View at the bottom. */
            @Override
            public void onClick(View clickedButton) {
                Random generator = new Random();
                int index = generator.nextInt(mColorChoices.length);
                setRegionColor(mColorChoices[index]);
            }
        });
    }
    
    /** Sets the color of the color region. */
    
    private void setRegionColor(int color) {
        mColorRegion.setBackgroundColor(color);
    }
}