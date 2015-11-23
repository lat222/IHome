package teamblowfish.ihome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ihomeLogin extends AppCompatActivity {

    private EditText mUserView;
    private EditText mHouseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ihome_login);

        setTitle("iHome Login");

        // Set up the login form.
        mUserView = (EditText) findViewById(R.id.user);

        mHouseView = (EditText) findViewById(R.id.house);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ihome_login, menu);
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

    public void logIn(View view){
        // Store values at the time of the login attempt.
        String user = mUserView.getText().toString();
        String house = mHouseView.getText().toString();
        Houses housesDB = new Houses();
        HouseAccount houseAccount = housesDB.findHouseAccount(house);
        if(houseAccount!=null){
            User userAccount = houseAccount.getUser(user);
            if(userAccount!=null){
                //TODO go to main activity
                Intent main = new Intent(this,Choose.class);
                //main.putExtra("user", user);
                //main.putExtra("house", house);
                main.putExtra("userAndHouse", new String[] {user,house});
                startActivity(main);
            }
            else{
                String userDNE = String.format("The user %s does not exist!",user);
                Toast.makeText(getApplicationContext(), userDNE, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            String houseDNE = String.format("The house %s does not exist!",house);
            Toast.makeText(getApplicationContext(), houseDNE, Toast.LENGTH_SHORT).show();

        }
        /*Intent main = new Intent(this,Choose.class);
        main.putExtra("userAndHouse", new String[] {user,house});
        //main.putExtra("house", house);
        startActivity(main);*/
    }
}
