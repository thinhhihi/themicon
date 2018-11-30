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
import com.dev.hieu.da1app.model.SSD;
import com.dev.hieu.da1app.sqlitedao.CartDAO;
import com.dev.hieu.da1app.sqlitedao.SSDDAO;

import java.util.List;

public class SSDAdapter extends RecyclerView.Adapter<SSDAdapter.SsdViewHolder> {


    private Context mCtx;
    private List<SSD> SSDS;
    private SSDDAO SSDDAO;

    public SSDAdapter(Context mCtx, List<SSD> SSDS, SSDDAO SSDDAO) {
        this.mCtx = mCtx;
        this.SSDS = SSDS;
        this.SSDDAO = SSDDAO;
    }

    @NonNull
    @Override
    public SsdViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.ssd_cardview, null);
        return new SsdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SsdViewHolder holder, final int i) {
        SSD product = SSDS.get(i);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                CartDAO cartDAO = new CartDAO(databaseHelper);
                Cart cart = new Cart(SSDS.get(i).getId(),SSDS.get(i).getTitle(),SSDS.get(i).getShortdesc(),SSDS.get(i).getRating(),SSDS.get(i).getPrice(),1);
                cartDAO.insertCart(cart);
                Toast.makeText(mCtx, "Added", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }

    @Override
    public int getItemCount() {
        return SSDS.size();
    }

    class SsdViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView,imgAddCart;

        public SsdViewHolder(@NonNull View itemView) {
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
