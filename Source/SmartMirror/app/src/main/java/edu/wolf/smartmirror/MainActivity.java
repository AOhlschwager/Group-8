package edu.wolf.smartmirror;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewSwitcher switcher;

    EditText ip, port;
    String ipString, portString;

    String a1String = "";
    String a2String = "";
    String a3String = "";
    String b1String = "";
    String b2String = "";
    String b3String = "";
    String c1String = "";
    String c2String = "";
    String c3String = "";

    String a1Current = "";
    String a2Current = "";
    String a3Current = "";
    String b1Current = "";
    String b2Current = "";
    String b3Current = "";
    String c1Current = "";
    String c2Current = "";
    String c3Current = "";

    String stateSelected, citySelected;

    Button connect, sleepCancel, sleepOk, powerCancel, powerOk, clockCancel, clockOk,
            gesturesCancel, gesturesOk, vcCancel, vcOk, colorCancel, colorOk, textColor, backColor,
            weatherCancel, weatherOk, addReminder, reminderCancel, reminderOk, addRCancel, addROk;
    Button text1, text2, text3, text4, text5, text6, text7, text8;
    Button back1, back2, back3, back4, back5, back6, back7, back8;
    Button a1, a2, a3, b1, b2, b3, c1, c2, c3;

    RadioButton sleepSelected, powerSelected, formatSelected, timezoneSelected, gesturesSelected, vcSelected;
    RadioButton a1format, a2format, a3format, b1format, b2format, b3format, c1format, c2format, c3format;
    RadioButton a1timezone, a2timezone, a3timezone, b1timezone, b2timezone, b3timezone, c1timezone, c2timezone, c3timezone;

    int a1text, a2text, a3text, b1text, b2text, b3text, c1text, c2text, c3text, textTemp;
    int a1back, a2back, a3back, b1back, b2back, b3back, c1back, c2back, c3back, backTemp;

    Spinner stateSpinner, citySpinner;

    TimePicker timePick;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.network:
                if(findViewById(R.id.networkSwitch).isShown())
                {
                    return true;
                }
                switcher.showNext();
                return true;

            case R.id.sleep:
                final Dialog sleepDialog = new Dialog(this);

                sleepDialog.setContentView(R.layout.sleep);
                List<String> sleepList = new ArrayList<>();
                sleepList.add("30 Seconds");
                sleepList.add("10 Minutes");
                sleepList.add("20 Minutes");
                sleepList.add("30 Minutes");
                sleepList.add("1 Hour");
                sleepList.add("2 Hours");
                sleepList.add("No Sleep");

                final RadioGroup rg = (RadioGroup) sleepDialog.findViewById(R.id.sleepGroup);
                for (int i=0; i<sleepList.size(); i++)
                {
                    RadioButton rb = new RadioButton(this);
                    rb.setText(sleepList.get(i));
                    rb.setId(i+1);
                    rg.addView(rb);
                    if(sleepSelected != null)
                    {
                        if(i == (sleepSelected.getId()-1))
                        {
                            rb.toggle();
                        }
                    }
                    else
                    {
                        if(i == 0)
                        {
                            rb.toggle();
                        }
                    }
                }

                sleepCancel = (Button) sleepDialog.findViewById(R.id.sleepCancel);
                sleepCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sleepDialog.dismiss();
                    }
                });

                sleepOk = (Button) sleepDialog.findViewById(R.id.sleepOk);
                sleepOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = rg.getCheckedRadioButtonId();
                        if(id == 1)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror will sleep after 30 Seconds", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 2)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror will sleep after 10 Minutes", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 3)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror will sleep after 20 Minutes", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 4)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror will sleep after 30 Minutes", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 5)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror will sleep after 1 Hour", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 6)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror will sleep after 2 Hours", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 7)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror will not sleep", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
                        }
                        sleepSelected = (RadioButton) sleepDialog.findViewById(id);
                        sleepDialog.dismiss();
                    }
                });


                sleepDialog.show();

                return true;

            case R.id.power:
                final Dialog powerDialog = new Dialog(this);

                powerDialog.setContentView(R.layout.power);
                List<String> powerList = new ArrayList<>();
                powerList.add("Mirror On");
                powerList.add("Mirror Off");

                final RadioGroup rg2 = (RadioGroup) powerDialog.findViewById(R.id.sleepGroup);
                for (int i=0; i<powerList.size(); i++)
                {
                    RadioButton rb2 = new RadioButton(this);
                    rb2.setText(powerList.get(i));
                    rb2.setId(i+1);
                    rg2.addView(rb2);
                    if(powerSelected != null)
                    {
                        if(i == (powerSelected.getId()-1))
                        {
                            rb2.toggle();
                        }
                    }
                    else
                    {
                        if(i == 0)
                        {
                            rb2.toggle();
                        }
                    }
                }

                powerCancel = (Button) powerDialog.findViewById(R.id.powerCancel);
                powerCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        powerDialog.dismiss();
                    }
                });

                powerOk = (Button) powerDialog.findViewById(R.id.powerOk);
                powerOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = rg2.getCheckedRadioButtonId();
                        if(id == 1)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror On", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 2)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Mirror Off", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
                        }
                        powerSelected = (RadioButton) powerDialog.findViewById(id);
                        powerDialog.dismiss();
                    }
                });

                powerDialog.show();

                return true;

            case R.id.handGestures:
                final Dialog gestureDialog = new Dialog(this);

                gestureDialog.setContentView(R.layout.hand_gestures);
                List<String> gestureList = new ArrayList<>();
                gestureList.add("On");
                gestureList.add("Off");

                final RadioGroup gestureGroup = (RadioGroup) gestureDialog.findViewById(R.id.gesturesGroup);
                for (int i=0; i<gestureList.size(); i++)
                {
                    RadioButton gestureRadio = new RadioButton(this);
                    gestureRadio.setText(gestureList.get(i));
                    gestureRadio.setId(i+1);
                    gestureGroup.addView(gestureRadio);
                    if(gesturesSelected != null)
                    {
                        if(i == (gesturesSelected.getId()-1))
                        {
                            gestureRadio.toggle();
                        }
                    }
                    else
                    {
                        if(i == 0)
                        {
                            gestureRadio.toggle();
                        }
                    }
                }

                gesturesCancel = (Button) gestureDialog.findViewById(R.id.gesturesCancel);
                gesturesCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gestureDialog.dismiss();
                    }
                });

                gesturesOk = (Button) gestureDialog.findViewById(R.id.gesturesOk);
                gesturesOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = gestureGroup.getCheckedRadioButtonId();
                        if(id == 1)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Hand Gestures On", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 2)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Hand Gestures Off", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
                        }
                        gesturesSelected = (RadioButton) gestureDialog.findViewById(id);
                        gestureDialog.dismiss();
                    }
                });

                gestureDialog.show();

                return true;

            case R.id.voiceControl:
                final Dialog vcDialog = new Dialog(this);

                vcDialog.setContentView(R.layout.voice_control);
                List<String> vcList = new ArrayList<>();
                vcList.add("On");
                vcList.add("Off");

                final RadioGroup vcGroup = (RadioGroup) vcDialog.findViewById(R.id.vcontrolGroup);
                for (int i=0; i<vcList.size(); i++)
                {
                    RadioButton vcRadio = new RadioButton(this);
                    vcRadio.setText(vcList.get(i));
                    vcRadio.setId(i+1);
                    vcGroup.addView(vcRadio);
                    if(vcSelected != null)
                    {
                        if(i == (vcSelected.getId()-1))
                        {
                            vcRadio.toggle();
                        }
                    }
                    else
                    {
                        if(i == 0)
                        {
                            vcRadio.toggle();
                        }
                    }
                }

                vcCancel = (Button) vcDialog.findViewById(R.id.vcontrolCancel);
                vcCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vcDialog.dismiss();
                    }
                });

                vcOk = (Button) vcDialog.findViewById(R.id.vcontrolOk);
                vcOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = vcGroup.getCheckedRadioButtonId();
                        if(id == 1)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Voice Control On", Toast.LENGTH_LONG).show();
                        }
                        else if (id == 2)
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Voice Control Off", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Log.d("ID", String.valueOf(id));
                            Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
                        }
                        vcSelected = (RadioButton) vcDialog.findViewById(id);
                        vcDialog.dismiss();
                    }
                });

                vcDialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public int textColorPicker()
    {
        final Dialog colorPicker = new Dialog(this);

        colorPicker.setContentView(R.layout.text_color_picker);

        text1 = (Button) colorPicker.findViewById(R.id.text1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#ff7903");
                textColor.setBackgroundColor(textTemp);
                colorPicker.dismiss();
            }
        });

        text2 = (Button) colorPicker.findViewById(R.id.text2);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#ffbc03");
                textColor.setBackgroundColor(textTemp);
                colorPicker.dismiss();
            }
        });

        text3 = (Button) colorPicker.findViewById(R.id.text3);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#29ff03");
                textColor.setBackgroundColor(textTemp);
                colorPicker.dismiss();
            }
        });

        text4 = (Button) colorPicker.findViewById(R.id.text4);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#03ffd5");
                textColor.setBackgroundColor(textTemp);
                colorPicker.dismiss();
            }
        });

        text5 = (Button) colorPicker.findViewById(R.id.text5);
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#929aff");
                textColor.setBackgroundColor(textTemp);
                colorPicker.dismiss();
            }
        });

        text6 = (Button) colorPicker.findViewById(R.id.text6);
        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#c655ff");
                textColor.setBackgroundColor(textTemp);
                colorPicker.dismiss();
            }
        });

        text7 = (Button) colorPicker.findViewById(R.id.text7);
        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#ff0019");
                textColor.setBackgroundColor(textTemp);
                colorPicker.dismiss();
            }
        });

        text8 = (Button) colorPicker.findViewById(R.id.text8);
        text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = Color.parseColor("#ffffff");
                textColor.setBackgroundResource(R.drawable.button);
                colorPicker.dismiss();
            }
        });

        colorPicker.show();

        return textTemp;
    }

    public int backColorPicker()
    {
        final Dialog colorPicker = new Dialog(this);

        colorPicker.setContentView(R.layout.back_color_picker);

        back1 = (Button) colorPicker.findViewById(R.id.back1);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#330e01");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        back2 = (Button) colorPicker.findViewById(R.id.back2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#0b4600");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        back3 = (Button) colorPicker.findViewById(R.id.back3);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#005042");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        back4 = (Button) colorPicker.findViewById(R.id.back4);
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#15172e");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        back5 = (Button) colorPicker.findViewById(R.id.back5);
        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#290c38");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        back6 = (Button) colorPicker.findViewById(R.id.back6);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#4e032b");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        back7 = (Button) colorPicker.findViewById(R.id.back7);
        back7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#4b0007");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        back8 = (Button) colorPicker.findViewById(R.id.back8);
        back8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = Color.parseColor("#000000");
                backColor.setBackgroundColor(backTemp);
                colorPicker.dismiss();
            }
        });

        colorPicker.show();

        return backTemp;
    }

    public void colorOptions(String row, int column)
    {
        // Create a dialog and set the content to color_options.xml
        final Dialog colorDialog = new Dialog(this);
        colorDialog.setContentView(R.layout.color_options);

        // Register buttons and set the default button for the background color to black
        textColor = (Button) colorDialog.findViewById(R.id.textColorButton);
        backColor = (Button) colorDialog.findViewById(R.id.backColorButton);
        backColor.setBackgroundColor(Color.parseColor("#000000"));

        // Determine the panel that is being edited
        //     Set the background of the textColor and backColor buttons
        //     If the background of the textColor button is white, set the
        //       background resource such that the button gets an outline.
        //     Log the color value for debugging purposes
        if(row.equals("A") && column == 1)
        {
            if(a1text != 0)
            {
                textColor.setBackgroundColor(a1text);
            }
            else
            {
                textColor.setBackgroundResource(R.drawable.button);
            }
            if(a1back != 0)
            {
                backColor.setBackgroundColor(a1back);
            }
            Log.d("A1_ColorOptions", String.valueOf(a1text) + " " + String.valueOf(a1back));
        }
        else if(row.equals("A") && column == 2)
        {
            if(a2text != 0)
            {
                textColor.setBackgroundColor(a2text);
            }
            if(a2back != 0)
            {
                backColor.setBackgroundColor(a2back);
            }
            Log.d("A2_ColorOptions", String.valueOf(a2text) + " " + String.valueOf(a2back));
        }
        else if(row.equals("A") && column == 3)
        {
            if(a3text != 0)
            {
                textColor.setBackgroundColor(a3text);
            }
            if(a3back != 0)
            {
                backColor.setBackgroundColor(a3back);
            }
            Log.d("A3_ColorOptions", String.valueOf(a3text) + " " + String.valueOf(a3back));
        }
        else if(row.equals("B") && column == 1)
        {
            if(b1text != 0)
            {
                textColor.setBackgroundColor(b1text);
            }
            if(b1back != 0)
            {
                backColor.setBackgroundColor(b1back);
            }
            Log.d("B1_ColorOptions", String.valueOf(b1text) + " " + String.valueOf(b1back));
        }
        else if(row.equals("B") && column == 2)
        {
            if(b2text != 0)
            {
                textColor.setBackgroundColor(b2text);
            }
            if(b2back != 0)
            {
                backColor.setBackgroundColor(b2back);
            }
            Log.d("B2_ColorOptions", String.valueOf(b2text) + " " + String.valueOf(b2back));
        }
        else if(row.equals("B") && column == 3)
        {
            if(b3text != 0)
            {
                textColor.setBackgroundColor(b3text);
            }
            if(b3back != 0)
            {
                backColor.setBackgroundColor(b3back);
            }
            Log.d("B3_ColorOptions", String.valueOf(b3text) + " " + String.valueOf(b3back));
        }
        else if(row.equals("C") && column == 1)
        {
            if(c1text != 0)
            {
                textColor.setBackgroundColor(c1text);
            }
            if(c1back != 0)
            {
                backColor.setBackgroundColor(c1back);
            }
            Log.d("C1_ColorOptions", String.valueOf(c1text) + " " + String.valueOf(c1back));
        }
        else if(row.equals("C") && column == 2)
        {
            if(c2text != 0)
            {
                textColor.setBackgroundColor(c2text);
            }
            if(c2back != 0)
            {
                backColor.setBackgroundColor(c2back);
            }
            Log.d("C2_ColorOptions", String.valueOf(c2text) + " " + String.valueOf(c2back));
        }
        else if(row.equals("C") && column == 3)
        {
            if(c3text != 0)
            {
                textColor.setBackgroundColor(c3text);
            }
            if(c3back != 0)
            {
                backColor.setBackgroundColor(c3back);
            }
            Log.d("C3_ColorOptions", String.valueOf(c3text) + " " + String.valueOf(c3back));
        }
        else
        {
            Log.d("CRAP", "Why are you here?");
        }

        // If the user selects the textTemp button, run the code to change
        // the color. Save the new color in textTemp.
        // Log the color for debugging purposes.
        textColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textTemp = textColorPicker();
                Log.d("textTemp", String.valueOf(textTemp));
            }
        });

        // If the user selects the backTemp button, run the code to change
        // the color. Store the new color in textTemp.
        // Log the color for debugging purposes.
        backColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backTemp = backColorPicker();
                Log.d("backTemp", String.valueOf(backTemp));
            }
        });

        // Register the cancel button. If the user selects cancel,
        // dismiss the colorDialog
        colorCancel = (Button) colorDialog.findViewById(R.id.colorCancel);
        colorCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorDialog.dismiss();
            }
        });

        // Register the ok button. If the user selects ok, store the
        // color in textTemp to the appropriate panel color variable.
        //
        // Example:
        //     a1text - stores the text color for the a1 panel.
        //     c3back - stores the background color for the c3 panel.
        //
        // Dismiss the colorDialog when finished.
        // Log colors for debugging purposes.
        colorOk = (Button) colorDialog.findViewById(R.id.colorOk);

        if(row.equals("A") && column == 1)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a1text = textTemp;
                    a1.setTextColor(a1text);
                    a1back = backTemp;
                    a1.setBackgroundColor(a1back);
                    colorDialog.dismiss();
                }
            });
            Log.d("A1_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("A") && column == 2)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a2text = textTemp;
                    a2.setTextColor(a2text);
                    a2back = backTemp;
                    a2.setBackgroundColor(a2back);
                    colorDialog.dismiss();
                }
            });
            Log.d("A2_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("A") && column == 3)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    a3text = textTemp;
                    a3.setTextColor(a3text);
                    a3back = backTemp;
                    a3.setBackgroundColor(a3back);
                    colorDialog.dismiss();
                }
            });
            Log.d("A3_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("B") && column == 1)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    b1text = textTemp;
                    b1.setTextColor(b1text);
                    b1back = backTemp;
                    b1.setBackgroundColor(b1back);
                    colorDialog.dismiss();
                }
            });
            Log.d("B1_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("B") && column == 2)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    b2text = textTemp;
                    b2.setTextColor(b2text);
                    b2back = backTemp;
                    b2.setBackgroundColor(b2back);
                    colorDialog.dismiss();
                }
            });
            Log.d("B2_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("B") && column == 3)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    b3text = textTemp;
                    b3.setTextColor(b3text);
                    b3back = backTemp;
                    b3.setBackgroundColor(b3back);
                    colorDialog.dismiss();
                }
            });
            Log.d("B3_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("C") && column == 1)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    c1text = textTemp;
                    c1.setTextColor(c1text);
                    c1back = backTemp;
                    c1.setBackgroundColor(c1back);
                    colorDialog.dismiss();
                }
            });
            Log.d("C1_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("C") && column == 2)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    c2text = textTemp;
                    c2.setTextColor(c2text);
                    c2back = backTemp;
                    c2.setBackgroundColor(c2back);
                    colorDialog.dismiss();
                }
            });
            Log.d("C2_ColorOptions", String.valueOf(a1text));
        }
        else if(row.equals("C") && column == 3)
        {
            colorOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    c3text = textTemp;
                    c3.setTextColor(c3text);
                    c3back = backTemp;
                    c3.setBackgroundColor(c3back);
                    colorDialog.dismiss();
                }
            });
            Log.d("C3_ColorOptions", String.valueOf(a1text));
        }
        else
        {
            Log.d("CRAP", "Why are you here?");
        }

        // If the text color is white, surround the textColor
        // button with a black outline.
        if(textTemp == -1)
        {
            textColor.setBackgroundResource(R.drawable.button);
        }

        // Display the dialog.
        colorDialog.show();
    }

    public void dismissOption(String rowFinal, int columnFinal, String current)
    {
        if(rowFinal.equals("A") && columnFinal == 1)
        {
            a1.setText(current);
//            switch(current) {
//                case "+":
//                    a1.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    //a1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                    break;
//            }
        }
        else if(rowFinal.equals("A") && columnFinal == 2)
        {
            a2.setText(current);
//            switch(current) {
//                case "+":
//                    a2.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    //a2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                    break;
//            }
        }
        else if(rowFinal.equals("A") && columnFinal == 3)
        {
            a3.setText(current);
//            switch(current) {
//                case "+":
//                    a3.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    //a3.setBackgroundColor(Color.parseColor("#a90a1a"));
//                    break;
//            }
        }
        else if(rowFinal.equals("B") && columnFinal == 1)
        {
            b1.setText(current);
//            switch(current) {
//                case "+":
//                    b1.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    //b1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                    break;
//            }
        }
        else if(rowFinal.equals("B") && columnFinal == 2)
        {
            b2.setText(current);
//            switch(current) {
//                case "+":
//                    b2.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    //b2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                    break;
//            }
        }
        else if(rowFinal.equals("B") && columnFinal == 3)
        {
            b3.setText(current);
//            switch(current) {
//                case "+":
//                    b3.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    b3.setBackgroundColor(Color.parseColor("#a90a1a"));
//            }
        }
        else if(rowFinal.equals("C") && columnFinal == 1)
        {
            c1.setText(current);
//            switch(current) {
//                case "+":
//                    c1.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    c1.setBackgroundColor(Color.parseColor("#a90a1a"));
//            }
        }
        else if(rowFinal.equals("C") && columnFinal == 2)
        {
            c2.setText(current);
//            switch(current) {
//                case "+":
//                    c2.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    c2.setBackgroundColor(Color.parseColor("#a90a1a"));
//            }
        }
        else if(rowFinal.equals("C") && columnFinal == 3)
        {
            c3.setText(current);
//            switch(current) {
//                case "+":
//                    c3.setBackgroundColor(Color.parseColor("#000000"));
//                    break;
//                default:
//                    c3.setBackgroundColor(Color.parseColor("#a90a1a"));
//            }
        }
    }


    public void clockOptions(String row, int column, Boolean longC, final String currentSetting)
    {
        final String rowFinal = row;
        final int columnFinal = column;
        final Boolean longClick = longC;

        // Create a dialog and set the content to clock_options.xml
        final Dialog clockDialog = new Dialog(this);
        clockDialog.setContentView(R.layout.clock_options);

        // Create an array list of clock formats (formatClockList)
        List<String> formatClockList = new ArrayList<>();
        formatClockList.add("Standard");
        formatClockList.add("Military");

        // Create a radio group and link to clockFormatGroup in clock_options.xml
        // For each item in the formatClockList:
        //     Create a radio button
        //     Set the ID and text of the button.
        //     Add the radio button the the radio group
        //     If the ID of the format previously selected is the current id,
        //       toggle the radio button such that it is checked.
        final RadioGroup format = (RadioGroup) clockDialog.findViewById(R.id.clockFormatGroup);
        for (int i=0; i<formatClockList.size(); i++)
        {
            RadioButton formatRadio = new RadioButton(this);
            formatRadio.setText(formatClockList.get(i));
            formatRadio.setId(i+1);
            format.addView(formatRadio);
            if(row.equals("A") && column == 1)
            {
                if(a1format != null && i == (a1format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("A") && column == 2)
            {
                if(a2format != null && i == (a2format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("A") && column == 3)
            {
                if(a3format != null && i == (a3format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("B") && column == 1)
            {
                if(b1format != null && i == (b1format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("B") && column == 2)
            {
                if(b2format != null && i == (b2format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("B") && column == 3)
            {
                if(b3format != null && i == (b3format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("C") && column == 1)
            {
                if(c1format != null && i == (c1format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("C") && column == 2)
            {
                if(c2format != null && i == (c2format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else if(row.equals("C") && column == 3)
            {
                if(c3format != null && i == (c3format.getId()-1))
                {
                    formatRadio.toggle();
                }
            }
            else
            {
                Log.d("CRAP", "Why are you here?");
            }
        }

        // Create an array list of timezones
        List<String> timezoneList = new ArrayList<>();
        timezoneList.add("Eastern");
        timezoneList.add("Central");
        timezoneList.add("Mountain");
        timezoneList.add("Mountain (No DST)");
        timezoneList.add("Pacific");
        timezoneList.add("Alaska");
        timezoneList.add("Hawaii-Aleutian");
        timezoneList.add("Hawaii-Aleutian (No DST)");

        final RadioGroup timezone = (RadioGroup) clockDialog.findViewById(R.id.clockTimezoneGroup);
        for (int i = 0; i<timezoneList.size(); i++)
        {
            RadioButton zoneRadio = new RadioButton(this);
            zoneRadio.setText(timezoneList.get(i));
            zoneRadio.setId(i+1);
            timezone.addView(zoneRadio);

            if(row.equals("A") && column == 1)
            {
                if(a1timezone != null && i == (a1timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("A") && column == 2)
            {
                if(a2timezone != null && i == (a2timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("A") && column == 3)
            {
                if(a3timezone != null && i == (a3timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("B") && column == 1)
            {
                if(b1timezone != null && i == (b1timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("B") && column == 2)
            {
                if(b2timezone != null && i == (b2timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("B") && column == 3)
            {
                if(b3timezone != null && i == (b3timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("C") && column == 1)
            {
                if(c1timezone != null && i == (c1timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("C") && column == 2)
            {
                if(c2timezone != null && i == (c2timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else if(row.equals("C") && column == 3)
            {
                if(c3timezone != null && i == (c3timezone.getId()-1))
                {
                    zoneRadio.toggle();
                }
            }
            else
            {
                Log.d("CRAP", "Why are you here?");
            }

        }

        clockCancel = (Button) clockDialog.findViewById(R.id.clockCancel);
        clockCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!longClick)
                {
                    dismissOption(rowFinal, columnFinal, currentSetting);
                }
                clockDialog.dismiss();
            }
        });

        clockOk = (Button) clockDialog.findViewById(R.id.clockOk);
        clockOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int formatID = format.getCheckedRadioButtonId();
                int timezoneID = timezone.getCheckedRadioButtonId();

                String toastStr = "";

                if(formatID == 1)
                {
                    Log.d("ID", String.valueOf(formatID));
                    toastStr = toastStr + "Standard ";
                }
                else if (formatID == 2)
                {
                    Log.d("ID", String.valueOf(formatID));
                    toastStr = toastStr + "Military ";
                }
                else
                {
                    Log.d("ID", String.valueOf(formatID));
                }

                if(timezoneID == 1)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Eastern Time Selected";
                }
                else if(timezoneID == 2)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Central Time Selected";
                }
                else if(timezoneID == 3)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Mountain Time Selected";
                }
                else if(timezoneID == 4)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Mountain Time (No DST) Selected";
                }
                else if(timezoneID == 5)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Pacific Time Selected";
                }
                else if(timezoneID == 6)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Alaskan Time Selected";
                }
                else if(timezoneID == 7)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Hawaii-Aleutian Time Selected";
                }
                else if(timezoneID == 8)
                {
                    Log.d("ID", String.valueOf(timezoneID));
                    toastStr = toastStr + "- Hawaii-Aleutian Time (No DST) Selected";
                }
                else
                {
                    Log.d("ID", String.valueOf(timezoneID));
                }

                formatSelected = (RadioButton) clockDialog.findViewById(formatID);
                timezoneSelected = (RadioButton) clockDialog.findViewById(timezoneID);

                if(rowFinal.equals("A") && columnFinal == 1)
                {
                    if(formatSelected != null)
                    {
                        a1format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        a1timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("A") && columnFinal == 2)
                {
                    if(formatSelected != null)
                    {
                        a2format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        a2timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("A") && columnFinal == 3)
                {
                    if(formatSelected != null)
                    {
                        a3format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        a3timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("B") && columnFinal == 1)
                {
                    if(formatSelected != null)
                    {
                        b1format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        b1timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("B") && columnFinal == 2)
                {
                    if(formatSelected != null)
                    {
                        b2format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        b2timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("B") && columnFinal == 3)
                {
                    if(formatSelected != null)
                    {
                        b3format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        b3timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("C") && columnFinal == 1)
                {
                    if(formatSelected != null)
                    {
                        c1format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        c1timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("C") && columnFinal == 2)
                {
                    if(formatSelected != null)
                    {
                        c2format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        c2timezone = timezoneSelected;
                    }
                }
                else if(rowFinal.equals("C") && columnFinal == 3)
                {
                    if(formatSelected != null)
                    {
                        c3format = formatSelected;
                    }
                    if(timezoneSelected != null)
                    {
                        c3timezone = timezoneSelected;
                    }
                }
                else
                {
                    Log.d("CRAP", "Why are you here?");
                }

                if(formatSelected != null && timezoneSelected != null)
                {
                    Toast.makeText(getApplicationContext(), toastStr, Toast.LENGTH_SHORT).show();
                    clockDialog.dismiss();
                }
                else if(formatSelected == null && timezoneSelected != null)
                {
                    Toast.makeText(getApplicationContext(), "Please Select Format", Toast.LENGTH_LONG).show();
                }
                else if(formatSelected != null && timezoneSelected == null)
                {
                    Toast.makeText(getApplicationContext(), "Please Select Timezone", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Select Time Settings", Toast.LENGTH_LONG).show();
                }

            }
        });

        clockDialog.show();
    }

    public void weatherOptions(String row, int column, Boolean longC, final String currentSetting)
    {
        final String rowFinal = row;
        final int columnFinal = column;
        final Boolean longClick = longC;

        // Create a dialog and set the content to clock_options.xml
        final Dialog weatherDialog = new Dialog(this);
        weatherDialog.setContentView(R.layout.weather_options);

        stateSpinner = (Spinner) weatherDialog.findViewById(R.id.stateSpinner);
        citySpinner = (Spinner) weatherDialog.findViewById(R.id.citySpinner);

        weatherCancel = (Button) weatherDialog.findViewById(R.id.weatherCancel);
        weatherCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(!longClick)
                {
                    dismissOption(rowFinal, columnFinal, currentSetting);
                }
                weatherDialog.dismiss();
            }
        });

        weatherOk = (Button) weatherDialog.findViewById(R.id.weatherOk);
        weatherOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(citySelected != null && stateSelected != null)
                {
                    Toast.makeText(getApplicationContext(), "These weather options selected", Toast.LENGTH_SHORT).show();
                    weatherDialog.dismiss();
                }
                else if(citySelected == null && stateSelected != null)
                {
                    Toast.makeText(getApplicationContext(), "Please Select City", Toast.LENGTH_LONG).show();
                }
                else if(citySelected != null && stateSelected == null)
                {
                    Toast.makeText(getApplicationContext(), "Please Select State", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Select Weather Settings", Toast.LENGTH_LONG).show();
                }
            }
        });

        weatherDialog.show();
    }

    public void reminderOptions(String row, int column, Boolean longC, final String currentSetting)
    {
        final String rowFinal = row;
        final int columnFinal = column;
        final Boolean longClick = longC;

        // Create a dialog and set the content to clock_options.xml
        final Dialog reminderDialog = new Dialog(this);
        final Dialog addReminderDialog = new Dialog(this);

        reminderDialog.setContentView(R.layout.reminder_options);
        addReminderDialog.setContentView(R.layout.add_reminder);

        addReminder = (Button) reminderDialog.findViewById(R.id.addReminder);
        addReminder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                timePick = (TimePicker) addReminderDialog.findViewById(R.id.timePicker);

                addRCancel = (Button) addReminderDialog.findViewById(R.id.reminderCancel);
                addRCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        addReminderDialog.dismiss();
                    }
                });

                addROk = (Button) addReminderDialog.findViewById(R.id.reminderOk);
                addROk.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v)
                    {
                        // if the reminder list is not empty
                        addReminderDialog.dismiss();
                        // else Toast.makeText(getApplicationContext(), "Please Set at Least One Reminder", Toast.LENGTH_LONG).show();
                    }
                });

                addReminderDialog.show();
            }
        });

        reminderCancel = (Button) reminderDialog.findViewById(R.id.reminderCancel);
        reminderCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if(!longClick)
                {
                    dismissOption(rowFinal, columnFinal, currentSetting);
                }
                reminderDialog.dismiss();
            }
        });

        reminderOk = (Button) reminderDialog.findViewById(R.id.reminderOk);
        reminderOk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                reminderDialog.dismiss();
            }
        });

        reminderDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switcher = (ViewSwitcher) findViewById(R.id.activity_main);

        ip = (EditText) findViewById(R.id.ipEdit);
        ipString = ip.getText().toString();

        port = (EditText) findViewById(R.id.portEdit);
        portString = port.getText().toString();

        connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switcher.showNext();
            }
        });

        a1 = (Button) findViewById(R.id.A1);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1.setBackgroundColor(Color.parseColor("#fa6f04"));

                a1Current = a1.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, a1);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        a1String = item.getTitle().toString();
                        switch (a1String) {
                            case "Clear Panel":
                                a1timezone = null;
                                a1format = null;
                                a1.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("A", 1);
                                popup.dismiss();
                                break;
                            case "Clock":
                                a1.setText(item.getTitle());
                                clockOptions("A", 1, false, a1Current);
                                break;
                            case "Weather":
                                a1.setText(item.getTitle());
                                weatherOptions("A", 1, false, a1Current);
                                break;
                            case "Reminder":
                                a1.setText(item.getTitle());
                                reminderOptions("A", 1, false, a1Current);
                                break;
                            default:
                                a1.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        a1.setBackgroundColor(a1back);
//                        switch (a1String) {
//                            case "Clear Panel":
//                                a1.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                a1.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(a1.getText().equals("+"))
//                                {
//                                    a1.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    a1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                a1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();

            }
        });

        a1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a1Current = a1.getText().toString();

                if(a1.getText().equals("Clock"))
                {
                    clockOptions("A", 1, true, a1Current);
                }
                else if(a1.getText().equals("Weather"))
                {
                    weatherOptions("A", 1, true, a1Current);
                }
                else if(a1.getText().equals("Reminder"))
                {
                    reminderOptions("A", 1, true, a1Current);
                }
                return true;
            }
        });

        a2 = (Button) findViewById(R.id.A2);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a2.setBackgroundColor(Color.parseColor("#fa6f04"));

                a2Current = a2.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, a2);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        a2String = item.getTitle().toString();
                        switch (a2String) {
                            case "Clear Panel":
                                a2timezone = null;
                                a2format = null;
                                a2.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("A", 2);
                                popup.dismiss();
                                break;
                            case "Clock":
                                a2.setText(item.getTitle());
                                clockOptions("A", 2, false, a2Current);
                                break;
                            case "Weather":
                                a2.setText(item.getTitle());
                                weatherOptions("A", 2, false, a2Current);
                                break;
                            case "Reminder":
                                a2.setText(item.getTitle());
                                reminderOptions("A", 2, false, a2Current);
                                break;
                            default:
                                a2.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        a2.setBackgroundColor(a2back);
//                        switch (a2String) {
//                            case "Clear Panel":
//                                a2.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                a2.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(a2.getText().equals("+"))
//                                {
//                                    a2.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    a2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                a2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();
            }
        });

        a2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a2Current = a2.getText().toString();

                if(a2.getText().equals("Clock"))
                {
                    clockOptions("A", 2, true, a2Current);
                }
                else if(a2.getText().equals("Weather"))
                {
                    weatherOptions("A", 2, true, a2Current);
                }
                else if(a2.getText().equals("Reminder"))
                {
                    reminderOptions("A", 2, true, a2Current);
                }
                return true;
            }
        });

        a3 = (Button) findViewById(R.id.A3);
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a3.setBackgroundColor(Color.parseColor("#fa6f04"));

                a3Current = a3.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, a3);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        a3String = item.getTitle().toString();
                        switch (a3String) {
                            case "Clear Panel":
                                a3timezone = null;
                                a3format = null;
                                a3.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("A", 3);
                                popup.dismiss();
                                break;
                            case "Clock":
                                a3.setText(item.getTitle());
                                clockOptions("A", 3, false, a3Current);
                                break;
                            case "Weather":
                                a3.setText(item.getTitle());
                                weatherOptions("A", 3, false, a3Current);
                                break;
                            case "Reminder":
                                a3.setText(item.getTitle());
                                reminderOptions("A", 3, false, a3Current);
                                break;
                            default:
                                a3.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        a3.setBackgroundColor(a3back);
//                        switch (a3String) {
//                            case "Clear Panel":
//                                a3.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                a3.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(a3.getText().equals("+"))
//                                {
//                                    a3.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    a3.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                a3.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();
            }
        });

        a3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                a3Current = a3.getText().toString();

                if(a3.getText().equals("Clock"))
                {
                    clockOptions("A", 3, true, a3Current);
                }
                else if(a3.getText().equals("Weather"))
                {
                    weatherOptions("A", 3, true, a3Current);
                }
                else if(a3.getText().equals("Reminder"))
                {
                    reminderOptions("A", 3, true, a3Current);
                }
                return true;
            }
        });

        b1 = (Button) findViewById(R.id.B1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setBackgroundColor(Color.parseColor("#fa6f04"));

                b1Current = b1.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, b1);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        b1String = item.getTitle().toString();
                        switch (b1String) {
                            case "Clear Panel":
                                b1timezone = null;
                                b1format = null;
                                b1.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("B", 1);
                                popup.dismiss();
                                break;
                            case "Clock":
                                b1.setText(item.getTitle());
                                clockOptions("B", 1, false, b1Current);
                                break;
                            case "Weather":
                                b1.setText(item.getTitle());
                                weatherOptions("B", 1, false, b1Current);
                                break;
                            case "Reminder":
                                b1.setText(item.getTitle());
                                reminderOptions("B", 1, false, b1Current);
                                break;
                            default:
                                b1.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        b1.setBackgroundColor(b1back);
//                        switch (b1String) {
//                            case "Clear Panel":
//                                b1.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                b1.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(b1.getText().equals("+"))
//                                {
//                                    b1.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    b1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                b1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();
            }
        });

        b1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                b1Current = b1.getText().toString();

                if(b1.getText().equals("Clock"))
                {
                    clockOptions("B", 1, true, b1Current);
                }
                else if(b1.getText().equals("Weather"))
                {
                    weatherOptions("B", 1, true, b1Current);
                }
                else if(b1.getText().equals("Reminder"))
                {
                    reminderOptions("B", 1, true, b1Current);
                }
                return true;
            }
        });

        b2 = (Button) findViewById(R.id.B2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setBackgroundColor(Color.parseColor("#fa6f04"));

                b2Current = b2.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, b2);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        b2String = item.getTitle().toString();
                        switch (b2String) {
                            case "Clear Panel":
                                b2timezone = null;
                                b2format = null;
                                b2.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("B", 2);
                                popup.dismiss();
                                break;
                            case "Clock":
                                b2.setText(item.getTitle());
                                clockOptions("B", 2, false, b2Current);
                                break;
                            case "Weather":
                                b2.setText(item.getTitle());
                                weatherOptions("B", 2, false, b2Current);
                                break;
                            case "Reminder":
                                b2.setText(item.getTitle());
                                reminderOptions("B", 2, false, b2Current);
                                break;
                            default:
                                b2.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        b2.setBackgroundColor(b2back);
//                        switch (b2String) {
//                            case "Clear Panel":
//                                b2.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                b2.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(b2.getText().equals("+"))
//                                {
//                                    b2.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    b2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                b2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();
            }
        });

        b2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                b2Current = b2.getText().toString();

                if(b2.getText().equals("Clock"))
                {
                    clockOptions("B", 2, true, b2Current);
                }
                else if(b2.getText().equals("Weather"))
                {
                    weatherOptions("B", 2, true, b2Current);
                }
                else if(b2.getText().equals("Reminder"))
                {
                    reminderOptions("B", 2, true, b2Current);
                }
                return true;
            }
        });

        b3 = (Button) findViewById(R.id.B3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3.setBackgroundColor(Color.parseColor("#fa6f04"));

                b3Current = b3.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, b3);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        b3String = item.getTitle().toString();
                        switch (b3String) {
                            case "Clear Panel":
                                b3timezone = null;
                                b3format = null;
                                b3.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("B", 3);
                                popup.dismiss();
                                break;
                            case "Clock":
                                b3.setText(item.getTitle());
                                clockOptions("B", 3, false, b3Current);
                                break;
                            case "Weather":
                                b3.setText(item.getTitle());
                                weatherOptions("B", 3, false, b3Current);
                                break;
                            case "Reminder":
                                b3.setText(item.getTitle());
                                reminderOptions("B", 3, false, b3Current);
                                break;
                            default:
                                b3.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        b3.setBackgroundColor(b3back);
//                        switch (b3String) {
//                            case "Clear Panel":
//                                b3.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                b3.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(b3.getText().equals("+"))
//                                {
//                                    b3.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    b3.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                b3.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();
            }
        });

        b3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                b3Current = b3.getText().toString();

                if(b3.getText().equals("Clock"))
                {
                    clockOptions("B", 3, true, b3Current);
                }
                else if(b3.getText().equals("Weather"))
                {
                    weatherOptions("B", 3, true, b3Current);
                }
                else if(b3.getText().equals("Reminder"))
                {
                    reminderOptions("B", 3, true, b3Current);
                }
                return true;
            }
        });

        c1 = (Button) findViewById(R.id.C1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setBackgroundColor(Color.parseColor("#fa6f04"));

                c1Current = c1.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, c1);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        c1String = item.getTitle().toString();
                        switch (c1String) {
                            case "Clear Panel":
                                c1timezone = null;
                                c1format = null;
                                c1.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("C", 1);
                                popup.dismiss();
                                break;
                            case "Clock":
                                c1.setText(item.getTitle());
                                clockOptions("C", 1,false, c1Current);
                                break;
                            case "Weather":
                                c1.setText(item.getTitle());
                                weatherOptions("C", 1, false, c1Current);
                                break;
                            case "Reminder":
                                c1.setText(item.getTitle());
                                reminderOptions("C", 1, false, c1Current);
                                break;
                            default:
                                c1.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        c1.setBackgroundColor(c1back);
//                        switch (c1String) {
//                            case "Clear Panel":
//                                c1.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                c1.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(c1.getText().equals("+"))
//                                {
//                                    c1.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    c1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                c1.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();
            }
        });

        c1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c1Current = c1.getText().toString();

                if(c1.getText().equals("Clock"))
                {
                    clockOptions("C", 1, true, c1Current);
                }
                else if(c1.getText().equals("Weather"))
                {
                    weatherOptions("C", 1, true, c1Current);
                }
                else if(c1.getText().equals("Reminder"))
                {
                    reminderOptions("C", 1, true, c1Current);
                }
                return true;
            }
        });

        c2 = (Button) findViewById(R.id.C2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setBackgroundColor(Color.parseColor("#fa6f04"));

                c2Current = c2.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, c2);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        c2String = item.getTitle().toString();
                        switch (c2String) {
                            case "Clear Panel":
                                c2timezone = null;
                                c2format = null;
                                c2.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("C", 2);
                                popup.dismiss();
                                break;
                            case "Clock":
                                c2.setText(item.getTitle());
                                clockOptions("C", 2, false, c2Current);
                                break;
                            case "Weather":
                                c2.setText(item.getTitle());
                                weatherOptions("C", 2, false, c2Current);
                                break;
                            case "Reminder":
                                c2.setText(item.getTitle());
                                reminderOptions("C", 2, false, c2Current);
                                break;
                            default:
                                c2.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        c2.setBackgroundColor(c2back);
//                        switch (c2String) {
//                            case "Clear Panel":
//                                c2.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                c2.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(c2.getText().equals("+"))
//                                {
//                                    c2.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    c2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                c2.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                popup.show();
            }
        });

        c2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                c2Current = c2.getText().toString();

                if(c2.getText().equals("Clock"))
                {
                    clockOptions("C", 2, true, c2Current);
                }
                else if(c2.getText().equals("Weather"))
                {
                    weatherOptions("C", 2, true, c2Current);
                }
                else if(c2.getText().equals("Reminder"))
                {
                    reminderOptions("C", 2, true, c2Current);
                }
                return true;
            }
        });

        c3 = (Button) findViewById(R.id.C3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c3.setBackgroundColor(Color.parseColor("#fa6f04"));

                c3Current = c3.getText().toString();

                final PopupMenu popup = new PopupMenu(MainActivity.this, c3);
                popup.getMenuInflater().inflate(R.menu.menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        c3String = item.getTitle().toString();
                        switch (c3String) {
                            case "Clear Panel":
                                c3timezone = null;
                                c3format = null;
                                c3.setText("+");
                                break;
                            case "Color Options":
                                colorOptions("C", 3);
                                popup.dismiss();
                                break;
                            case "Clock":
                                c3.setText(item.getTitle());
                                clockOptions("C", 3, false, c3Current);
                                break;
                            case "Weather":
                                c3.setText(item.getTitle());
                                weatherOptions("C", 3, false, c3Current);
                                break;
                            case "Reminder":
                                c3.setText(item.getTitle());
                                reminderOptions("C", 3, false, c3Current);
                                break;
                            default:
                                c3.setText(item.getTitle());
                                break;
                        }
                        return true;
                    }
                });

                popup.setOnDismissListener(new PopupMenu.OnDismissListener(){
                    public void onDismiss(PopupMenu menu) {
                        c3.setBackgroundColor(c3back);
//                        switch (c3String) {
//                            case "Clear Panel":
//                                c3.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "":
//                                c3.setBackgroundColor(Color.parseColor("#000000"));
//                                break;
//                            case "Color Options":
//                                if(c3.getText().equals("+"))
//                                {
//                                    c3.setBackgroundColor(Color.parseColor("#000000"));
//                                }
//                                else
//                                {
//                                    c3.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                }
//                                break;
//                            default:
//                                c3.setBackgroundColor(Color.parseColor("#a90a1a"));
//                                break;
//                        }
                    }
                });

                c3.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        c3Current = c3.getText().toString();

                        if(c3.getText().equals("Clock"))
                        {
                            clockOptions("C", 3, true, c3Current);
                        }
                        else if(c3.getText().equals("Weather"))
                        {
                            weatherOptions("C", 3, true, c3Current);
                        }
                        else if(c3.getText().equals("Reminder"))
                        {
                            reminderOptions("C", 3, true, c3Current);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("A1Text", a1.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        String a1Text = savedInstanceState.getString("A1Text");
        a1.setText(a1Text);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("PAUSE", "Not in focus");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("RESUME", "Became visible");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d("START", "About to become visible");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d("STOP", "No longer visible");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        Log.d("DESTROY", "System destroyed");
    }


}
