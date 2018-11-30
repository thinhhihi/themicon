package com.dev.hieu.da1app.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.activity.NamePCActivity;
import com.dev.hieu.da1app.activity.NavigationActivity;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.Cart;
import com.dev.hieu.da1app.model.PC;
import com.dev.hieu.da1app.sqlitedao.CartDAO;
import com.dev.hieu.da1app.sqlitedao.PCDAO;

import java.util.ArrayList;
import java.util.List;

public class PCAdapter extends RecyclerView.Adapter<PCAdapter.CpuViewHolder> {
    private Context mCtx;
    private ArrayList<PC> pcs;
    private PCDAO pcdao;

    public PCAdapter(Context mCtx, ArrayList<PC> pcs, PCDAO pcdao) {
        this.mCtx = mCtx;
        this.pcs = pcs;
        this.pcdao = pcdao;
    }

    @NonNull
    @Override
    public CpuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.pc_cardview, null);
        return new CpuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CpuViewHolder holder, final int position) {
        final PC product = pcs.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(mCtx);
                dialog.setTitle(pcs.get(position).getTitle());
                dialog.setContentView(R.layout.activity_name_pc);
                TextView textView1,textView2,textView3;
                ImageView imageView;
                Button button;
                textView1 = dialog.findViewById(R.id.textViewTitle);
                textView2 = dialog.findViewById(R.id.textViewShortDesc);
                textView3 = dialog.findViewById(R.id.textViewPrice);
                imageView = dialog.findViewById(R.id.imglogoclick);
                button = dialog.findViewById(R.id.btnThem);
                textView1.setText(product.getTitle());
                textView2.setText(product.getShortdesc());
                imageView.setImageResource(Integer.parseInt(""+product.getImage()));
                textView3.setText(product.getPrice()+"");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                        CartDAO cartDAO = new CartDAO(databaseHelper);

                        Cart cart = new Cart(pcs.get(position).getId()+Math.random(),pcs.get(position).getTitle(),pcs.get(position).getShortdesc(),pcs.get(position).getRating(),pcs.get(position).getPrice(),1);
                        cartDAO.insertCart(cart);
                        Toast.makeText(mCtx, "Added", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        holder.textViewTitle.setText(product.getTitle());
        holder.imglogo.setImageResource(Integer.parseInt(""+product.getImage()));
//        holder.textViewShortDesc.setText(product.getShortdesc());
//        holder.textViewRating.setText(String.valueOf(product.getRating()));
//        holder.imageView.setImageResource(product.setImage(R.drawable.logo););
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));

        holder.addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                CartDAO cartDAO = new CartDAO(databaseHelper);

                Cart cart = new Cart(pcs.get(position).getId()+Math.random(),pcs.get(position).getTitle(),pcs.get(position).getShortdesc(),pcs.get(position).getRating(),pcs.get(position).getPrice(),1);
                cartDAO.insertCart(cart);
            }
        });
//        holder.imageView.setImageResource(product.getImage());








    }

    @Override
    public int getItemCount() {
        return pcs.size();
    }

    class CpuViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imglogo;
        Button addCart;

        public CpuViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imglogo = itemView.findViewById(R.id.imgtextsp);
            addCart = itemView.findViewById(R.id.btnAddCart);
        }
    }
    public void setFilter(List<PC> newList){
        pcs = new ArrayList<>();
        pcs.addAll(newList);
        notifyDataSetChanged();
    }
}
