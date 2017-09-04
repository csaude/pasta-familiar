package org.openmrs.module.gaac.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.PersonAttribute;
import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FamilyListController {

	@RequestMapping(value = { "/module/gaac/familyList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView getGaacList() {
		ModelAndView modelAndView = new ModelAndView();

		GaacService service = GaacUtils.getService();
		List<Family> families= service.getAllFamily();
		
	    List<String> contacto = new ArrayList<String>();
		
	    
	    
	    for(int i = 0; i<families.size(); i++){
	    	PersonAttribute temp = families.get(i).getFocalPatient().getAttribute(9);
	    	if(temp != null)
	    	contacto.add(families.get(i).getFocalPatient().getAttribute(9).getValue());
	    	else{
	    		//Por adicionar uma melhor mensagem
	    		contacto.add("Sem contacto");
	    	}
	    }
	
		modelAndView.addObject("familyList", families);
		modelAndView.addObject("familyContato", contacto);
		return modelAndView;
		
		
		}
	
	
	
	
	
	
}

