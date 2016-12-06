package ir.dotin.core.model;


public enum WorkGroupType {

    HEADQUARTER, BRANCH, SYSTEMMANAGER, SUPERUSER;

    public String getDescription() {

        switch (this) {
            case HEADQUARTER:
                return "Headquarter";
            case BRANCH:
                return "Branch";
            case SYSTEMMANAGER:
                return "SystemManager";
            case SUPERUSER:
                return "SuperUser";
        }

        return "unknown";
    }

    public String getValue() {

        switch (this) {
            case HEADQUARTER:
                return "1";
            case BRANCH:
                return "2";
            case SYSTEMMANAGER:
                return "3";
            case SUPERUSER:
                return "4";
        }

        return "0";
    }
}
