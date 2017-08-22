package com.robustastudio.formssample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.robustastudio.forms.utils.FormItemUtil;
import com.robustastudio.forms.validation.FormValidator;
import com.robustastudio.forms.widget.FormItem;

public class MainActivity extends AppCompatActivity {

    @NotEmpty
    FormItem nameFormItem;
    @NotEmpty
    FormItem passwordFormItem;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameFormItem = (FormItem) findViewById(R.id.name_form_item);
        passwordFormItem = (FormItem) findViewById(R.id.password_form_item);

        validator = FormValidator.newInstance(this, new FormValidator.DefaultFormValidator() {
            @Override
            public void onValidationSucceeded() {
                Toast.makeText(MainActivity.this, "Validation Succeeded, " + nameFormItem.getContentAsString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormItemUtil.removeErrorHintIfFound(nameFormItem, passwordFormItem);
                validator.validate();
            }
        });
    }
}
