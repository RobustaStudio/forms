# Forms
- Thin wrapper above `TextInpulLayout` and `TextInpulEditText` from the support library, `Validator` from android-saripaar library

- This library aims to make buidling forms faster

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
- Use normal saripaar validations for the FormItem

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

## Features

### Defaults
- Hint animation is enabled
- supports RTL 
- if the `input_type` is set to password, password toggling is enabled


### Adding drawable 
           
```xml
app:drawable="@drawable/ic_email"
```

### Adding Vector drawable
*todo*

### Adding text as drawable
 
```xml
app:textAsDrawable="LE"
```

### Extra Validation annotations

- `@PhoneNumber` for validating valid phone number content
- `@LongValue` for validating valid long content
- `@EgyptNationalId` -> *todo*

### Adding max length
```xml
android:maxLength="10"
```
- This adds a counter view, and sets maximum characters number

### Create NonEditable field

- this is useful for creating uneditable fields that should execute action when clicked

```xml
<com.robustastudio.forms.widget.UnEditableFormItem
android:id="@+id/full_name_item"
android:layout_width="match_parent"
android:layout_height="wrap_content"
app:drawable="@drawable/ic_s"
android:hint="@string/choose_city" />
```

- set a listener in Java

```java
 unEditableFormItem.setListener(new UnEditableFormItem.OnFormItemClickedListener() {
            @Override
            public void onFormItemClicked(UnEditableFormItem unEditableFormItem) {
                // open dialog to choose city
            }
        });
```



