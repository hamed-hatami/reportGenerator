package ir.dotin.core.utils;


import ir.dotin.core.action.UserManagementAction;

public class BundleUtil {

    private static UserManagementAction me = (UserManagementAction) ManagedBeanManager.lookup(UserManagementAction.class);

    public static synchronized String getBundleValue(String bundleKey, String... arguments) {
        return me.getBundleMessage(bundleKey, arguments);
    }

}
