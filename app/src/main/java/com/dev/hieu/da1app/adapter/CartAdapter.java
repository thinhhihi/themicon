package com.dev.hieu.da1app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.hieu.da1app.R;
import com.dev.hieu.da1app.model.Cart;
import com.dev.hieu.da1app.sqlitedao.CartDAO;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CpuViewHolder> {
    private Context mCtx;
    private List<Cart> carts;
    private CartDAO cartdao;

    public CartAdapter(Context mCtx, List<Cart> carts, CartDAO cartdao) {
        this.mCtx = mCtx;
        this.carts = carts;
        this.cartdao = cartdao;
    }

    @NonNull
    @Override
    public CpuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cardview_cart, null);
        return new CpuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CpuViewHolder holder, final int i) {
        Cart product = carts.get(i);


        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(String.valueOf(product.getRating()));
        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
        holder.tvSoLuong.setText("1");
        holder.btnDeleteCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartdao.deleteCart(carts.get(i).getId());
                carts.remove(i);
                notifyDataSetChanged();
            }
        });
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));



    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class CpuViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice,tvSoLuong;
        ImageView imageView,btnDeleteCart;

        public CpuViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            btnDeleteCart = itemView.findViewById(R.id.btnDeleteCart);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        }
    }
}
