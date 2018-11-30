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
import com.dev.hieu.da1app.model.RAM;
import com.dev.hieu.da1app.sqlitedao.CartDAO;
import com.dev.hieu.da1app.sqlitedao.RAMDAO;

import java.util.List;

public class RAMAdapter extends RecyclerView.Adapter<RAMAdapter.RamViewHolder> {
    private Context mCtx;
    private List<RAM> RAMS;
    private RAMDAO ramdao;

    public RAMAdapter(Context mCtx, List<RAM> RAMS, RAMDAO ramdao) {
        this.mCtx = mCtx;
        this.RAMS = RAMS;
        this.ramdao = ramdao;
    }

    @NonNull
    @Override
    public RamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.ram_cardview, null);
        return new RamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RamViewHolder holder, final int i) {
        RAM product = RAMS.get(i);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.imgAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(mCtx);
                CartDAO cartDAO = new CartDAO(databaseHelper);

                Cart cart = new Cart(RAMS.get(i).getId(),RAMS.get(i).getTitle(),RAMS.get(i).getShortdesc(),RAMS.get(i).getRating(),RAMS.get(i).getPrice(),1);
                cartDAO.insertCart(cart);
                Toast.makeText(mCtx, "Added", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }

    @Override
    public int getItemCount() {
        return RAMS.size();
    }

    class RamViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView,imgAddCart;

        public RamViewHolder(@NonNull View itemView) {
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
