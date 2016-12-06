package ir.dotin.core.helper;

import ir.dotin.core.model.entity.BaseInformationChild;
import ir.dotin.core.model.entity.City;
import ir.dotin.core.model.entity.Country;
import ir.dotin.core.model.entity.State;
import ir.dotin.core.model.service.*;
import ir.dotin.core.model.service.generic.RoleServiceImpl;
import ir.dotin.core.model.service.generic.UserServiceImpl;
import ir.dotin.core.model.service.generic.WorkgroupServiceImpl;
import ir.dotin.core.utils.Configuration;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "generalHelper")
@ApplicationScoped
public class GeneralHelper implements Serializable {


    @EJB
    private UserServiceImpl userService;
    @EJB
    private WorkgroupServiceImpl workgroupService;
    @EJB
    private RoleServiceImpl roleService;

    @EJB
    private BaseInformationParentServiceImpl baseInformationParentService;

    @EJB
    private BaseInformationChildServiceImpl baseInformationChildService;

    @EJB
    private ActualCustomerServiceImpl actualCustomerService;
    @EJB
    private AddressServiceImpl addressService;
    @EJB
    private PipeLineServiceImpl pipeLineService;
    @EJB
    private InterestsServiceImpl interestsService;
    @EJB
    private DemographyServiceImpl demographyService;
    @EJB
    private StateServiceImpl stateService;
    @EJB
    private CityServiceImpl cityService;
    @EJB
    private CountryServiceImpl countryService;

    private boolean showCaptcha = true;

    @PostConstruct
    public void init() throws Exception {
        String showCaptchaProperty = Configuration.getProperty("show_captcha");
        if (showCaptchaProperty != null && showCaptchaProperty.equals("off")) {
            showCaptcha = false;
        }

    }


    public List<BaseInformationChild> baseInfos(String parentEnglishName) {
        List<BaseInformationChild> baseInfoList = new ArrayList<>();
        return baseInfoList = getBaseInformationChildService().findByParentName(parentEnglishName);
    }

    public List<BaseInformationChild> ChildOfParent(Long parentId) {
        List<BaseInformationChild> baseInfoList = new ArrayList<>();
        if (parentId != null) {
            if (parentId != 0)
                return baseInfoList = getBaseInformationChildService().findById(String.valueOf(parentId)).getChildren();
        }
        return baseInfoList;
    }

    public List<Country> getCountry() {
        List<Country> countryList = new ArrayList<>();
        List<Country> countries = countryService.getAllCountry();
        return countries;
    }

    public List<State> getStates() {
        List<State> states = stateService.getAllState();
        return states;
    }

    public List<City> city(String stateId) {
        if (!stateId.isEmpty()) {
            List<City> cityList = cityService.findByStateId(stateId);
            return cityList;
        }
        return new ArrayList<>();
    }


    public UserServiceImpl getUserService() {
        return userService;
    }

    public WorkgroupServiceImpl getWorkgroupService() {
        return workgroupService;
    }

    public RoleServiceImpl getRoleService() {
        return roleService;
    }

    public boolean isShowCaptcha() {
        return showCaptcha;
    }

    public void setShowCaptcha(boolean showCaptcha) {
        this.showCaptcha = showCaptcha;
    }

    public BaseInformationParentServiceImpl getBaseInformationParentService() {
        return baseInformationParentService;
    }

    public BaseInformationChildServiceImpl getBaseInformationChildService() {
        return baseInformationChildService;
    }

    public ActualCustomerServiceImpl getActualCustomerService() {
        return actualCustomerService;
    }

    public AddressServiceImpl getAddressService() {
        return addressService;
    }

    public PipeLineServiceImpl getPipeLineService() {
        return pipeLineService;
    }

    public InterestsServiceImpl getInterestsService() {
        return interestsService;
    }

    public DemographyServiceImpl getDemographyService() {
        return demographyService;
    }

    public StateServiceImpl getStateService() {
        return stateService;
    }

    public CityServiceImpl getCityService() {
        return cityService;
    }

    public CountryServiceImpl getCountryService() {
        return countryService;
    }
}
