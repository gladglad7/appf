// Generated by view binder compiler. Do not edit!
package com.example.financeapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.financeapp.R;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDetailedAnalysisBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final TextView averageExpenseValue;

  @NonNull
  public final RecyclerView detailedAnalysisList;

  @NonNull
  public final TextView highestAmountValue;

  @NonNull
  public final TextView highestCategoryValue;

  @NonNull
  public final TextView totalExpenseValue;

  private FragmentDetailedAnalysisBinding(@NonNull MaterialCardView rootView,
      @NonNull TextView averageExpenseValue, @NonNull RecyclerView detailedAnalysisList,
      @NonNull TextView highestAmountValue, @NonNull TextView highestCategoryValue,
      @NonNull TextView totalExpenseValue) {
    this.rootView = rootView;
    this.averageExpenseValue = averageExpenseValue;
    this.detailedAnalysisList = detailedAnalysisList;
    this.highestAmountValue = highestAmountValue;
    this.highestCategoryValue = highestCategoryValue;
    this.totalExpenseValue = totalExpenseValue;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDetailedAnalysisBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDetailedAnalysisBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_detailed_analysis, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDetailedAnalysisBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.averageExpenseValue;
      TextView averageExpenseValue = ViewBindings.findChildViewById(rootView, id);
      if (averageExpenseValue == null) {
        break missingId;
      }

      id = R.id.detailedAnalysisList;
      RecyclerView detailedAnalysisList = ViewBindings.findChildViewById(rootView, id);
      if (detailedAnalysisList == null) {
        break missingId;
      }

      id = R.id.highestAmountValue;
      TextView highestAmountValue = ViewBindings.findChildViewById(rootView, id);
      if (highestAmountValue == null) {
        break missingId;
      }

      id = R.id.highestCategoryValue;
      TextView highestCategoryValue = ViewBindings.findChildViewById(rootView, id);
      if (highestCategoryValue == null) {
        break missingId;
      }

      id = R.id.totalExpenseValue;
      TextView totalExpenseValue = ViewBindings.findChildViewById(rootView, id);
      if (totalExpenseValue == null) {
        break missingId;
      }

      return new FragmentDetailedAnalysisBinding((MaterialCardView) rootView, averageExpenseValue,
          detailedAnalysisList, highestAmountValue, highestCategoryValue, totalExpenseValue);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
