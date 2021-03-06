package com.helmet.taglib;

import com.helmet.api.AgyService;
import com.helmet.api.ServiceFactory;
import com.helmet.taglib.abztract.AbstractTagSupport;

public class RegulatorListTag extends AbstractTagSupport 
{
  protected Object getObject() 
  {
		AgyService agyService = ServiceFactory.getInstance().getAgyService();
		return agyService.getRegulators(true);
	}
}
