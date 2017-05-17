package demo.georgiosafo.com.androiddemo.presentation.view.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import demo.georgiosafo.com.androiddemo.R;
import demo.georgiosafo.com.androiddemo.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.UserInfoView;

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
        public final SimpleDraweeView avatarImageView;

        public UserNewsViewHolder(View view) {
            super(view);
            carView = (CardView) view.findViewById(R.id.card_view);
            carView.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            post = (TextView) view.findViewById(R.id.postText);
            avatarImageView = (SimpleDraweeView) view.findViewById(R.id.avatar_imageView);
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
        notifyItemRangeInserted(0,userList.size());
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
        holder.date.setText(item.getDate());
        holder.post.setText(item.getPost());

        GenericDraweeHierarchy hierarchy = holder.avatarImageView.getHierarchy();
        hierarchy.setPlaceholderImage(R.drawable.ic_profile, ScalingUtils.ScaleType.CENTER_INSIDE);
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

        ImageRequest requestBuilder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUri))
                .setProgressiveRenderingEnabled(true)
                .build();

        DraweeController contoller = Fresco.newDraweeControllerBuilder().setImageRequest(requestBuilder).build();

        holder.avatarImageView.setController(contoller);
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
