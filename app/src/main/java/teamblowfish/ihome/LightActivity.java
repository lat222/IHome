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

public class LightActivity extends AppCompatActivity {

    private HouseAccount houseAccount;
    private String house;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        setTitle("Turn On/Off Lights");

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
        getMenuInflater().inflate(R.menu.menu_light, menu);
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
        final int[] accessibleRooms = houseAccount.getRoomAccess(user);
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);
        table.removeAllViewsInLayout();
        for(int i=0; i<accessibleRooms.length;i++) {
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            final Button button = new Button(this);
            final int roomIndex = i;
            button.setText(houseAccount.getRoom(accessibleRooms[roomIndex]).getName());
            //Toast.makeText(getApplicationContext(),accessibleDoors[i],Toast.LENGTH_SHORT).show();
            if(houseAccount.getRoom(accessibleRooms[roomIndex]).isLit()){
                button.setBackgroundResource(R.drawable.lit1);
            }
            else{
                button.setBackgroundResource(R.drawable.unlit);
            }
            button.setPadding(0, 0, 0, 0);     //keeps text from being clipped
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (houseAccount.getRoom(accessibleRooms[roomIndex]).isLit()) {
                        houseAccount.turnOffLight(accessibleRooms[roomIndex]);
                        button.setBackgroundResource(R.drawable.lit1);
                    }
                    else{
                        houseAccount.turnOnLight(accessibleRooms[roomIndex]);
                        button.setBackgroundResource(R.drawable.unlit);
                    }

                }
            });
            tablerow.addView(button);
        }
    }
}
