package com.example.coffeehouse.apdapter;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coffeehouse.R;
import com.example.coffeehouse.model.Drink;
import com.example.coffeehouse.model.News;

import java.util.List;

public class RecyclerListDrink extends RecyclerView.Adapter<RecyclerListDrink.MyViewHolder>{
    private Context mContext;
    private List<Drink> mData;
    private int resource;
    public RecyclerListDrink(Context mContext, int resource, List<Drink> mData) {
        this.resource = resource;
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(resource,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
        holder.tvPrice.setText(Integer.toString(mData.get(position).getPrice()) + " đ");
        holder.imageView.setImageResource(mData.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvPrice;
        ImageView imageView;
        public MyViewHolder(final View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            imageView = itemView.findViewById(R.id.image);
            tvPrice = itemView.findViewById(R.id.tv_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,Integer.toString(getAdapterPosition()),Toast.LENGTH_SHORT).show();
                    openDialog();
                }
            });
        }
    }
    public void openDialog() {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detail);
        ImageView btnDecrease = dialog.findViewById(R.id.btn_decrease);
        ImageView btnIncrease = dialog.findViewById(R.id.btn_increase);
        TextView tvQuantity = dialog.findViewById(R.id.tv_quantity);
        TextView tvAmmount = dialog.findViewById(R.id.tv_ammount);
        TextView tvPrice = dialog.findViewById(R.id.tv_price);

        tvAmmount.setText(tvPrice.getText());
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(tvQuantity.getText().toString());
                int price = Integer.parseInt(tvPrice.getText().toString());
                tvQuantity.setText(Integer.toString(quantity+1));
                tvAmmount.setText(Integer.toString((quantity+1)*price));
            }
        });
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(tvQuantity.getText().toString());
                int price = Integer.parseInt(tvPrice.getText().toString());
                if(quantity > 1){
                    tvQuantity.setText(Integer.toString(quantity-1));
                    tvAmmount.setText(Integer.toString((quantity-1)*price));
                }
            }
        });
        dialog.show();
    }
}
