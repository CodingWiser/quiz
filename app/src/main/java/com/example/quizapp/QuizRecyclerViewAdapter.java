package com.example.quizapp;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.TextView;
        import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizRecyclerViewAdapter extends
        RecyclerView.Adapter<QuizRecyclerViewAdapter.ViewHolder> {

    private List<QuizModel> quizList;
    private Context context;
    private OnIncrementListener mListener;

    private RadioGroup lastCheckedRadioGroup = null;

    public QuizRecyclerViewAdapter(OnIncrementListener mListener, List<QuizModel> quizList
            , Context ctx) {
        this.quizList = quizList;
        context = ctx;
        this.mListener = mListener;
    }
    public interface OnIncrementListener{
       void onNumberIncremented(int adapterPosition, int i);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);

        ViewHolder viewHolder =
                new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 int position) {
        QuizModel quizModel = quizList.get(position);
        holder.packageName.setText(quizModel.getName());

//
        for(int i = 0; i < holder.priceGroup.getChildCount(); i++){
            ((RadioButton) holder.priceGroup.getChildAt(i)).setId(i);

            ((RadioButton) holder.priceGroup.getChildAt(i)).setText(quizModel.getSublist().get(i));

        }
//        id=0;
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView packageName;
        public RadioGroup priceGroup;
        public RadioButton gr1,gr2,gr3,gr4;

        public ViewHolder(View view) {
            super(view);
            packageName = (TextView) view.findViewById(R.id.package_name);
            priceGroup = (RadioGroup) view.findViewById(R.id.price_grp);
            gr1 = (RadioButton) view.findViewById(R.id.gr1);
            gr2 = (RadioButton) view.findViewById(R.id.gr2);
            gr3 = (RadioButton) view.findViewById(R.id.gr3);
            gr4 = (RadioButton) view.findViewById(R.id.gr4);

            priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    //since only one package is allowed to be selected
                    //this logic clears previous selection
                    //it checks state of last radiogroup and
                    // clears it if it meets conditions
//                    if (lastCheckedRadioGroup != null
//                            && lastCheckedRadioGroup.getCheckedRadioButtonId()
//                            != radioGroup.getCheckedRadioButtonId()
//                            && lastCheckedRadioGroup.getCheckedRadioButtonId() != -1) {
////                        lastCheckedRadioGroup.clearCheck();
//
//
//
//                    }
//                    lastCheckedRadioGroup = radioGroup;
                    quizList.get(getAdapterPosition()).setSelectedposition(i);
                    Toast.makeText(QuizRecyclerViewAdapter.this.context,
                            "Radio button clicked " + getAdapterPosition()+ " "+radioGroup.getCheckedRadioButtonId(),
                            Toast.LENGTH_SHORT).show();
                    mListener.onNumberIncremented(getAdapterPosition(),radioGroup.getCheckedRadioButtonId());

                }
            });
        }
    }

}
