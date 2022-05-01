package com.example.dnotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myviewholder>/* implements Filterable */ {

    Context context;
    Activity activity;
    List<model> noteslist;

    //  List<model> backup;
//    List<model> temp;
//    List<model> filterdata;

    public Adapter(Context context, Activity activity, List<model> noteslist) {
        this.context = context;
        this.activity = activity;
        this.noteslist = noteslist;
      //  backup=new ArrayList<>(noteslist);
        //    temp=new ArrayList<>(backup);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.title.setText(noteslist.get(position).getTitle());
        holder.description.setText(noteslist.get(position).getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,updatenoteactivity.class);

                intent.putExtra("title",noteslist.get(position).getTitle());
                intent.putExtra("description",noteslist.get(position).getDescription());
                intent.putExtra("id",noteslist.get(position).getId());

                activity.startActivity(intent);
            }
        });


        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context)
                        .setTitle("Delete note")
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                databaseclass db=new databaseclass(context);
                                db.deletesingleitem(noteslist.get(position).getId());


                                    noteslist.remove(position);
                                notifyItemRemoved(position);

     /*                           for(model a:temp) {
                                    if (a.getId().toString() == noteslist.get(position).getId().toString())
                                        temp.remove(a);
                                }
                                backup=new ArrayList<>(temp);


      */
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();

                return true;

            }
        });
    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }



 /*   @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
             filterdata=new ArrayList<>();
            if(constraint.toString().isEmpty())
                filterdata.addAll(backup);
            else
            {
                for(model obj : backup)
                {
                    if(obj.getTitle().toString().toLowerCase().contains(constraint.toString().toLowerCase()))
                        filterdata.add(obj);
                }
            }

                FilterResults results=new FilterResults();
            results.values=filterdata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

         noteslist.clear();
         noteslist.addAll((List) results.values);
         notifyDataSetChanged();

        }
    };
*/

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView title,description;
        ConstraintLayout layout;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.textView1);
            description=itemView.findViewById(R.id.textView2);
            layout=itemView.findViewById(R.id.layout1);
        }
    }

  /* public List<model> getList()
    {
        return noteslist;
    }

    public void removeitem(int position)
    {
        noteslist.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreitem(model item,int position)
    {
        noteslist.add(position,item);
        notifyItemInserted(position);
    }
*/

}
