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

public class DoorActivity extends AppCompatActivity {

    private HouseAccount houseAccount;
    private String house;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);
        setTitle("Lock and Unlock Doors");

        Houses housesDB = new Houses();

        Intent loginIntent = getIntent();
        String[] userAndHouse = loginIntent.getStringArrayExtra("userAndHouse");
        house = userAndHouse[1];
        //Toast.makeText(getApplicationContext(),house,Toast.LENGTH_SHORT).show();
        houseAccount = housesDB.findHouseAccount(house);
        user = userAndHouse[0];

        populateButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_door, menu);
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
        //User userAccount = houseAccount.getUser(user);
        final String[] accessibleDoors = houseAccount.getDoorNames(user);
        //final String[] accessibleDoors = {"Front","Back"};
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        table.removeAllViewsInLayout();
        for(int i=0; i<accessibleDoors.length;i++) {
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            final Button button = new Button(this);
            final int doorIndex = i;
            button.setText(accessibleDoors[i]);
            //Toast.makeText(getApplicationContext(),accessibleDoors[i],Toast.LENGTH_SHORT).show();
            if(houseAccount.getDoor(accessibleDoors[doorIndex]).isLocked()){
                button.setBackgroundResource(R.drawable.lock_closed);
            }
            else{
                button.setBackgroundResource(R.drawable.lock_open);
            }
            button.setPadding(0, 0, 0, 0);     //keeps text from being clipped
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (houseAccount.getDoor(accessibleDoors[doorIndex]).isLocked()) {
                        houseAccount.unlockDoor(accessibleDoors[doorIndex]);
                        button.setBackgroundResource(R.drawable.lock_open);
                    }
                    else{
                        houseAccount.lockDoor(accessibleDoors[doorIndex]);
                        button.setBackgroundResource(R.drawable.lock_closed);
                    }

                }
            });
            tablerow.addView(button);
        }
}
}
