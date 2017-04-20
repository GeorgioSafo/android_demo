package demo.georgiosafo.com.androiddemo.presentation.view.adapters;

import android.content.Context;
import android.net.Uri;
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

/**
 * Created by gevorksafaryan on 16.05.17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mContext;
    private List<UserLocalData> userList;

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public SimpleDraweeView avatarImageView;

        public UserViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.title);
            avatarImageView = (SimpleDraweeView) view.findViewById(R.id.avatar_imageView);
        }
    }


    public UserAdapter(Context mContext) {
        this.mContext = mContext;
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
        holder.name.setText(String.format(Locale.getDefault(), mContext.getResources().getString(R.string.name_format), item.getFirstName(), item.getLastName()));

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

