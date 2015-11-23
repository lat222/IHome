package teamblowfish.ihome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class TempActivity extends AppCompatActivity {

    private HouseAccount houseAccount;
    private String house;
    private String user;
    private SeekBar seekBar;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        setTitle("Change House Temperature");

        Houses housesDB = new Houses();

        Intent loginIntent = getIntent();
        String[] userAndHouse = loginIntent.getStringArrayExtra("userAndHouse");
        house = userAndHouse[1];
        //Toast.makeText(getApplicationContext(),house,Toast.LENGTH_SHORT).show();
        houseAccount = housesDB.findHouseAccount(house);
        user = userAndHouse[0];
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.temp);

        // Initialize the textview
//        textView.setText("Covered: " + seekBar.getProgress() + "/" + seekBar.getMax());
        textView.setText(seekBar.getProgress() + (char) 0x00B0);

        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 68;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
