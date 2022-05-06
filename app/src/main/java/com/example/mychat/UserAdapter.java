package com.example.mychat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter
        extends RecyclerView.Adapter <UserAdapter.UserViewHoledr> {

    private ArrayList<User> users;
    private OnUserClickListener listener;

    public interface OnUserClickListener{
        void onUserClick (int position);
    }

    public void setOnUserClickListener (OnUserClickListener listener){
        this.listener = listener;
    }

    public UserAdapter(ArrayList<User> users){
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHoledr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        UserViewHoledr viewHoledr = new UserViewHoledr(view, listener);
        return viewHoledr;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHoledr holder, int position) {
        User currentUser = users.get(position);
        holder.avatarImageView.setImageResource(currentUser.getAvatarMockUpResource());
        holder.userNameTextView.setText(currentUser.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHoledr extends RecyclerView.ViewHolder {

        public ImageView avatarImageView;
        public TextView userNameTextView;


        public UserViewHoledr(@NonNull View itemView, OnUserClickListener listener) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.avatarImageView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION);
                        listener.onUserClick(position);
                    }
                }
            });
        }
    }
}
