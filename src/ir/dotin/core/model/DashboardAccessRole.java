package ir.dotin.core.model;


import ir.dotin.core.utils.BundleUtil;

public enum DashboardAccessRole {
    BRANCH, HEADQUARTER, BRANCH_HEADQUARTER;

    public String getDescription() {

        switch (this) {
            case BRANCH:
                return BundleUtil.getBundleValue("enum_branch");
            case HEADQUARTER:
                return BundleUtil.getBundleValue("enum_headquarter");
            case BRANCH_HEADQUARTER:
                return BundleUtil.getBundleValue("enum_branch_headquarter");
        }

        return BundleUtil.getBundleValue("enum_none");
    }

    public long getValue() {

        switch (this) {
            case BRANCH:
                return 1;
            case HEADQUARTER:
                return 2;
            case BRANCH_HEADQUARTER:
                return 3;
        }

        return 0;
    }
}
