package com.example.currency.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currency.R;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final Context context;
    private final List<ValuteData> data;

    private String calculateDifference(String previous, String value){
        double val = Double.parseDouble(value) - Double.parseDouble(previous);
        return myFormat(Double.toString(val));
    }

    @SuppressLint("DefaultLocale")
    private String myFormat(String val){
        Double d_val = Double.parseDouble(val);
        return String.format("%.2f", d_val);
    }

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
        



        holder.tv_char_code.setText(data.get(position).getCharCode());
        holder.tv_name.setText(data.get(position).getName());
        holder.tv_value.setText(myFormat(data.get(position).getValue()));

        String s_tmp = calculateDifference(data.get(position).getPrevious(), data.get(position).getValue());
        double d_tmp = Double.parseDouble(s_tmp);


        holder.tv_previous_value.setText(s_tmp);
        if(d_tmp > 0.0){

            holder.tv_previous_value.setTextColor(Color.GREEN);
        }
        else if(d_tmp < 0.0){
            holder.tv_previous_value.setTextColor(Color.RED);
        }

        holder.itemView.setOnClickListener(view -> Toast.makeText(context, data.get(position).getValue(), Toast.LENGTH_SHORT).show());
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
