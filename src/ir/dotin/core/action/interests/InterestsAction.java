package ir.dotin.core.action.interests;

import ir.dotin.core.action.UserManagementAction;
import ir.dotin.core.action.actualCustomer.ActualCustomerAction;
import ir.dotin.core.helper.GeneralHelper;
import ir.dotin.core.model.EventType;
import ir.dotin.core.model.entity.ActualCustomer;
import ir.dotin.core.model.entity.Interests;
import ir.dotin.core.utils.Configuration;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

/**
 * Created by h.safyari on 11/13/2016.
 */
@Named("interestsAction")
@SessionScoped
public class InterestsAction implements Serializable {

    @Inject
    private UserManagementAction me;
    @Inject
    private GeneralHelper generalHelper;

    @Inject
    private ActualCustomerAction actualCustomerAction;

    private Set<Interests> interestsList = new HashSet<>();
    private Interests currentInterests = null;
    private Interests newInterests = null;
    private Interests selectedInterests = new Interests();
    private TreeNode root;
    private TreeNode selectedNode;


    public void begin() {
        if (FacesContext.getCurrentInstance().isPostback()) {
            return;
        } else {
            initTree();
            refresh();
        }
    }

    private void refresh() {
        List<ActualCustomer> actualCustomers = new ArrayList<>();
        actualCustomers.add(actualCustomerAction.getCurrentActualCustomer());
        interestsList = new HashSet<>(generalHelper.getInterestsService().actualCustomer(actualCustomerAction.getCurrentActualCustomer().getId()));
    }

    public void initTree() {
        Interests rootInterests = new Interests();
        rootInterests.setTitle(me.getBundleMessage("interests"));
        root = new DefaultTreeNode(rootInterests, null);
        List<Interests> interestsRoot = fetchRoot();
        if (interestsRoot.size() > 0) {
            for (Interests interests : interestsRoot) {
                TreeNode treeNode = makeTree(interests, root);
                if (interests.getId() == selectedInterests.getId()) {
                    treeNode.setExpanded(true);
                    treeNode.getParent().setExpanded(true);
                }
                root.getChildren().add(treeNode);
            }
        }
        root.setType("default");


    }

    private List<Interests> fetchRoot() {
        List<Interests> rootList = null;
        try {
            return rootList = generalHelper.getInterestsService().findParent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public TreeNode makeTree(Interests interests, TreeNode parent) {
        TreeNode treeNode = new DefaultTreeNode(interests, parent);
        if (interests.getId() == selectedInterests.getId()) {
            treeNode.setExpanded(true);
        } else {
            treeNode.setExpanded(false);
        }
        List<Interests> childList = interests.getChildren();
        if (childList.size() != 0) {
            for (Interests child : childList) {
                TreeNode childNode = makeTree(child, treeNode);
                if (child.getId() == selectedInterests.getId()) {
                    childNode.setExpanded(true);
                }
                treeNode.getChildren().add(childNode);
            }
            treeNode.setType("default");
        } else {
            treeNode.setType("leaf");
        }
        return treeNode;
    }

    public void doDelete() {
        selectedInterests.setParent(null);
        if (generalHelper.getInterestsService().deleteInterests(selectedInterests)) {
            initTree();
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }


    public void add() {
        newInterests = new Interests();
    }

    public void doAdd() {

        newInterests.setDeleted("0");
        newInterests.setEventDate(new Date().toString());
        newInterests.setEventType(EventType.CREATE);
        newInterests.setParent(selectedInterests);
        newInterests = generalHelper.getInterestsService().createInterests(newInterests);
        if (newInterests != null) {
            initTree();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }

    }

    public void edit() {
        currentInterests = selectedInterests;
    }

    public void doEdit() {
        if (generalHelper.getInterestsService().editInterests(currentInterests) != null) {
            initTree();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
        refresh();
    }

    public void addSelected() {
        Set<ActualCustomer> actualCustomers = new HashSet<>();
        actualCustomers.add(actualCustomerAction.getCurrentActualCustomer());
        if (selectedNode != null && selectedNode.getType().equalsIgnoreCase("leaf")) {
            selectedInterests.setActualCustomerSet(actualCustomers);
            interestsList.add(selectedInterests);
            actualCustomerAction.getCurrentActualCustomer().setInterestsSet(interestsList);
            generalHelper.getActualCustomerService().editActualCustomer(actualCustomerAction.getCurrentActualCustomer());
        }
        refresh();
    }

    public void removeSelected() {


        ActualCustomer actualCustomer = actualCustomerAction.getCurrentActualCustomer();
        for (Interests interests : actualCustomer.getInterestsSet()) {
            if (interests.getId() == selectedInterests.getId()) {
                interests.setActualCustomerSet(null);
            }
        }
        if (generalHelper.getActualCustomerService().editActualCustomer(actualCustomer) != null) {
            refresh();
            me.addInfoMessage("operation.occurred");
        } else {
            me.addErrorMessage("operation.not.occurred");
        }
    }

    public void onContextMenu(NodeSelectEvent event) {
        selectedNode = event.getTreeNode();
        selectedInterests = (Interests) selectedNode.getData();
    }

    public void onNodeSelect(NodeSelectEvent event) {
        selectedNode = event.getTreeNode();
        selectedInterests = (Interests) selectedNode.getData();
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        selectedNode = event.getTreeNode();
        selectedInterests = null;
    }

    public void onNodeExpand(NodeExpandEvent event) {
        selectedNode = event.getTreeNode();
        selectedInterests = (Interests) selectedNode.getData();
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        selectedNode = event.getTreeNode();
        selectedInterests = (Interests) selectedNode.getData();
    }

    public Interests getSelectedInterests() {
        return selectedInterests;
    }

    public Set<Interests> getInterestsList() {
        return interestsList;
    }

    public void setInterestsList(Set<Interests> interestsList) {
        this.interestsList = interestsList;
    }

    public void setSelectedInterests(Interests selectedInterests) {
        this.selectedInterests = selectedInterests;
    }

    public Interests getCurrentInterests() {
        return currentInterests;
    }

    public void setCurrentInterests(Interests currentInterests) {
        this.currentInterests = currentInterests;
    }

    public Interests getNewInterests() {
        return newInterests;
    }

    public void setNewInterests(Interests newInterests) {
        this.newInterests = newInterests;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
}
