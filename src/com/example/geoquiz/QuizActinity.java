package com.example.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActinity extends ActionBarActivity {

	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	//private ImageButton mPrevButton;
	private TextView mQuestionTextView;
	private Button mCheatButton;
	
	private static final String tag = "QuizActivity";
			private static final String KEY_INDEX = "index";
	
			protected void onAvtivityResult(int requestCode, int resultCode,Intent data){
				if (data==null){
					return;
					
				}
				
				mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
				
			}
			
	private TrueFalse[] mQuestionBank = new TrueFalse[]{
		
			new TrueFalse(R.string.question_oceans,true),
			new TrueFalse(R.string.question_mideast, false),
			new TrueFalse(R.string.question_americas, true),
			new TrueFalse(R.string.question_asia, true),
			
			
	};
	
	
	private void updateQuestion(){
		int question = mQuestionBank[mCurrntIndex].getQuestion();
		mQuestionTextView.setText(question);
		
	}
	
	private void checkAnswer(boolean userPressedTrue){
		
		boolean answerIsTrue = mQuestionBank[mCurrntIndex].isTrueQuestion();
		int messageResId = 0;
		if (mIsCheater) {
			messageResId = R.string.judgment_toast;
			} else {
		if (userPressedTrue == answerIsTrue) {
			messageResId = R.string.correct_toast;
			} else {
			messageResId = R.string.incorrect_toast;
			}
			Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
			}
		
	}
	
	private int mCurrntIndex = 0;
	private boolean  mIsCheater;
	 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(tag, "onCreate(Bundle) called");
		setContentView(R.layout.activity_quiz_actinity);
		
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrntIndex = (mCurrntIndex+1) % mQuestionBank.length;
				mIsCheater = false;
				updateQuestion();
				
			}
		});
		
		
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
	
		mTrueButton = (Button)findViewById(R.id.true_button);
		
																	//Set listener  for  True  button	
		mTrueButton.setOnClickListener(new View.OnClickListener(){
		
			public void onClick(View v){
				
			checkAnswer(true);
		}
		});
		
		//set listener for false button
		mFalseButton= (Button)findViewById(R.id.false_button);
		
		mFalseButton.setOnClickListener(new View.OnClickListener(){
		
			public void onClick(View v){
				checkAnswer(false);
				
			}
				
		});
		
		mNextButton = (Button)findViewById(R.id.next_button);
		mNextButton.setOnClickListener(new View.OnClickListener(){
			
				public void onClick(View v){
					mCurrntIndex = (mCurrntIndex+1) % mQuestionBank.length;
					updateQuestion();
					
				}
		
		});
		
		
		
		
		mCheatButton = (Button)findViewById(R.id.cheat_button);
		mCheatButton.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				
				Intent i = new Intent(QuizActinity.this, CheatActivity.class);
				boolean answerIsTrue = mQuestionBank[mCurrntIndex].isTrueQuestion();
				i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
				startActivityForResult(i, 0);
			}
			
			
		
		});
		
		
		updateQuestion();
		
		if(savedInstanceState !=null){
			mCurrntIndex = savedInstanceState.getInt(KEY_INDEX,0);
		}
		
	}
	
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		Log.i(tag, "onSaveInstanceState");
		savedInstanceState.putInt(KEY_INDEX, mCurrntIndex);
		
	}
	
	@Override
	public void onStart() {
	super.onStart();
	Log.d(tag, "onStart() called");
	}
	@Override
	public void onPause() {
	super.onPause();
	Log.d(tag, "onPause() called");
	}
	@Override
	public void onResume() {
	super.onResume();
	Log.d(tag, "onResume() called");
	}
	@Override
	public void onStop() {
	super.onStop();
	Log.d(tag, "onStop() called");
	}
	@Override
	public void onDestroy() {
	super.onDestroy();
	Log.d(tag, "onDestroy() called");
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz_actinity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
