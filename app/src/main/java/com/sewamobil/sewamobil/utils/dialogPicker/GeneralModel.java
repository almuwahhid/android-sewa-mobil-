package com.sewamobil.sewamobil.utils.dialogPicker;

public class GeneralModel {
    String id, name, other;

    public GeneralModel(String id, String name, String other) {
        this.id = id;
        this.name = name;
        this.other = other;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOther() {
        return other;
    }
}
