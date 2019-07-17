package org.amahi.anywhere.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.amahi.anywhere.R;
import org.amahi.anywhere.server.model.FriendRequest;

import java.util.List;

public class FriendRequestsListAdapter extends RecyclerView.Adapter<FriendRequestsListAdapter.FriendRequestsListViewHolder> {
    private List<FriendRequest> friendRequestsList;
    private Context context;

    public FriendRequestsListAdapter(Context context, List<FriendRequest> friendRequestsList) {
        this.context = context;
        this.friendRequestsList = friendRequestsList;

    }

    @NonNull
    @Override
    public FriendRequestsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendRequestsListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_requests_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestsListViewHolder holder, int position) {
        holder.friendEmailText.setText(friendRequestsList.get(position).getFriendUser().getEmail());
        int status = friendRequestsList.get(position).getStatus();

        //set status text and color based on status value
        String statusText = "Active";
        int colorResourceId = R.color.status_active;
        switch (status) {
            case 0:
                statusText = "Active";
                colorResourceId = R.color.status_active;
                break;
            case 1:
                statusText = "Expired";
                colorResourceId = R.color.status_expired;
                break;
            case 2:
                statusText = "Accepted";
                colorResourceId = R.color.status_accepted;
                break;
            case 3:
                statusText = "Rejected";
                colorResourceId = R.color.status_rejected;
                break;

        }
        holder.friendRequestStatus.setText(statusText);
        GradientDrawable drawable = (GradientDrawable) holder.friendRequestStatus.getBackground();
        drawable.setColor(context.getResources().getColor(colorResourceId));
        holder.friendRequestStatus.setBackground(drawable);

    }

    @Override
    public int getItemCount() {
        return friendRequestsList.size();
    }

    class FriendRequestsListViewHolder extends RecyclerView.ViewHolder {
        TextView friendEmailText;
        TextView friendRequestStatus;

        FriendRequestsListViewHolder(View itemView) {
            super(itemView);
            friendEmailText = itemView.findViewById(R.id.text_friend_request_email);
            friendRequestStatus = itemView.findViewById(R.id.text_friend_request_status);

        }
    }
}


