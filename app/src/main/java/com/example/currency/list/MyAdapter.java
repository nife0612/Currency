package com.example.currency.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.activity.ConvertActivity;
import com.example.currency.Modules;
import com.example.currency.R;
import com.example.currency.list.data_types.ValuteData;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Context context;
    private final List<ValuteData> data;


    public MyAdapter(Context context, List<ValuteData> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Double previous = new Double(data.get(position).getPrevious());
        Double value = new Double(data.get(position).getValue());
        Double nominal = new Double(data.get(position).getNominal());

        previous /= nominal;
        value /= nominal;


        holder.tv_char_code.setText(data.get(position).getCharCode());
        holder.tv_name.setText(data.get(position).getName());
        holder.tv_value.setText(Modules.myFormat(value));

        String s_tmp = Modules.calculateDifference(previous, value);
        Modules.textColoring(holder.tv_previous_value, s_tmp);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConvertActivity.class);
                intent.putExtra("char_code", holder.tv_char_code.getText());
                intent.putExtra("name", holder.tv_name.getText());
                intent.putExtra("value", holder.tv_value.getText());
                intent.putExtra("previous_value", holder.tv_previous_value.getText());
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_char_code;
        TextView tv_name;
        TextView tv_value;
        TextView tv_previous_value;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_char_code = itemView.findViewById(R.id.char_code);
            tv_name = itemView.findViewById(R.id.name);
            tv_value = itemView.findViewById(R.id.value);
            tv_previous_value = itemView.findViewById(R.id.previous_value);

        }
    }
}
