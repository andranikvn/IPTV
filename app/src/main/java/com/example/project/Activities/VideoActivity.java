package com.example.project.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.project.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.MediaDrmCallback;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Util;

import java.io.IOException;
import java.net.URI;

public class VideoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);




        String baseUrl, username, password, acton, id;

        baseUrl = getResources().getString(R.string.baseUrl);
        username = "bravo";
        password = "bravo";


        String url;

        Context context = VideoActivity.this;
        Bundle bundle = getIntent().getExtras();

        id = bundle.getString("id");
        url = baseUrl + "live" + "/" + username + "/" + password + "/" + id + ".ts";
        System.out.println(url);
        //url = "https://file-examples.com/wp-content/uploads/2017/04/file_example_MP4_1920_18MG.mp4";
        Uri uri = Uri.parse(url);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "Project"), null);
        ;

        PlayerView playerView = findViewById(R.id.exoplayerview);
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();//.setTsExtractorMode(TsExtractor.MODE_HLS);



        MediaSource videoSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory,
                new Handler(Looper.getMainLooper()), null);

//                new HlsMediaSource.Factory(dataSourceFactory)
//                .setAllowChunklessPreparation(true)
//                .createMediaSource(uri,
//                        new Handler(Looper.getMainLooper()),
//                        mediaSourceEventListener);
                //new ExtractorMediaSource(uri, dataSourceFactory, extractorsFactory, null, null);


        SimpleExoPlayer player = new SimpleExoPlayer.Builder(context).build();

        playerView.setPlayer(player);

        player.release();

        player.prepare(videoSource);








    }


}
