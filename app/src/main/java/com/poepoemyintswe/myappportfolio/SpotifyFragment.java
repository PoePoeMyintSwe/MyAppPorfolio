package com.poepoemyintswe.myappportfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.poepoemyintswe.myappportfolio.adapter.ArtistAdapter;
import java.util.concurrent.TimeUnit;
import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.OnTextChangeEvent;
import rx.android.widget.WidgetObservable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * A placeholder fragment containing a simple view.
 */
public class SpotifyFragment extends Fragment {

  @InjectView(R.id.artist_search) EditText mEditText;
  @InjectView(R.id.artist_list) RecyclerView mRecyclerView;
  private SpotifyService spotifyService;
  private ArtistAdapter artistAdapter;

  public SpotifyFragment() {
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    spotifyService = new SpotifyApi().getService();
    artistAdapter = new ArtistAdapter();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_spotify, container, false);
    ButterKnife.inject(this, view);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setAdapter(artistAdapter);
    rxEdit();

    return view;
  }

  private void rxEdit() {
    WidgetObservable.text(mEditText)
        .filter(new Func1<OnTextChangeEvent, Boolean>() {
          @Override public Boolean call(OnTextChangeEvent onTextChangeEvent) {
            return onTextChangeEvent.text().length() > 2;
          }
        })
        .throttleWithTimeout(200, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<OnTextChangeEvent>() {
          @Override public void call(OnTextChangeEvent onTextChangeEvent) {
            Log.d("edit text", onTextChangeEvent.text().toString());
            spotifyService.searchArtists(onTextChangeEvent.text().toString(),
                new Callback<ArtistsPager>() {
                  @Override
                  public void success(final ArtistsPager artistsPager, Response response) {
                    getActivity().runOnUiThread(new Runnable() {
                      @Override public void run() {
                        artistAdapter.setArtists(artistsPager.artists.items);
                      }
                    });
                  }

                  @Override public void failure(RetrofitError retrofitError) {

                  }
                });
          }
        });
  }
}
