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

            try {
            JSONObject baseJsonObject = new JSONObject(json);

            JSONObject nameJsonObject = baseJsonObject.getJSONObject("name");
            String mainName = nameJsonObject.getString("mainName");

            JSONArray alsoKnownAsArray = nameJsonObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAsList = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsArray.length(); i++){
                String otherName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(otherName);
            }

            String placeOfOrigin = nameJsonObject.getString("placeOfOrigin");
            String description = nameJsonObject.getString("description");
            String image = nameJsonObject.getString("image");

            JSONArray ingredientsArray = nameJsonObject.getJSONArray("ingredients");
            List<String> ingredientsList = new ArrayList<>();
            for (int a = 0; a < ingredientsArray.length(); a++){
                String otherIngredient = ingredientsArray.getString(a);
                ingredientsList.add(otherIngredient);
            }

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image,ingredientsList);
        }
        catch (JSONException e){
            Log.e("JsonUtils", "Eroare in Json", e);
        }

        return new Sandwich();
    }
}
