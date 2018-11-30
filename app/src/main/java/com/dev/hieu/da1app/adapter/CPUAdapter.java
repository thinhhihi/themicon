package com.dev.hieu.da1app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.database.DatabaseHelper;
import com.dev.hieu.da1app.model.CPU;
import com.dev.hieu.da1app.model.Cart;
import com.dev.hieu.da1app.sqlitedao.CPUDAO;
import com.dev.hieu.da1app.sqlitedao.CartDAO;

import java.util.List;

public class CPUAdapter extends RecyclerView.Adapter<CPUAdapter.CpuViewHolder> {
    private Context mCtx;
    private List<CPU> cpus;
    private CPUDAO cpudao;

    public CPUAdapter(Context mCtx, List<CPU> cpus, CPUDAO cpudao) {
        this.mCtx = mCtx;
        this.cpus = cpus;
        this.cpudao = cpudao;
    }

    @NonNull
    @Override
    public CpuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cpu_cardview, null);
        return new CpuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CpuViewHolder holder, final int position) {
        CPU product = cpus.get(position);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                CartDAO cartDAO = new CartDAO(databaseHelper);

                Cart cart = new Cart(cpus.get(position).getId(),cpus.get(position).getTitle(),cpus.get(position).getShortdesc(),cpus.get(position).getRating(),cpus.get(position).getPrice(),1);
                cartDAO.insertCart(cart);
                Toast.makeText(mCtx, "Added", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }

    @Override
    public int getItemCount() {
        return cpus.size();
    }

    class CpuViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView,imgAddCart;

        public CpuViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            imgAddCart = itemView.findViewById(R.id.imgAddCart);
        }
    }
}
