package ir.dotin.core.model;

public enum EventType {

    CREATE, RETRIEVE, UPDATE, DELETE;


    public String getDescription() {

        switch (this) {
            case CREATE:
                return "CREATE";
            case RETRIEVE:
                return "RETRIEVE";
            case UPDATE:
                return "UPDATE";
            case DELETE:
                return "DELETE";
        }
        return "NONE";
    }

    public String getValue() {
        switch (this) {

            case CREATE:
                return "1";
            case RETRIEVE:
                return "2";
            case UPDATE:
                return "3";
            case DELETE:
                return "4";
        }
        return "0";
    }

}
