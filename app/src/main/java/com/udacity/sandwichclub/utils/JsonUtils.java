package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();


            try {
            JSONObject baseJsonObject = new JSONObject(json);


            JSONObject nameJsonObject = baseJsonObject.getJSONObject("name");
                String mainName = nameJsonObject.optString("mainName");

            JSONArray alsoKnownAsArray = nameJsonObject.optJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++){
                String otherName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(otherName);
            }

            String placeOfOrigin = baseJsonObject.optString("placeOfOrigin");
            String description = baseJsonObject.optString("description");
            String image = baseJsonObject.optString("image");

            JSONArray ingredientsArray = baseJsonObject.optJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int a = 0; a < ingredientsArray.length(); a++){
                String otherIngredient = ingredientsArray.getString(a);
                ingredientsList.add(otherIngredient);
            }

            sandwich = new Sandwich(mainName,alsoKnownAsList,placeOfOrigin,description,image,ingredientsList);
        }
        catch (JSONException e){
            Log.e("JsonUtils", "Error in Json", e);
        }

        return sandwich ;
    }
}
