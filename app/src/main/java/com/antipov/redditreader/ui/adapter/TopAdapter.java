package com.antipov.redditreader.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
import com.bumptech.glide.RequestManager;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.antipov.redditreader.utils.common.Const.BASE_URL;
import static com.antipov.redditreader.utils.common.Const.PAGE_SIZE;

public class TopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<Child> model;
    private final TopAdapterListener clickListener;
    private final int DESIRED_HEIGHT = 600;
    private final RequestManager requestManager;
    private String after;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    public TopAdapter(Context context, TopAdapterListener clickListener, RequestManager requestManager, List<Child> model, String after) {
        this.model = model;
        this.clickListener = clickListener;
        this.context = context;
        this.after = after;
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewLayout) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view;
        switch (viewLayout) {
            case R.layout.recycler_item_loader:
                view = inflater.inflate(R.layout.recycler_item_loader, viewGroup, false);
                return new LoaderVH(view);
            case R.layout.recycler_item_post:
                view = inflater.inflate(R.layout.recycler_item_post, viewGroup, false);
                PostVH vh = new PostVH(view);
                // click listener
                view.setOnClickListener(v -> {
                            int pos = vh.getAdapterPosition();
                            if (pos != RecyclerView.NO_POSITION) {
                                clickListener.onRecyclerItemClicked(
                                        BASE_URL + model.get(pos).getData().getPermalink()
                                );
                            }
                        }
                );
                return vh;
            default:
                throw new RuntimeException("Passed wrong view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int i) {
        // not binding for Loader
        if (vh instanceof LoaderVH) return;

        PostVH viewHolder = (PostVH) vh;

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
                // unescaping url to avoid 404. Reddit returns weird escaped links to images.
                String unescapedUrl = Html.fromHtml(desiredResolution.getUrl()).toString();
                requestManager
                        .load(unescapedUrl)
                        .into(viewHolder.thumbnail);
            }
        } else {
            // gone thumb if don`t have an image
            viewHolder.thumbnail.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public int getItemViewType(int position) {
        // returning layout as view types
        if (position == model.size() - 1 && isLoading) {
            return R.layout.recycler_item_loader;
        } else {
            return R.layout.recycler_item_post;
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        // pagination listening
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (layoutManager != null) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    if (!isLoading && !isLastPage) {
                        // end list reached - call new page
                        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && totalItemCount >= PAGE_SIZE) {
                            isLoading = true;
                            model.add(new Child());
                            notifyItemInserted(model.size());
                            clickListener.onNextPageRequired(after);
                        }
                    }
                }
            }
        });
    }

    public void addItems(List<Child> model, String after, boolean isLastPage) {
        this.isLoading = false;
        this.isLastPage = isLastPage;
        this.after = after;
        this.model.remove(this.model.size() - 1);
        notifyItemRemoved(this.model.size() + 1);
        this.model.addAll(model);
        notifyItemRangeInserted(this.model.size() - model.size(), model.size());
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder vh) {
        if (vh instanceof PostVH) {
            PostVH holder = (PostVH) vh;
            // clearing thumb for improving performance
            requestManager.clear(holder.thumbnail);
            holder.thumbnail.setImageDrawable(null);
        }
        super.onViewRecycled(vh);
    }

    class PostVH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_subreddit) TextView subReddit;
        @BindView(R.id.tv_user) TextView userName;
        @BindView(R.id.tv_title) TextView title;
        @BindView(R.id.tv_time) TextView time;
        @BindView(R.id.tv_likes_count) TextView score;
        @BindView(R.id.tv_comments_count) TextView commentsCount;
        @BindView(R.id.iv_thumb) ImageView thumbnail;

        PostVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class LoaderVH extends RecyclerView.ViewHolder {
        public LoaderVH(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface TopAdapterListener {
        void onRecyclerItemClicked(String url);

        void onNextPageRequired(String after);
    }
}
