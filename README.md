# Forms
- Thin wrapper above `TextInpulLayout` and `TextInpulEditText` from the support library, `Validator` from android-saripaar library

## Usage

- Add `FormItem` to xml

```xml
<com.robustastudio.forms.widget.FormItem
android:id="@+id/email_form_item"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:hint="@string/price"
android:textColorHint="@color/gray"
app:inputType="email" />
``` 
```java
private Validator validator;

    @BindView(R.id.email_item)
    @Email
    @NotEmpty
    FormItem emailFormItem;
    
    @Override
    protected void onCreate(Bundle bundle) {
        validator = FormValidator.newInstance(this, new FormValidator.DefaultFormValidator() {
            @Override
            public void onValidationSucceeded() {
                // do stuff
            }
        });
    }
    
    @OnClick(R.id.submit_email)
    void OnSubmitClicked() {
        FormItemUtil.removeErrorHintIfFound(emailFormItem);
        validator.validate();
    }


```
