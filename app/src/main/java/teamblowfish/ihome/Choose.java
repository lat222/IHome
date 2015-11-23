package teamblowfish.ihome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class Choose extends AppCompatActivity {

    private HouseAccount houseAccount;
    private String house;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        setTitle("iHome");

        Houses housesDB = new Houses();

        Intent loginIntent = getIntent();
        String[] userAndHouse = loginIntent.getStringArrayExtra("userAndHouse");
        house = userAndHouse[1];
        //Toast.makeText(getApplicationContext(),house,Toast.LENGTH_SHORT).show();
        houseAccount = housesDB.findHouseAccount(house);
        user = userAndHouse[0];

        if(houseAccount.getUser(user).getAccountType()=='a'){
            Button settings = new Button(this);
            settings.setText("Settings");
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toSettings = new Intent(Choose.this, Settings.class);
                    toSettings.putExtra("userAndHouse", new String[]{user, house});
                    startActivity(toSettings);
                }
            });

        }

        //Toast.makeText(getApplicationContext(),user,Toast.LENGTH_SHORT).show();

        populateButtons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose, menu);
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

    public void populateButtons() {
        User userAccount = houseAccount.getUser(user);
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        //table.removeAllViewsInLayout();

        if(userAccount.getNumRooms()!=0){
            //Toast.makeText(getApplicationContext(),Integer.toString(userAccount.getNumRooms()),Toast.LENGTH_SHORT).show();
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            Button button=new Button(this);
            button.setText("Rooms");
            button.setPadding(0, 0, 0, 0);     //keeps text from being clipped
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toRooms = new Intent(Choose.this, LightActivity.class);
                    toRooms.putExtra("userAndHouse", new String[]{user, house});
                    startActivity(toRooms);
                }
            });
            tablerow.addView(button);
        }
        if(userAccount.getNumDoors()!=0) {
            Toast.makeText(getApplicationContext(),Integer.toString(userAccount.getNumDoors()),Toast.LENGTH_SHORT).show();
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            Button button = new Button(this);
            button.setText("Doors");
            button.setPadding(0, 0, 0, 0);     //keeps text from being clipped
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toDoors = new Intent(Choose.this, DoorActivity.class);
                    toDoors.putExtra("userAndHouse", new String[]{user, house});
                    startActivity(toDoors);
                }
            });
            tablerow.addView(button);
        }
        if(userAccount.isAccessibleTemp()){
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            Button button=new Button(this);
            button.setText("Temperature");
            button.setPadding(0, 0, 0, 0);     //keeps text from being clipped
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toTemp = new Intent(Choose.this, TempActivity.class);
                    toTemp.putExtra("userAndHouse", new String[] {user,house});
                    startActivity(toTemp);
                }
            });
            tablerow.addView(button);
        }
    }
}
