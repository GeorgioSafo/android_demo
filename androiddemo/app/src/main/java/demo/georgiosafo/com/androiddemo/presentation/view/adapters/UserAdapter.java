package demo.georgiosafo.com.androiddemo.presentation.view.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.Pair;
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
import java.util.Locale;

import demo.georgiosafo.com.androiddemo.R;
import demo.georgiosafo.com.androiddemo.data.model.local.UserLocalData;
import demo.georgiosafo.com.androiddemo.presentation.view.interfaces.MainView;

/**
 * Created by gevorksafaryan on 22.04.17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mContext;
    private MainView view;
    private List<UserLocalData> userList;

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final CardView carView;
        public final TextView name, age;
        public final SimpleDraweeView avatarImageView;

        public UserViewHolder(View view) {
            super(view);
            carView = (CardView) view.findViewById(R.id.card_view);
            carView.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.title);
            age = (TextView) view.findViewById(R.id.age);
            avatarImageView = (SimpleDraweeView) view.findViewById(R.id.avatar_imageView);
        }

        @Override
        public void onClick(View v) {
            view.startTransition(Pair.create(avatarImageView, mContext.getResources().getString(R.string.transitionAvatar)), userList.get((Integer) v.getTag()));
        }
    }


    public UserAdapter(Context mContext, MainView view) {
        this.mContext = mContext;
        this.view = view;
        this.userList = new ArrayList<>();
    }

    public void setData(List<UserLocalData> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {
        UserLocalData item = userList.get(position);
        holder.carView.setTag(position);
        holder.name.setText(String.format(Locale.getDefault(), mContext.getResources().getString(R.string.name_format), item.getFirstName(), item.getLastName()));
        holder.age.setText(String.valueOf(item.getAge()));

        GenericDraweeHierarchy hierarchy = holder.avatarImageView.getHierarchy();
        hierarchy.setPlaceholderImage(R.drawable.ic_profile, ScalingUtils.ScaleType.CENTER_INSIDE);
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

        ImageRequest requestBuilder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(item.getAvatarUrl()))
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

