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

public class MainActivity extends AppCompatActivity {

    private HouseAccount houseAccount;
    private String house;
    private String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Houses housesDB = new Houses();

        Intent loginIntent = getIntent();
        house = loginIntent.getStringExtra("house");
        houseAccount = housesDB.findHouseAccount(house);
        user = loginIntent.getStringExtra("user");

        populateButtons();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        table.removeAllViewsInLayout();
        if(userAccount.getNumRooms()!=0){
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            Button button=new Button(this);
            button.setText("Rooms");
        }
        if(userAccount.getNumDoors()!=0) {
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            Button button = new Button(this);
            button.setText("Doors");
            button.setPadding(0, 0, 0, 0);     //keeps text from being clipped
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent toDoors = new Intent(this, DoorActivity.class);
                    toDoors.putExtra("house", house);
                    toDoors.putExtra("user", user);
                    startActivity(toDoors);
                }
            });
        }
            if(userAccount.isAccessibleTemp()){
            TableRow tablerow = new TableRow(this);
            table.addView(tablerow);
            Button button=new Button(this);
            button.setText("Temperature");
        }
    }
}