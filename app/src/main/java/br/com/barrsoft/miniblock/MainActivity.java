package br.com.barrsoft.miniblock;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_PREFERENCES = "FILE_PREFERENCES_MODE_0";
    @BindView(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        recoverPreferences();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.snack_text), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                SharedPreferences sharedPreferences = getSharedPreferences(FILE_PREFERENCES,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String anotation = String.valueOf(editText.getText());
                editor.putString("anotation",anotation);
                editor.apply();
            }
        });
    }

    private void recoverPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(FILE_PREFERENCES,0);
        if (sharedPreferences.contains("anotation")){
            String recoveryText = sharedPreferences.getString("anotation","Null");
            if (recoveryText.length() == 0){
                editText.setText(getResources().getString(R.string.welcome));
            }else{
                editText.setText(recoveryText);
            }
        }
    }
}