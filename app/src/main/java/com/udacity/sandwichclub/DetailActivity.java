package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView alsoKnownAsTv = (TextView) findViewById(R.id.also_known_tv);
        TextView descriptionTv = (TextView) findViewById(R.id.description_tv);
        TextView ingredientsTv = (TextView) findViewById(R.id.ingredients_tv);
        TextView originTv = (TextView) findViewById(R.id.origin_tv);



        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }



        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        List<String> lAlsoKnownAs = sandwich.getAlsoKnownAs();
        StringBuilder builderA = new StringBuilder();
        for (String sAlsoKnownAs : lAlsoKnownAs ){
            builderA.append(sAlsoKnownAs + " ");
        }
        alsoKnownAsTv.setText(builderA.toString());

        String sDescription = sandwich.getDescription();
        descriptionTv.setText(sDescription);

        String sPlaceOfOrigin = sandwich.getPlaceOfOrigin();
        originTv.setText(sPlaceOfOrigin);

        List<String> lIngredients = sandwich.getIngredients();
        StringBuilder builderB = new StringBuilder();
        for (String sIngredients : lIngredients ){
            builderB.append(sIngredients + "\n");
        }
        ingredientsTv.setText(builderB.toString());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
