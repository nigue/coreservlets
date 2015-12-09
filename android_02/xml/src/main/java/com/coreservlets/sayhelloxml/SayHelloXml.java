package com.coreservlets.sayhelloxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Gives a simple example of an Android app where the layout and strings are
 * defined in XML files. An earlier example does a similar task with layout and
 * strings defined in the Java code.
 * <p>
 * From <a href="http://www.coreservlets.com/android-tutorial/">
 * the coreservlets.com Android programming tutorial series</a>.
 */
public class SayHelloXml extends Activity {

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Creates a Toast (temporary message) when button is clicked. Note that
     * there is a version of Toast.makeText that takes an id (R.string.blah)
     * instead of a String. However, it is important to know how to turn an ID
     * from res/values/strings into a String, for use in Java code.
     *
     * @param clickedButton
     */
    public void showToast(View clickedButton) {
        String greetingText = getString(R.string.greeting_text);
        Toast tempMessage
                = Toast.makeText(this, greetingText,
                        Toast.LENGTH_SHORT);
        tempMessage.show();
    }
}
