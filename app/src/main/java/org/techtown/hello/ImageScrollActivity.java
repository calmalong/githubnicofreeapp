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

        // ScrollView와 LinearLayout 생성
        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // 이미지 리소스 배열
        int[] imageResources = {
                R.drawable.main1,
                R.drawable.main2,
                R.drawable.main3,
                R.drawable.main4,
                R.drawable.main5
        };

        // 이미지 리소스를 순회하며 이미지 뷰 생성하고 LinearLayout에 추가
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
