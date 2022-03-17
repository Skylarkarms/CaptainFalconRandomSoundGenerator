package com.andradejuan.captainfalconrandomsoundgenerator;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    // TODO: Add member variables here
    private SoundPool falconSoundPool;

    Random randomNumberGenerator = new Random();

    int number1;
    int number2;

    private static int number3 ( int number1){

        if ( number1 >9){
            return 6;
        }else if(number1>8){
            return 4;
        }else if(number1>7) {
            return 2;
        }else {
            return 1;
        }
    }

    int randomDamageNum;
    int totalDamage = 0;


    static int onesDigits(int totalDamage){
        return totalDamage%10;
    }
    static int tensDigits(int totalDamage,int onesDigits){
        return (totalDamage%100 - onesDigits)/10;
    }
    static int hundredsDigits (int totalDamage,int onesDigits, int tensDigits){
        return (totalDamage%1000 - onesDigits - tensDigits)/100;
    }

    THEInterpolator interpolator = new THEInterpolator(0.75, 20);


    static void numberRecolor (int totalDamage,
                               final ImageView ones,
                               final ImageView tens,
                               final ImageView hundreds,
                               final ImageView percentage,
                               int color1,
                               int color2,
                               int color3,
                               int color4) {
        if (totalDamage > 120) {
            ones.setColorFilter(color4);
            tens.setColorFilter(color4);
            hundreds.setColorFilter(color4);
            percentage.setColorFilter(color4);
        } else if (totalDamage > 90) {
            ones.setColorFilter(color3);
            tens.setColorFilter(color3);
            hundreds.setColorFilter(color3);
            percentage.setColorFilter(color3);
        } else if (totalDamage > 60) {
            ones.setColorFilter(color2);
            tens.setColorFilter(color2);
            hundreds.setColorFilter(color2);
            percentage.setColorFilter(color2);
        } else if (totalDamage > 30) {
            ones.setColorFilter(color1);
            tens.setColorFilter(color1);
            hundreds.setColorFilter(color1);
            percentage.setColorFilter(color1);
        } else {
            ones.setColorFilter(null);
            tens.setColorFilter(null);
            hundreds.setColorFilter(null);
            percentage.setColorFilter(null);}
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        final int color1 = getResources().getColor(R.color.colorYellow);
        final int color2 = getResources().getColor(R.color.colorYellowDark);
        final int color3 = getResources().getColor(R.color.colorOrange);
        final int color4 = getResources().getColor(R.color.colorRed);

        final ImageView ones;
        final ImageView tens;
        final ImageView hundreds;
        final ImageView percentage;

        ones = findViewById(R.id.image_ones);
        tens = findViewById(R.id.image_tens);
        hundreds = findViewById(R.id.image_hundreds);
        percentage = findViewById(R.id.image_percentage);

        final int[] numberArray = {
                R.drawable.zero,
                R.drawable.one,
                R.drawable.two,
                R.drawable.three,
                R.drawable.four,
                R.drawable.five,
                R.drawable.six,
                R.drawable.seven,
                R.drawable.eight,
                R.drawable.nine
        };

        final Animation buttonAnim = AnimationUtils.loadAnimation(this,R.anim.bounce);
        final Animation dashAnim = AnimationUtils.loadAnimation(this,R.anim.dash_bounce);
        final Animation suddenDeathAnim = AnimationUtils.loadAnimation(this,R.anim.sudden_death);
        suddenDeathAnim.setInterpolator(interpolator);
        final Animation yesAnim = AnimationUtils.loadAnimation(this,R.anim.yes_anim);
        final Animation gameAnim = AnimationUtils.loadAnimation(this,R.anim.game_zoom);
        final Animation characterAnim = AnimationUtils.loadAnimation(this,R.anim.character);
        final Animation numberAnim = AnimationUtils.loadAnimation(this,R.anim.number_anim);
        numberAnim.setInterpolator(interpolator);

        final Button randomButton;
        randomButton = findViewById(R.id.RandomButton);

        final Button pinkButton;
        pinkButton = findViewById(R.id.PinkButton);

        final Button specialButton;
        specialButton = findViewById(R.id.Special);

        final Button meleeButton;
        meleeButton = findViewById(R.id.Melee);

        final Button dashButton;
        dashButton = findViewById(R.id.Dash);

        final Button yesButton;
        yesButton = findViewById(R.id.Yes);

        final Button showMeYourMovesButton;
        showMeYourMovesButton = findViewById(R.id.Character);

        final Button suddendeathButton;
        suddendeathButton = findViewById(R.id.SuddenDeath);

        final Button gameButton;
        gameButton = findViewById(R.id.Game);

        int NR_OF_SIMULTANEOUS_SOUNDS = 21;
        falconSoundPool = new SoundPool(NR_OF_SIMULTANEOUS_SOUNDS, AudioManager.STREAM_MUSIC,0);

        final int[] OtherIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.captain_falcon,1),
                falconSoundPool.load(getApplicationContext(),R.raw.game,1)
        };
        final int[] YeahIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.yeah,1)
        };

        final int[] YesIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.yes,1)
        };

        final int[] SpecialIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.falcon_kick,1),
                falconSoundPool.load(getApplicationContext(),R.raw.falcon_punch,1)
        };
        final int[] PinkButtonIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.tuh_1,1)
        };
        final int[] ShowMeYourMovesIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.show_me_your_moves,1)
        };
        final int[] DashIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.tuh,1)
        };

        final int [] AerialIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.huh,1)
        };

        final int [] BlueFalconIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.blue_falcon,1)
        };

        final int [] ComeOnIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.come_on,1)
        };

        final int[] MeleeIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.thuea,1),
                falconSoundPool.load(getApplicationContext(),R.raw.tuha,1),
                falconSoundPool.load(getApplicationContext(),R.raw.tuhaa,1),
                falconSoundPool.load(getApplicationContext(),R.raw.twaa,1)
        };
        final int[] SuperSuddenDeathIDs = {
                falconSoundPool.load(getApplicationContext(),R.raw.suffer,1),
                falconSoundPool.load(getApplicationContext(),R.raw.suffer_1,1),
                falconSoundPool.load(getApplicationContext(),R.raw.suffer_2,1),
                falconSoundPool.load(getApplicationContext(),R.raw.suffer_3,1),
                falconSoundPool.load(getApplicationContext(),R.raw.suffer_5,1),
                falconSoundPool.load(getApplicationContext(),R.raw.suffer_6,1)
        };
//         TODO: Load and get the IDs to identify the sounds

        final int [][] falconArray = new int[][]{
                ShowMeYourMovesIDs,
                AerialIDs,
                PinkButtonIDs,
                DashIDs,
                BlueFalconIDs,
                ComeOnIDs,
                YesIDs,
                YeahIDs,
                SpecialIDs,
                MeleeIDs,
                SuperSuddenDeathIDs
        };

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","Random Button has been pressed");

                randomButton.startAnimation(buttonAnim);

                number1 = randomNumberGenerator.nextInt(11);

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Random Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);


                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });

        pinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","Pink Button has been pressed");

                pinkButton.startAnimation(buttonAnim);

                final int [] chanceArray = new int[]{
                        2,2,2,2,2,2,2,2,2,  /*90%*/
                        10                  /*10%*/

                };

                number1 = chanceArray[randomNumberGenerator.nextInt(10)];

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Pink Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);

                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));
            }

        });

        specialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","Special Button has been pressed");

                specialButton.startAnimation(buttonAnim);

                final int [] chanceArray = new int[]{
                        8,8,8,8,8,8,8,8,8,
                        8,8,8,8,8,8,8,8,      /*85%*/
                        10,10,                /*10%*/
                        4,                    /*5%*/

                };

                number1 = chanceArray[randomNumberGenerator.nextInt(20)];

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Special Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);

                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });

        meleeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","Special Button has been pressed");

                meleeButton.startAnimation(buttonAnim);

                final int [] chanceArray = new int[]{
                        9,9,9,9,9,9,9,9,9,  /*90%*/
                        10                  /*10%*/

                };

                number1 = chanceArray[randomNumberGenerator.nextInt(10)];

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Melee Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);

                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });

        dashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","Dash Button has been pressed");

                dashButton.startAnimation(dashAnim);

                final int [] chanceArray = new int[]{
                        3,3,3,3,3,3,3,3,3,3,3,3,    /*60%*/
                        1,1,1,1,1,1,                /*30%*/
                        10,10,                      /*10%*/

                };

                number1 = chanceArray[randomNumberGenerator.nextInt(20)];

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Melee Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);

                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","Yes Button has been pressed");

                yesButton.startAnimation(yesAnim);

                final int [] chanceArray = new int[]{
                        6,6,6,6,6,6,6,6,6,
                        6,6,6,6,6,6,6,6,      /*85%*/
                        10,10,                /*10%*/
                        7,                    /*5%*/

                };

                number1 = chanceArray[randomNumberGenerator.nextInt(20)];

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Melee Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);

                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });

        showMeYourMovesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","showMeYourMoves Button has been pressed");

                showMeYourMovesButton.startAnimation(characterAnim);

                final int [] swagArray = new int[]{
                        0,0,0,0,0,0,0,0,0,
                        0,0,0,0,0,0,0,0,      /*85%*/
                        10,10,                /*10%*/
                        5,                    /*5%*/

                };

                number1 = swagArray[randomNumberGenerator.nextInt(20)];

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Melee Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);

                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });

        suddendeathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","SuddenDeath Button has been pressed");

                suddendeathButton.startAnimation(suddenDeathAnim);

                number1 = 10;

                number2 = randomNumberGenerator.nextInt(number3(number1));

                Log.d("CptFalconGen", "The numbers for the Melee Button are:" + number1 + " for number 1 and:" + number2 + " for number 2");

                falconSoundPool.play(falconArray[number1][number2],LEFT_VOLUME,RIGHT_VOLUME,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);


                if (number1 == 10){
                    randomDamageNum = randomNumberGenerator.nextInt(30);
                    ones.startAnimation(numberAnim);
                    tens.startAnimation(numberAnim);
                    hundreds.startAnimation(numberAnim);
                    percentage.startAnimation(numberAnim);
                }else {
                    randomDamageNum = 0;
                }

                Log.d("CptFalconGen", "The Random Damage is: " + randomDamageNum);

                totalDamage = totalDamage + randomDamageNum;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });

        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CptFalconGen","Game Button has been pressed");

                gameButton.startAnimation(gameAnim);

                falconSoundPool.play(OtherIDs[1],3.0f,3.0f,PRIORITY,NO_LOOP,NORMAL_PLAY_RATE);

                totalDamage = 0;

                Log.d("CptFalconGen", "Total Damage is: " + totalDamage);

                ones.setImageResource(numberArray[onesDigits(totalDamage)]);
                tens.setImageResource(numberArray[tensDigits(totalDamage, onesDigits(totalDamage))]);
                hundreds.setImageResource(numberArray[hundredsDigits(totalDamage, tensDigits(totalDamage, onesDigits(totalDamage)), tensDigits(totalDamage, onesDigits(totalDamage)))]);

                numberRecolor(totalDamage,ones,tens,hundreds,percentage,color1,color2,color3,color4);

                Log.d("CptFalconGen", "Ones digits are: " + onesDigits(totalDamage));
                Log.d("CptFalconGen", "Tens digits are: " + tensDigits(totalDamage,onesDigits(totalDamage)));
                Log.d("CptFalconGen", "Hundred digits are: " + hundredsDigits(totalDamage,onesDigits(totalDamage),tensDigits(totalDamage,onesDigits(totalDamage))));

            }

        });
    }

}
