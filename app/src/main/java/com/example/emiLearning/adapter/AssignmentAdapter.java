package com.example.emiLearning.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emiLearning.R;

import com.example.emiLearning.model.Assignment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private Context mContext;
    private List<Assignment> assignmentList;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private String myAnswer;
    String showAnswer;
    public AssignmentAdapter(Context mContext, List<Assignment> assignmentList) {
        this.mContext = mContext;
        this.assignmentList = assignmentList;
    }


    @NonNull
    @Override
    public AssignmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.assignment_content,parent,false);

        return new AssignmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        final Assignment assignment = assignmentList.get(position);
        holder.courseName.setText(assignment.getCourseName());
        holder.infoText.setText(assignment.getAssignmentInfoText());
        holder.courseId.setText(assignment.getCourseId());

        holder.writeAnswerAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Your answer");
                final EditText answerInput = new EditText(mContext);
                answerInput.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(answerInput);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myAnswer=answerInput.getText().toString();
                        DatabaseReference uploadRef = FirebaseDatabase.getInstance().getReference("UploadAssignments");
                        Map<String, Object> hashMap = new HashMap<>();

                        hashMap.put("courseId", assignment.getCourseId());
                        hashMap.put("assignmentId", assignment.getAssignmentId());
                        hashMap.put("assignmentInfoText", assignment.getAssignmentInfoText());
                        hashMap.put("uploadAssignment", myAnswer);
                        hashMap.put("studentId", currentUser.getUid());

                        uploadRef.child(currentUser.getUid()).child(assignment.getAssignmentId()).setValue(hashMap);
                        Toast.makeText(mContext, "Your answer has been submitted.", Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                    }
                });
                builder.show();
            }
        });





    }


    @Override
    public int getItemCount() {
        return assignmentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView courseName, infoText, date, courseId;
        private Button writeAnswerAssignment;
        private Button showAssignment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.content_assignment_courseName);
            infoText = itemView.findViewById(R.id.content_assignment_infoText);
            date = itemView.findViewById(R.id.content_assignment_date);
            courseId = itemView.findViewById(R.id.content_assignment_courseId);
            writeAnswerAssignment = itemView.findViewById(R.id.assignment_write_answer_button);

        }
    }
}
