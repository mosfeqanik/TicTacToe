package Mosfeqanik01.tiktactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //0=zero1=cross 2=empty
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    boolean gameActive=true;

    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedCounter]==2 && gameActive) {
            gamestate[tappedCounter]=activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.zero);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(2000);
            for (int[] winningPosition : winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]]
                        &&
                        gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
                        &&
                        gamestate[winningPosition[0]] != 2) {
//                    int number1 = gamestate[winningPosition[0]];
//                    int number2 = gamestate[winningPosition[1]];
//                    int number3 = gamestate[winningPosition[2]];
//
//                    Toast.makeText(this,String.valueOf(number1) , Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this,String.valueOf(number2) , Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this,String.valueOf(number2) , Toast.LENGTH_SHORT).show();
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Zero";
                    } else {
                        winner = "Cross";
                    }

//                    Toast.makeText(this, winner + " has one", Toast.LENGTH_SHORT).show();

                    Button playAgainButton =(Button) findViewById(R.id.playAgainButton);
                    TextView winnertextView =(TextView) findViewById(R.id.winnerTextView);
                    winnertextView.setText(winner + " has won");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnertextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playAgain(View view){
        Button playAgainButton =(Button) findViewById(R.id.playAgainButton);
        TextView winnertextView =(TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnertextView.setVisibility(View.INVISIBLE);
        GridLayout gridLayout =(GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
            // do stuff with child view
        }
        for (int i=0 ; i<gamestate.length;i++){
            gamestate[i]=2;
        }
        activePlayer=0;
        gameActive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
