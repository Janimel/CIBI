package com.example.cibi;


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddTasks extends BottomSheetDialogFragment {

    public static final String TAG = "TaskBOTTOMdIALOG";

    private EditText title, desc;
    private Button add;
    private DatabaseHandler taskDB;

    public static AddTasks newInstance(){
        return new AddTasks();
    }

    @Override
    public void onCreate(Bundle savedInsatnceState) {
        super.onCreate(savedInsatnceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.addtasks, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        title = getView().findViewById(R.id.tasktitlein);
        desc = getView().findViewById(R.id.taskdescin);
        add = getView().findViewById(R.id.btnadd);

        taskDB = new DatabaseHandler(getActivity());
        taskDB.openDatabase();

        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("title");
            title.setText(task);
            if(task.length()>0){
                add.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
            }
        }

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals(" ")){
                    add.setEnabled(false);
                    add.setTextColor(Color.GRAY);
                } else {
                    add.setEnabled(true);
                    add.setTextColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final boolean finalIsUpdate = isUpdate;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tasktitle = title.getText().toString();
                String taskdesc = desc.getText().toString();
                if(finalIsUpdate){
                    taskDB.updateTask(bundle.getInt("id"), tasktitle, taskdesc);
                } else {
                    TaskItems task = new TaskItems();
                    task.setTasktitle(tasktitle);
                    task.setTaskdesc(taskdesc);
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
