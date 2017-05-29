/**
 * Copyright (c) 2017 Gevork Safaryan
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package demo.georgiosafo.com.socialnetworkexample.presentation.view.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.R;
import demo.georgiosafo.com.socialnetworkexample.SocialNetworkExampleApp;
import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.wrappers.DateWrapper;
import demo.georgiosafo.com.socialnetworkexample.presentation.view.interfaces.UserInfoView;

/**
 * Created by gevorksafaryan on 25.04.17.
 */

public class UserNewsAdapter extends RecyclerView.Adapter<UserNewsAdapter.UserNewsViewHolder> {

    private Context mContext;
    private UserInfoView view;
    private List<UserNewsLocalData> userList;
    private String imageUri;

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public class UserNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final CardView carView;
        public final TextView name, date, post;
        public final ImageView avatarImageView;

        public UserNewsViewHolder(View view) {
            super(view);
            carView = (CardView) view.findViewById(R.id.card_view);
            carView.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            post = (TextView) view.findViewById(R.id.postText);
            avatarImageView = (ImageView) view.findViewById(R.id.avatar_imageView);
        }

        @Override
        public void onClick(View v) {
            //todo make transition
        }
    }


    public UserNewsAdapter(Context mContext, UserInfoView view) {
        this.mContext = mContext;
        this.view = view;
        this.userList = new ArrayList<>();
    }

    public void setData(List<UserNewsLocalData> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public UserNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserNewsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserNewsViewHolder holder, int position) {
        UserNewsLocalData item = userList.get(position);
        holder.carView.setTag(position);
        holder.name.setText(item.getTitle());
        holder.date.setText(DateWrapper.longToString(item.getDate()));
        holder.post.setText(item.getPost());

        Glide.with(SocialNetworkExampleApp.getSocialNetworkExampleApp().getApplicationContext())
                .load(imageUri)
                .into(holder.avatarImageView);
    }

    @Override
    public long getItemId(int position) {
        return userList.get(position).hashCode();
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
