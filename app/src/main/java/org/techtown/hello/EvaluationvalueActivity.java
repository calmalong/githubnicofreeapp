package org.techtown.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Typeface;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

public class EvaluationvalueActivity extends Activity {

    Button btnGather;
    TextView textFinalScore, testresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.evaluation_result);

        // 뷰 요소 초기화
       textFinalScore = (TextView) findViewById(R.id.textFinalScore);
        testresult = (TextView) findViewById(R.id.testresult);
        btnGather = (Button) findViewById(R.id.btnGather);

        // 전달받은 점수 가져오기
        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("score");

        // 점수에 대한 서식 지정 및 텍스트뷰에 설정
        SpannableStringBuilder spannableText = new SpannableStringBuilder("당신의 니코틴 의존도는 " + score + "점 입니다.");

        int start = spannableText.toString().indexOf(String.valueOf(score));
        int end = start + String.valueOf(score).length();
        spannableText.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableText.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textFinalScore.setText(spannableText);

        // 점수에 따른 테스트 결과 설정
        if (score <=3 ){
            SpannableStringBuilder spannableText1 =  new SpannableStringBuilder("니코틴 의존도가 낮습니다. 금연 가능성이 큰 시기이므로 흡연에 대한 경각심을 가지는 것이 중요합니다. \n ‘나중에 끊어야지’ 혹은 ’얼마 안 피니까 괜찮아‘ 대신 지금부터 금연합시다!");
            setBoldSpan(spannableText1,"니코틴 의존도가 낮습니다");
            testresult.setText(spannableText1);
        } else if (score <=6 ){
            SpannableStringBuilder spannableText2 = new SpannableStringBuilder("니코틴 의존도가 중간입니다. 니코틴 중독으로 인한 증상이 나타나지 않았을 수 있으나, 불시에 심각한 의존도로 번질 수 있습니다. \n 금연 계획과 자기 조절을 통해 금연을 준비합시다!");
            setBoldSpan(spannableText2, "니코틴 의존도가 중간입니다");
            testresult.setText(spannableText2);
        } else {
            SpannableStringBuilder spannableText3 = new SpannableStringBuilder("니코틴 의존도가 높습니다. 현재 신체적, 심리적으로 의존이 생긴 상태이므로, 바로 금연한다면 초조하거나 조바심이 날 수 있습니다. " +
                    "이는 금단증상의 한 양상으로 금연을 지속하기 어렵게 만듭니다.\n 따라서, 단기 금연 계획과 흡연 기록 등을 적으면서 의존 수준을 떨어뜨리고 니코틴 패치나 약물 등 " +
                    "보조 수단을 적절히 사용하는 것을 권장합니다.");
            setBoldSpan(spannableText3, "니코틴 의존도가 높습니다");
            testresult.setText(spannableText3);
        }



        // 정보 수집 버튼 클릭시 UserinfoActivity로 이동
        btnGather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserinfoActivity.class);
                startActivity(intent);

            }
        });

    }
    // 텍스트에 스타일 적용
    private void setBoldSpan(SpannableStringBuilder spannableText, String targetText) {
        int start = spannableText.toString().indexOf(targetText);
        int end = start + targetText.length();
        spannableText.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
