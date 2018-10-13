package app.dev.app.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.dev.app.R;

public class ImageDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTV,descTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        titleTV = (TextView) findViewById(R.id.text_title);
        descTV = (TextView) findViewById(R.id.text_desc);
        imageView=(ImageView)findViewById(R.id.img_iv);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            String title = bundle.getString("title");
            String image = bundle.getString("image");
            String desc = bundle.getString("desc");
            titleTV.setText(title);
            descTV.setText(desc);
            setImage(imageView, image);
        }
    }

    public static void setImage(ImageView imageView, String url) {
        if (url != null && url.trim().length() > 0) {
            Picasso.with(imageView.getContext()).load(url).error(R.mipmap.ic_launcher).into(imageView);
        } else
            imageView.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
