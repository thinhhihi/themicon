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
import com.dev.hieu.da1app.model.Main;
import com.dev.hieu.da1app.sqlitedao.CartDAO;
import com.dev.hieu.da1app.sqlitedao.MainDAO;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private Context mCtx;
    private List<Main> mains;
    private MainDAO mainDAO;

    public MainAdapter(Context mCtx, List<Main> mains, MainDAO mainDAO) {
        this.mCtx = mCtx;
        this.mains = mains;
        this.mainDAO = mainDAO;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.main_cardview, null);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, final int i) {
        Main product = mains.get(i);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                CartDAO cartDAO = new CartDAO(databaseHelper);

                Cart cart = new Cart(mains.get(i).getId(),mains.get(i).getTitle(),mains.get(i).getShortdesc(),mains.get(i).getRating(),mains.get(i).getPrice(),1);
                cartDAO.insertCart(cart);
                Toast.makeText(mCtx, "Added", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }

    @Override
    public int getItemCount() {
        return mains.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView,imgAddCart;

        public MainViewHolder(@NonNull View itemView) {
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
