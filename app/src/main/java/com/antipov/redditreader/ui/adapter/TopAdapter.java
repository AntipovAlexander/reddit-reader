package com.antipov.redditreader.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antipov.redditreader.R;
import com.antipov.redditreader.data.pojo.Child;
import com.antipov.redditreader.data.pojo.Content;
import com.antipov.redditreader.data.pojo.Resolution;
import com.antipov.redditreader.utils.NumberFormatter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.antipov.redditreader.utils.common.Const.BASE_URL;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    private final Context context;
    private final List<Child> model;
    private final OnRecyclerItemClicked clickListener;
    private final int DESIRED_HEIGHT = 600;

    public TopAdapter(Context context, OnRecyclerItemClicked clickListener, List<Child> model) {
        this.model = model;
        this.clickListener = clickListener;
        this.context = context;
    }

    @NonNull
    @Override
    public TopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // filling recycler item
        Content post = model.get(i).getData();

        // text info
        viewHolder.subReddit.setText(post.getSubredditName());
        viewHolder.userName.setText(context.getString(R.string.username_placeholder,post.getAuthor()));
        viewHolder.title.setText(post.getTitle());
        viewHolder.score.setText(NumberFormatter.format(post.getScore()));
        viewHolder.commentsCount.setText(NumberFormatter.format(post.getNumComments()));

        // set date
        Date date = new Date(post.getCreatedUtc() * 1000);
        PrettyTime prettyTime = new PrettyTime(new Locale("en"));
        viewHolder.time.setText(prettyTime.format(date));

        // images
        List<Resolution> resolutions = post.getImagesFromPreview();

        // selecting image with desired height
        if (resolutions != null && resolutions.size() > 0){
            // to avoid hiding thumb when view will be recycled
            viewHolder.thumbnail.setVisibility(View.VISIBLE);
            // selecting resolution which close to DESIRED_HEIGHT
            Resolution desiredResolution = null;
            for (Resolution resolution: resolutions) {
                // if find desired
                if (resolution.getHeight() >= DESIRED_HEIGHT) desiredResolution = resolution;
            }

            // if not find - get source
            if (desiredResolution == null) {
                desiredResolution = post.getSource();
            }

            if (desiredResolution != null) {
                // fix wrap content and glide
                // https://github.com/bumptech/glide/issues/1591
                viewHolder.thumbnail.getLayoutParams().height = desiredResolution.getHeight();
                viewHolder.thumbnail.getLayoutParams().width = desiredResolution.getWidth();
                RequestManager glide = Glide.with(context);
                // unescaping url to avoid 404. Reddit returns weird escaped links to images.
                String unescapedUrl = Html.fromHtml(desiredResolution.getUrl()).toString();
                glide
                        .load(unescapedUrl)
                        .into(viewHolder.thumbnail);
            }
        } else {
            // gone thumb if don`t have an image
            viewHolder.thumbnail.setVisibility(View.GONE);
        }

        // click listener
        viewHolder.itemView.setOnClickListener(view ->
            clickListener.onRecyclerItemClicked(BASE_URL + post.getPermalink())
        );
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_subreddit) TextView subReddit;
        @BindView(R.id.tv_user) TextView userName;
        @BindView(R.id.tv_title) TextView title;
        @BindView(R.id.tv_time) TextView time;
        @BindView(R.id.tv_likes_count) TextView score;
        @BindView(R.id.tv_comments_count) TextView commentsCount;
        @BindView(R.id.iv_thumb) ImageView thumbnail;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnRecyclerItemClicked {
        void onRecyclerItemClicked(String url);
    }
}
