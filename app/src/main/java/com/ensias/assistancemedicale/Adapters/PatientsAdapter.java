package com.ensias.assistancemedicale.Adapters;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ensias.assistancemedicale.R;

import java.util.List;

import Model.Patient;
import Model.User;

/**
 * Created by freenemya on 5/1/18.
 */

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.ViewHolder> implements NumberPicker.OnValueChangeListener{
    private List<Patient> listItems;
    private Context context;
    private int pos;
    private List<Drawable> images;
    private ViewHolder viewHolder;
    public PatientsAdapter(List<Patient> listItems, List<Drawable> imageView, Context context) {
        this.listItems = listItems;
        this.context = context;
        this.images=imageView;
    }

    @NonNull
    @Override
    public PatientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient,parent,false);
        return new PatientsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Patient listItem = listItems.get(position);
        final Drawable listimage =images.get(position);

        holder.Nom.setText("Nom :" + listItem.getNom());
        holder.UserName.setText("Email :"+listItem.getUserName());
        holder.image.setImageDrawable(listimage);

    }

    /**
     * Showing popup menu when tapping on 3 dots
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_panier, popup.getMenu());
        popup.setOnMenuItemClickListener(new PanierAdapter.MyMenuItemClickListener());
        popup.show();
    }
    /**
     * Click listener for popup menu items

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(final MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.update_item:
                    //Toast.makeText(context, "Commander", Toast.LENGTH_SHORT).show();
                    final Dialog d = new Dialog(context);
                    d.setTitle("Quantité");
                    d.setContentView(R.layout.dialog);
                    Button b1 = (Button) d.findViewById(R.id.button1);
                    final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
                    np.setMaxValue(100);
                    np.setMinValue(1);
                    np.setWrapSelectorWheel(false);
                    np.setOnValueChangedListener(PanierAdapter.this);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int nb = np.getValue();
                            int id_cmd = listItems.get(pos).getId_cmd();


                            String param = "id_cmd="+id_cmd+"&qte="+nb;
                            HttpPostRequest postRequest = new HttpPostRequest(context,param);

                            Log.i("param: ", "" + param);
                            String url = Authentification.IP+"/ecommerce_android/client.php?action=updatePanier";
                            postRequest.execute(url);

                            Log.i("param: ", "" + url+param);
                            viewHolder.qte.setText("Qauntité "+nb);
                            d.dismiss();
                        }
                    });
                    d.show();

                    return true;

                case R.id.del_item:
                    int id_cmd = listItems.get(pos).getId_cmd();
                    String param = "id_cmd="+id_cmd;
                    Log.i("param: ", "" + param);

                    String url = Authentification.IP+"/ecommerce_android/client.php?action=deletePanier";
                    HttpPostRequest postRequest = new HttpPostRequest(context,param);
                    postRequest.execute(url);
                    Log.i("param: ", "" + url+param);

                    Intent self = new Intent(context,Panier.class);
                    context.startActivity(self);

                    SharedPreferences sharedpreferences = context.getSharedPreferences(Authentification.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    Panier.nbPanier--;
                    editor.putString(Authentification.NbPanier,Panier.nbPanier+"");
                    editor.commit();
                    return true;

                case R.id.cmd_item:
                    id_cmd = listItems.get(pos).getId_cmd();
                    param = "id_cmd="+id_cmd;
                    Log.i("param: ", "" + param);

                    url = Authentification.IP+"/ecommerce_android/client.php?action=validerItem";
                    postRequest = new HttpPostRequest(context,param);
                    postRequest.execute(url);
                    Log.i("param: ", "" + url+param);

                    self = new Intent(context,Panier.class);
                    context.startActivity(self);

                    sharedpreferences = context.getSharedPreferences(Authentification.MyPREFERENCES, Context.MODE_PRIVATE);
                    editor = sharedpreferences.edit();
                    Panier.nbPanier--;
                    editor.putString(Authentification.NbPanier,Panier.nbPanier+"");
                    editor.commit();
                    return true;

                case R.id.valider_item:
                    String id_client = context.getSharedPreferences(Authentification.MyPREFERENCES,Context.MODE_PRIVATE).getString(Authentification.Id,null);
                    param = "id_client="+id_client;
                    url = Authentification.IP+"/ecommerce_android/client.php?action=validerPanier";
                    postRequest = new HttpPostRequest(context,param);
                    postRequest.execute(url);

                    self = new Intent(context,Panier.class);
                    context.startActivity(self);

                    sharedpreferences = context.getSharedPreferences(Authentification.MyPREFERENCES, Context.MODE_PRIVATE);
                    editor = sharedpreferences.edit();
                    Panier.nbPanier=0;
                    editor.putString(Authentification.NbPanier,Panier.nbPanier+"");
                    editor.commit();

                    return true;
                default:
            }
            return false;
        }
    }

    */
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is", "" + newVal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Nom;
        public TextView UserName;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Nom = (TextView) itemView.findViewById(R.id.nom);
            UserName = (TextView)itemView.findViewById(R.id.username);
            image =(ImageView)itemView.findViewById(R.id.imagepatient);

        }

    }



}