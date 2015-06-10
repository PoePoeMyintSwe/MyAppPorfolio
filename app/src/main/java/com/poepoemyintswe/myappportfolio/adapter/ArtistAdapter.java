package com.poepoemyintswe.myappportfolio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.myappportfolio.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import kaaes.spotify.webapi.android.models.Artist;

/**
 * Created by poepoe on 10/6/15.
 */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {

  List<Artist> artists;

  public ArtistAdapter() {
    this.artists = new ArrayList<>();
    setHasStableIds(true);
  }

  public void setArtists(List<Artist> artists) {
    this.artists = artists;
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.row_artist, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    holder.name.setText(artists.get(position).name);
    Picasso.with(holder.itemView.getContext())
        .load(artists.get(position).images.get(0).url)
        .into(holder.image);
  }

  @Override public int getItemCount() {
    return artists.size();
  }

  @Override public long getItemId(int position) {
    return position;
  }

  public final static class ViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.artist_image) ImageView image;
    @InjectView(R.id.artist_name) TextView name;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
