package teamblowfish.ihome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ListIterator;

public class Settings extends AppCompatActivity {

    private HouseAccount houseAccount;
    private String house;
    private String user;
    private EditText mUser;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Houses housesDB = new Houses();

        Intent loginIntent = getIntent();
        String[] userAndHouse = loginIntent.getStringArrayExtra("userAndHouse");
        house = userAndHouse[1];
        //Toast.makeText(getApplicationContext(),house,Toast.LENGTH_SHORT).show();
        houseAccount = housesDB.findHouseAccount(house);
        user = userAndHouse[0];

        table = (TableLayout) findViewById(R.id.tableForButtons);

        Button add = (Button) findViewById(R.id.add);

        mUser = (EditText) findViewById(R.id.newUserName);

        populateButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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

    public void add(View view){
        String user = mUser.getText().toString();
        houseAccount.add(user);

        populateButtons();
    }

    public void populateButtons(){
        //Only limited users will be shown.
        //There will not be a way to remove administrators through the app
        if(houseAccount.getNumUsers()>1){
            ListIterator<User> userIterator = houseAccount.getUsers();
            while(userIterator.hasNext()){
                final User returnedUser=userIterator.next();
                //the returnedUser is not the same one that is using the app and is also not an administrator
                if(!returnedUser.equals(houseAccount.getUser(user))&&returnedUser.getAccountType()!='a'){
                    TableRow row = new TableRow(this);
                    table.addView(row);
                    TextView name = new TextView(this);
                    name.setText(returnedUser.getName());

                    Button remove = new Button(this);
                    remove.setText("Remove");
                    remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            houseAccount.remove(returnedUser.getName());

                            populateButtons();
                        }
                    });
                }
            }
        }
    }
}
