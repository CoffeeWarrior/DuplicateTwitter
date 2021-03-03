package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    Context context;
    List<Tweet> tweets;
    //pass in the context and the list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets){
        this.context = context;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //for each row inflate the layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind the data to the view -- populate the tweet with the information relevant to the tweet
        Tweet tweet = tweets.get(position);
        //bind the tweet with the view holder

        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void clear(){
        //clear elements of the recycler
        tweets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Tweet> tweetList){
        //add a list of items
        tweets.addAll(tweetList);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvScreenName, tvBody, tvHandle;

        public ViewHolder(@NonNull View itemView) { //itemView is a representation of one view (row). In this case it would be our single tweet, item_tweet
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvScreenName =  itemView.findViewById(R.id.tvScreenName);
            tvBody =  itemView.findViewById(R.id.tvBody);
            tvHandle =  itemView.findViewById(R.id.tvHandle);
        }

        public void bind(Tweet tweet) {
            tvHandle.setText('@' + tweet.user.screenName);
            tvScreenName.setText(tweet.user.name);
            tvBody.setText(tweet.body);
            Glide.with(context).load(tweet.user.profileImageUrl).into(ivProfileImage);
        }
    }

}
