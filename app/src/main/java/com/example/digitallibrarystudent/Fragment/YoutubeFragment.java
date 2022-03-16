package com.example.digitallibrarystudent.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.provider.Browser;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.digitallibrarystudent.R;


public class YoutubeFragment extends Fragment {

    String link,title;
    View view;
    ImageView back;


    public YoutubeFragment(String link, String title) {
        this.link=link;
        this.title=title;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_youtube, container, false);


        back=view.findViewById(R.id.back_youtube_pdf);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        TextView textView=view.findViewById(R.id.youtube_title_name);
        textView.setText(title);

        WebView webView = view.findViewById(R.id.web_youtube);
//        webView.setWebViewClient(new WebViewClient());
//
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//
//
//        webView.loadUrl(link);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new FullScreenClient(getActivity()){
            @Override
            public void onHideCustomView()
            {
                hideFullScreen(webView);
            }
            @Override
            public Bitmap getDefaultVideoPoster()
            {
                return videoBitmap();
            }
            @Override
            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback)
            {
                showFullScreen(view,callback);
            }

        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);





        return  view;
    }

}