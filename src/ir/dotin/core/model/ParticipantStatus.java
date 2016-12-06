package ir.dotin.core.model;


import ir.dotin.core.utils.BundleUtil;

public enum ParticipantStatus {

    ACTIVE, DEFAULT, SUSPEND;

    public String getDescription() {

        switch (this) {
            case ACTIVE:
                return BundleUtil.getBundleValue("enum_active");
            case DEFAULT:
                return BundleUtil.getBundleValue("enum_Default");
            case SUSPEND:
                return BundleUtil.getBundleValue("enum_suspend");
        }

        return BundleUtil.getBundleValue("enum_none");
    }

    public String getValue() {

        switch (this) {
            case ACTIVE:
                return "1";
            case DEFAULT:
                return "2";
            case SUSPEND:
                return "3";
        }

        return "0";
    }
}
