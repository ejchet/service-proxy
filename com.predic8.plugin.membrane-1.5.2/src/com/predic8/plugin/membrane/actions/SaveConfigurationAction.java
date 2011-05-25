/* Copyright 2009 predic8 GmbH, www.predic8.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License. */

package com.predic8.plugin.membrane.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.predic8.membrane.core.Router;

public class SaveConfigurationAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	
	public SaveConfigurationAction() {
		
	}
	
	public void dispose() {

	}
	
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	public void run(IAction action) {
		FileDialog fd = new FileDialog(window.getShell(), SWT.SAVE);
		fd.setText("Save Configuration");
		fd.setFilterPath("C:/");
        String[] filterExt = { "*.xml"};
        fd.setFilterExtensions(filterExt);
        String selected = fd.open();
        if (selected != null && !selected.equals("")) {
        	try {
				Router.getInstance().getConfigurationManager().saveConfiguration(selected);
			} catch (Exception e) {
				e.printStackTrace();
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Error", "Unable to save configuration: " + e.getMessage());
			}
        }
	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

}