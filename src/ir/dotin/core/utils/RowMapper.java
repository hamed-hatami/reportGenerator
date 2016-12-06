package ir.dotin.core.utils;

import ir.dotin.core.model.entity.generic.User;

import java.util.ArrayList;
import java.util.List;


public class RowMapper {


    public List<User> convertToListOfUser(ArrayList<Object[]> userList) {
        List<User> finalList = new ArrayList<>();
        for (Object[] obj : userList) {
            User user = new User();
            user.setId((Long) obj[0]);
            user.setUsername((String) obj[1]);
            user.setIsActive((Boolean) obj[2]);

//            user.setWorkgroup(new WorkGroup());
//            user.getWorkgroup().setPersianDescription((String) obj[3]);
//            user.getWorkgroup().setEnglishDescription((String) obj[4]);

            finalList.add(user);
        }
        return finalList;
    }

}