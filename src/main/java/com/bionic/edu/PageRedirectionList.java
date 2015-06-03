package com.bionic.edu;

import java.util.HashMap;
import java.util.Map;

import com.bionic.edu.entities.StaffRoles;

public class PageRedirectionList {
public static Map<StaffRoles, String> pagesByRoles;
static{
	pagesByRoles=new HashMap<StaffRoles, String>();
	pagesByRoles.put(StaffRoles.Role_Admin, "adminHome.xhtml");
	pagesByRoles.put(StaffRoles.Role_DeliveryStaff, "delivHome.xhtml");
	pagesByRoles.put(StaffRoles.Role_KitchenStaff, "kitchHome.xhtml");
	pagesByRoles.put(StaffRoles.Role_SUser, "suHome.xhtml");
	pagesByRoles.put(StaffRoles.Role_SysAnalytic, "sysHome.xhtml");
}
}
