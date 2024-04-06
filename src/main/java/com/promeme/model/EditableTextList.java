package com.promeme.model;

import java.util.ArrayList;

public class EditableTextList extends ArrayList<EditableText> {
    public void setProperty(EditableTextList editableTextList){
        while(this.size() > 0){
            this.remove(this.size() - 1);
        }
        for(EditableText text : editableTextList){
            EditableText textToSetProperty = new EditableText();
            textToSetProperty.setProperty(text);
            this.add(textToSetProperty);
        }
    }
    public void transformSizeByScale(double scale){
        for(EditableText text : this){
            text.transformByScale(scale);
        }
    }
}
