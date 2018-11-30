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
import com.dev.hieu.da1app.model.Cart;
import com.dev.hieu.da1app.model.GPU;
import com.dev.hieu.da1app.sqlitedao.CartDAO;
import com.dev.hieu.da1app.sqlitedao.GPUDAO;

import java.util.List;

public class GPUAdapter extends RecyclerView.Adapter<GPUAdapter.GpuViewHolder> {
    private Context mCtx;
    private List<GPU> gpus;
    private GPUDAO gpudao;

    public GPUAdapter(Context mCtx, List<GPU> gpus, GPUDAO gpudao) {
        this.mCtx = mCtx;
        this.gpus = gpus;
        this.gpudao = gpudao;
    }

    @NonNull
    @Override
    public GpuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.gpu_cardview, null);
        return new GpuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GpuViewHolder holder, final int position) {
        GPU product = gpus.get(position);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                CartDAO cartDAO = new CartDAO(databaseHelper);

                Cart cart = new Cart(gpus.get(position).getId(),gpus.get(position).getTitle(),gpus.get(position).getShortdesc(),gpus.get(position).getRating(),gpus.get(position).getPrice(),1);
                cartDAO.insertCart(cart);
                Toast.makeText(mCtx, "Added", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }

    @Override
    public int getItemCount() {
        return gpus.size();
    }

    class GpuViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView,imgAddCart;

        public GpuViewHolder(@NonNull View itemView) {
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
