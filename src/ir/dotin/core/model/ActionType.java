package ir.dotin.core.model;


import ir.dotin.core.utils.BundleUtil;

public enum ActionType {

    Add, Delete, Edit, Defaulted, Suspended;

    public String getDescription() {

        switch (this) {
            case Add:
                return BundleUtil.getBundleValue("enum_add");
            case Delete:
                return BundleUtil.getBundleValue("enum_delete");
            case Edit:
                return BundleUtil.getBundleValue("enum_edit");
            case Defaulted:
                return BundleUtil.getBundleValue("enum_defaulted_action");
            case Suspended:
                return BundleUtil.getBundleValue("enum_suspended_action");
        }

        return BundleUtil.getBundleValue("enum_none");
    }

    public String getValue() {

        switch (this) {
            case Add:
                return "1";
            case Delete:
                return "2";
            case Edit:
                return "3";
            case Defaulted:
                return "4";
            case Suspended:
                return "5";
        }

        return "0";
    }
}