package com.example.cengonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cengonline.PostActivity;
import com.example.cengonline.R;

import com.example.cengonline.model.Course;
import com.example.cengonline.model.User;
import com.example.cengonline.pdf;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context mContext;
    private List<Course> courseList;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;

    public CourseAdapter(Context mContext, List<Course> courseList) {
        this.mContext = mContext;
        this.courseList = courseList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_content,parent,false);

        return new CourseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        final Course course = courseList.get(position);
        holder.courseName.setText(course.getCourseName());
        holder.coursePeriod.setText(course.getCoursePeriod());
        holder.courseTeacher.setText(course.getCourseTeacherName());
        holder.courseIdInvisible.setText(course.getCourseId());


//
        holder.pdfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(v.getContext(), pdf.class);
                i.putExtra("title",course.getCourseName());
                i.putExtra("product",course.getCourseName());
                i.putExtra("link",course.getLink());
                mContext.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView courseName, coursePeriod, courseTeacher, editCourse, createAssignment, courseIdInvisible ;
        private Button pdfButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseName = itemView.findViewById(R.id.content_courseName);
            coursePeriod = itemView.findViewById(R.id.content_coursePeriod);
            courseTeacher = itemView.findViewById(R.id.content_courseTeacher);
            editCourse = itemView.findViewById(R.id.content_editCourse);
            createAssignment = itemView.findViewById(R.id.content_createAssignment);
            courseIdInvisible = itemView.findViewById(R.id.content_courseId);
            pdfButton=itemView.findViewById(R.id.btnpdf);

        }
    }
}
