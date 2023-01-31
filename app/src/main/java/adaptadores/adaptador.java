package adaptadores;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica419100058.R;
import com.example.practica419100058.informacion;

import global.info;

public class adaptador extends RecyclerView.Adapter<adaptador.Miniactivity> {
    public Context context;
    @NonNull
    @Override
    public Miniactivity onCreateViewHolder(@NonNull ViewGroup viewGrup, int i) {
        View v=View.inflate(context,R.layout.vista,null);
        Miniactivity obj=new Miniactivity(v);
        return obj;
    }

    @Override
    public void onBindViewHolder(@NonNull Miniactivity miniactivity, int i) {
        final int pos=i;
        miniactivity.nombre.setText(info.lista.get(i).getApellidop()+" "+info.lista.get(i).getApellidom());
        miniactivity.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent see = new Intent(context, informacion.class);
                see.putExtra("pos",pos);
                context.startActivity(see);
            }
        });
    }

    @Override
    public int getItemCount() {

        return info.lista.size();
    }

    public class Miniactivity extends RecyclerView.ViewHolder {
        public TextView nombre;
        public Miniactivity(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.review);
        }
    }
}
