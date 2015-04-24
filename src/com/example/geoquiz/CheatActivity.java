package com.example.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends Activity {
	
	private static String EXTRA_ANSWER_SHOWN1;
	private boolean mAnswerIsTrue ;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	public static final String EXTRA_ANSWER_IS_TRUE="com.example.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN="com.example.geoquiz.answer_shown";
	
	private void setAnswerShownResult(boolean isAnswerShown){
		
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN1, isAnswerShown);
		setResult(RESULT_OK,data);
		
	}
	
	
	
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		
		setAnswerShownResult(false);
		mShowAnswer.setOnClickListener(new View.OnClickListener(){
			
			public void onClick(View v){
				
				if(mAnswerIsTrue){
					
					mAnswerTextView.setText(R.string.true_button);
				}
				
				else{
					
					mAnswerTextView.setText(R.string.false_button);
					
				}
				
				setAnswerShownResult(false);
			}
			
		});
		
	}

}
