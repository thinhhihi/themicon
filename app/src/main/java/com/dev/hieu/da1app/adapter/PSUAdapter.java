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
import com.dev.hieu.da1app.model.PSU;
import com.dev.hieu.da1app.sqlitedao.CartDAO;
import com.dev.hieu.da1app.sqlitedao.PSUDAO;

import java.util.List;

public class PSUAdapter extends RecyclerView.Adapter<PSUAdapter.NguonViewHolder> {


    private Context mCtx;
    private List<PSU> PSUS;
    private PSUDAO psudao;

    public PSUAdapter(Context mCtx, List<PSU> PSUS, PSUDAO psudao) {
        this.mCtx = mCtx;
        this.PSUS = PSUS;
        this.psudao = psudao;
    }

    @NonNull
    @Override
    public NguonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.nguon_cardview, null);
        return new NguonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguonViewHolder holder, final int i) {
        PSU product = PSUS.get(i);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                CartDAO cartDAO = new CartDAO(databaseHelper);

                Cart cart = new Cart(PSUS.get(i).getId(),PSUS.get(i).getTitle(),PSUS.get(i).getShortdesc(),PSUS.get(i).getRating(),PSUS.get(i).getPrice(),1);
                cartDAO.insertCart(cart);
                Toast.makeText(mCtx, "Added", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }

    @Override
    public int getItemCount() {
        return PSUS.size();
    }

    class NguonViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView,imgAddCart;

        public NguonViewHolder(@NonNull View itemView) {
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
