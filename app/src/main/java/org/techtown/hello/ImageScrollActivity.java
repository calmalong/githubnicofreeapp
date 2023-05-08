package org.techtown.hello;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ImageScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        int[] imageResources = {
                R.drawable.main1,
                R.drawable.main2,
                R.drawable.main3,
                R.drawable.main4,
                R.drawable.main5
        };

        for (int imageResource : imageResources) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageResource);
            imageView.setAdjustViewBounds(true);
            linearLayout.addView(imageView);
        }

        scrollView.addView(linearLayout);
        setContentView(scrollView);
    }
}
