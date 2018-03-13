package com.cBudget.controller.utils;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JsfUtil {

	public static void addSuccessMessage(String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
		FacesContext.getCurrentInstance().addMessage("sucessInfo", facesMsg);
	}

	public static void addErrorMessage(String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
		FacesContext.getCurrentInstance().addMessage("errorInfo", facesMsg);
	}
	
	public static void addInfoMessage(String message) {
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
		FacesContext.getCurrentInstance().addMessage("infoInfo", facesMsg);
	}
	
	public static void addErrorMessages(List<String> messages) {
		for (String message : messages) {
			addErrorMessage(message);
		}
	}

}
