package com.example.dnotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    FloatingActionButton fab;
    Adapter adapter;
    List<model> noteslist;
    databaseclass db;
   ConstraintLayout mainlayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview=findViewById(R.id.recyclerView);
        fab=findViewById(R.id.floatingActionButton);
     //  mainlayout1=findViewById(R.id.mainlayout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,addnotes.class);
                startActivity(intent);
            }
        });

        noteslist= new ArrayList<>();

        db=new databaseclass(this);
        fetchallnotefromdatabase();

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(this,MainActivity.this,noteslist);
        recyclerview.setAdapter(adapter);


      /*   ItemTouchHelper helper=new ItemTouchHelper(callback);
         helper.attachToRecyclerView(recyclerview);      */

    }

    void fetchallnotefromdatabase()
    {
       Cursor cursor=db.readdata();

       if(cursor.getCount()==0)
       {
           Toast.makeText(this,"no data available",Toast.LENGTH_SHORT).show();
       }
       else
       {
           while(cursor.moveToNext())
           {
               noteslist.add(new model(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
           }
       }
    }


  @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.options,menu);
   /*   MenuItem item=menu.findItem(R.id.searchbar);                                                   <item
                                                                                                           android:id="@+id/searchbar"
                                                                                                             android:title="searchbar"
                                                                                                             android:icon="@drawable/ic_baseline_search_24"
                                                                                                        app:actionViewClass="androidx.appcompat.widget.SearchView"
                                                                                                              app:showAsAction="always|collapseActionView" />
  SearchView searchView= (SearchView) item.getActionView();

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
              adapter.getFilter().filter(newText);
              return true;
          }
      });

 */
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.deleteall)
        {
        deleteallnote();
        }

        return super.onOptionsItemSelected(item);
    }

    private  void deleteallnote()
    {
    databaseclass db=new databaseclass(MainActivity.this);
    db.delete_allnotes_from_database();
    recreate();
    }

  /*  ItemTouchHelper.SimpleCallback callback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position=viewHolder.getAdapterPosition();
            model item=adapter.getList().get(position);

            adapter.removeitem(viewHolder.getAdapterPosition());

            Snackbar snackbar=Snackbar.make(mainlayout1,"deleted",Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.restoreitem(item,position);
                    recyclerview.scrollToPosition(position);
                }
            }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);

                    if(!(event==DISMISS_EVENT_ACTION))
                    {
                        databaseclass db=new databaseclass(MainActivity.this);
                        db.deletesingleitem(item.getId());
                    }
                }
            });

            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }; */

}